package network;

import com.esotericsoftware.kryonet.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.Game;
import models.User;
import models.character.Character;
import models.character.InventorySlot;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ServerMain {
    private static List<User> allUsers = new ArrayList<>();
    private static ArrayList<Game> allGames = new ArrayList<>();
    private static Server server;

    public static void main(String[] args) throws Exception {
        server = new Server(1024*1024,1024*1024);
        Network.register(server);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Server is shutting down... Saving data.");
            try {
                saveApp();
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Failed to save data on shutdown");
            }
        }));

        server.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof Requsets request) {
                    NetworkRequest requestType = request.getRequestType();
                    switch (requestType) {
                        case GameRequest -> {
                            connection.sendTCP(getGameById(request.getGameId()));
                        }
                        case newGameId -> connection.sendTCP(new Requsets(NetworkRequest.newGameId, generateNewGameId(), 0));
                        case AllUsersRequest -> connection.sendTCP(allUsers);
                    }
                } else if (object instanceof Network.updateGame updateGame) {
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

        new Thread(() -> {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Type 'shutdown' to stop the server.");
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.equalsIgnoreCase("shutdown")) {
                        System.out.println("Shutdown command received. Stopping server...");
                        try {
                            saveApp();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Server stopped. Exiting.");
                        server.stop();
                        System.exit(0);
                    } else {
                        System.out.println("Unknown command: " + line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void saveApp() throws IOException {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(models.tool.Tool.class, new models.ToolAdapter())
            .registerTypeAdapter(models.resource.Resource.class, new models.ResourceAndBuildingAdapter())
            .registerTypeAdapter(models.building.Shop.class, new models.ShopAdapter())
            .setPrettyPrinting()
            .create();

        try (FileWriter fileWriter = new FileWriter("users.json")) {
            gson.toJson(allUsers, fileWriter);
            System.out.println("all users saved"+allUsers.size());
        }

        try (FileWriter fileWriter1 = new FileWriter("games.json")) {
            gson.toJson(allGames, fileWriter1);
            System.out.println("all games saved"+allGames.size());
        }
    }

    public static void loadApp() {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(models.tool.Tool.class, new models.ToolAdapter())
            .registerTypeAdapter(models.resource.Resource.class, new models.ResourceAndBuildingAdapter())
            .registerTypeAdapter(models.building.Shop.class, new models.ShopAdapter())
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
                    Type gameListType = new TypeToken<List<Game>>() {}.getType();
                    allGames = gson.fromJson(gameFileReader, gameListType);
                }
            }

            for (Game game : allGames) {
                for (Character character : game.getAllCharacters()) {
                    for (InventorySlot slot : character.getInventory().getSlots()) {
                        if (slot.getObjectInSlot() != null) {
                            if (slot.getTool() != null) {
                                slot.setObjectInSlot(slot.getTool(), 1);
                            } else if (slot.getItemType() != null) {
                                slot.setObjectInSlot(slot.getItemType(), slot.getCount());
                            }
                        }
                    }
                }
            }

            System.out.println("loaded games:" + allGames.size());
            System.out.println("loaded users:" + allUsers.size());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading files");
        }
    }

    private static Game getGameById(int id) {
        for (Game game : allGames) {
            if (game.getId() == id) {
                return game;
            }
        }
        return null;
    }

    private static void updateGame(Network.updateGame updatedGame) {
        Game original = getGameById(updatedGame.getGame().getId());
        if (original != null) {
            int id = updatedGame.getUserId();
            // Character Update
            original.updateCharacter(id, updatedGame.getGame().getCharacterByID(id));
            original.setMap(updatedGame.getGame().getMap());

            original.setDate(updatedGame.getGame().getDate());
            original.setMap(updatedGame.getGame().getMap());
            original.setNpcList(updatedGame.getGame().getNpcList());
            original.setShops(updatedGame.getGame().getShops());


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
