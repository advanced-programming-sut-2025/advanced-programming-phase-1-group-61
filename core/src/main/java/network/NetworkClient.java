package network;


import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import models.Game;
import models.User;

import java.io.IOException;

public class NetworkClient {
    private Client client;

    public  void start() {
        client = new Client();
        client.start();


        registerClasses();

        try {
            client.connect(5000, "127.0.0.1", 54555, 54777);
            System.out.println("Connected to server");

            client.addListener(new Listener() {
                public void received (com.esotericsoftware.kryonet.Connection connection, Object object) {
                    System.out.println("Message received: " + object);
                }
            });

        } catch (IOException e) {
            System.out.println("Failed to connect to server: " + e.getMessage());
        }
    }

    private  void registerClasses() {
        client.getKryo().register(String.class);
        client.getKryo().register(Game.class);
        client.getKryo().register(User.class);
    }

    public void sendMessage(Object message) {
        client.sendTCP(message);
    }
}
