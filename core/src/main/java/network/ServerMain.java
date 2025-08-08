package network;

import com.esotericsoftware.kryonet.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.Game;
import models.User;
import models.character.Character;
import models.character.InventorySlot;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ServerMain {
    private static List<User> allUsers = new ArrayList<>();
    private static ArrayList<models.Game> allGames = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        Network.register(server);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Server is shutting down... Saving data.");
            try {
                saveApp();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        server.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof Network.HelloMessage msg) {
                    System.out.println("Received from client: " + msg.text);
                } else if (object instanceof String s) {
                    System.out.println("Received from client: "+s);
                }
            }
        });

        server.bind(Network.TCP_PORT, Network.UDP_PORT);
        server.start();
        loadApp();
        System.out.println("Server started on ports " + Network.TCP_PORT + "/" + Network.UDP_PORT);
    }
    public static void saveApp() throws IOException {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(models.tool.Tool.class, new models.ToolAdapter())
            .registerTypeAdapter(models.resource.Resource.class, new models.ResourceAndBuildingAdapter())
            .registerTypeAdapter(models.building.Shop.class , new models.ShopAdapter())
            .setPrettyPrinting()
            .create();

        try (FileWriter fileWriter = new FileWriter("users.json")) {
            gson.toJson(allUsers, fileWriter);
        }

        try (FileWriter fileWriter1 = new FileWriter("games.json")) {
            gson.toJson(allGames, fileWriter1);
        }
    }

    public static void loadApp() {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(models.tool.Tool.class, new models.ToolAdapter())
            .registerTypeAdapter(models.resource.Resource.class, new models.ResourceAndBuildingAdapter())
            .registerTypeAdapter(models.building.Shop.class , new models.ShopAdapter())
            .setPrettyPrinting()
            .create();


        File userFile = new File("users.json");
        File gameFile = new File("games.json");

        try {

            if (userFile.exists()) {
                try (FileReader fileReader = new FileReader(userFile)) {
                    Type userListType = new TypeToken<List<User>>() {}.getType();
                    allUsers = gson.fromJson(fileReader, userListType);
                }
            }


            if (gameFile.exists()) {
                try (FileReader gameFileReader = new FileReader(gameFile)) {
                    Type gameListType = new TypeToken<List<models.Game>>() {}.getType();
                    allGames = gson.fromJson(gameFileReader, gameListType);
                }
            }


            for (Game game : allGames) {
                for (Character character : game.getAllCharacters()) {
                    for (InventorySlot slot : character.getInventory().getSlots()) {
                        if(slot.getObjectInSlot() !=  null){
                            if(slot.getTool() != null){
                                slot.setObjectInSlot(slot.getTool() , 1);
                            } else if (slot.getItemType() != null) {
                                slot.setObjectInSlot(slot.getItemType() , slot.getCount());
                            }
                        }
                    }
                }
            }

            System.out.println("loaded games:"+allGames.size());
            System.out.println("loaded users:"+allUsers.size());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading files");
        }
    }
}
