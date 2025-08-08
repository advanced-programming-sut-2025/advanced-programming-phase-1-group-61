package network;


import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import io.github.camera.Main;
import models.Game;
import models.User;

import java.io.IOException;
import java.util.ArrayList;


public class NetworkClient {
    private Client client;

    public  void start() {
        client = new Client(512 * 1024, 512 * 1024);
        client.start();


        registerClasses();

        try {
            client.connect(5000, "127.0.0.1", 54555, 54777);
            System.out.println("Connected to server");

            client.addListener(new Listener() {
                public void received (com.esotericsoftware.kryonet.Connection connection, Object object) {

                    if(object instanceof Game game){
                        Main.getApp().setCurrentGame(game);
                    } else if (object instanceof Requsets request) {
                        NetworkRequest requestType = request.getRequestType();
                        switch (requestType){
                            case newGameId -> Main.getApp().setNewGameId(request.getGameId());
                        }
                    }else if (object instanceof ArrayList<?> list && !list.isEmpty() && list.get(0) instanceof User) {
                        ArrayList<User> receivedUsers = (ArrayList<User>) list;
                        for (User user : receivedUsers) {
                            System.out.println(user.getUsername());
                        }
                        Main.getApp().setAllUsers(receivedUsers);
                        System.out.println("Received users: " + receivedUsers.size());
                    }
                }
            });

        } catch (IOException e) {
            System.out.println("Failed to connect to server: " + e.getMessage());
        }
    }

    private  void registerClasses() {
        KryoRegistrations.registerClasses(client.getKryo());

    }

    public void sendMessage(Object message) {
        System.out.println("sending message: " + message);
        client.sendTCP(message);

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

}
