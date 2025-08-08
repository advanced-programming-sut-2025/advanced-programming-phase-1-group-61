package network;

import com.esotericsoftware.kryonet.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.Game;
import models.User;
import models.character.Character;
import models.character.InventorySlot;
import models.shops.Pierre;


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
                try {
                    System.out.println("Received: " + object);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (object instanceof Requsets request) {
                    NetworkRequest requestType = request.getRequestType();
                    switch (requestType){
                        case GameRequest -> connection.sendTCP(getGameById(request.getGameId()));
                        case newGameId -> connection.sendTCP(new Requsets(NetworkRequest.newGameId , generateNewGameId(),0));
                        case AllUsersRequest -> connection.sendTCP(allUsers);
                    }
                } else if (object instanceof Network.updateGame updateGame) {
                    System.out.println("game updated");
                    updateGame(updateGame);
                } else if (object instanceof User newUser) {
                    boolean found = false;
                    for (int i = 0; i < allUsers.size(); i++) {
                        if (allUsers.get(i).getId() == newUser.getId()) {
                            allUsers.set(i, newUser);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        allUsers.add(newUser);
                    }
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
    private static Game getGameById(int id){
        for (Game game : allGames) {
            if(game.getId() == id){
                return game;
            }
        }
        return null;
    }
    private static void updateGame(Network.updateGame updatedGame) {
        Game original = getGameById(updatedGame.getGame().getId());
        if (original != null) {
            int id = updatedGame.getUserId();
            //Character Update
            original.updateCharacter(id, updatedGame.getGame().getCharacterByID(id));
            //Date Update
            original.getDate().setHour(updatedGame.getGame().getDate().getHour());
            original.getDate().setDayCounter(updatedGame.getGame().getDate().getDayCounter());
            original.getDate().setDay(updatedGame.getGame().getDate().getDay());
            original.getDate().setSeason(updatedGame.getGame().getDate().getSeason());
            original.getDate().setHasASeasonPassed(updatedGame.getGame().getDate().hasASeasonPassed());
        } else {
            allGames.add(updatedGame.getGame());
        }
    }
    private static int generateNewGameId() {
        int id = 1;
        while (true) {
            boolean used = false;
            for (Game game : allGames) {
                if (game.getId() == id) {
                    used = true;
                    break;
                }
            }
            if (!used) {
                return id;
            }
            id++;
        }
    }


}
