package network;

import com.esotericsoftware.kryonet.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.*;
import models.building.Shop;
import models.character.Character;
import models.character.InventorySlot;
import models.resource.Resource;
import models.tool.Tool;
import network.Lobby.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServerMain {
    private static List<User> allUsers = new ArrayList<>();
    private static ArrayList<Game> allGames = new ArrayList<>();
    private static  List<Lobby> lobbies = new ArrayList<>();
    private static Server server;
    private static ScheduledExecutorService scheduler;
    private static List<VotingGames> votes = new ArrayList<>();



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
                        case MapUpdateRequest -> connection.sendTCP(getGameById(request.getGameId()).getMap());
                    }
                } else if (object instanceof Network.updateGame updateGame) {
                    updateGame(updateGame , connection);
                    connection.sendTCP(getGameById(updateGame.getGame().getId()));
                } else if (object instanceof User newUser) {
                    newUser.setConnectionId(connection.getID());
                    boolean found = false;
                    for (int i = 0; i < allUsers.size(); i++) {
                        if (allUsers.get(i).getId() == newUser.getId()) {
                            allUsers.set(i, newUser);
                            allUsers.get(i).setConnectionId(connection.getID());
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        allUsers.add(newUser);
                    }
                    for (User user : allUsers) {
                        System.out.println(user.getUsername()+" "+user.getConnectionId());
                    }
                } else if (object instanceof MapUpdate mapUpdate) {
                    getGameById(mapUpdate.getGameId()).setMap(mapUpdate.getMap());
                } else if (object instanceof Lobby lobby) {
                    for (int i = 0; i < lobbies.size(); i++) {
                        if (lobbies.get(i).getId().equals(lobby.getId())) {
                            lobbies.set(i, lobby);
                            return;
                        }
                    }
                    lobbies.add(lobby);
                } else if (object instanceof AddUserLobbyRequest request) {
                    for (Lobby lobby : lobbies) {
                        if(lobby.getId().equals(request.getLobbyId())){
                            if(lobby.getUsers().size() < 5){
                                lobby.addUser(request.getUser());
                            }
                        }
                    }
                } else if ( object instanceof NetworkRequest request) {
                    switch (request){
                        case UpdateLobbies -> {
                            connection.sendTCP(lobbies);
                        }
                        case ScoreTableRefresh ->{
                            connection.sendTCP(new ScoreTableRefresh(allUsers , allGames));
                        }
                    }
                } else if (object instanceof LobbyRequest request) {
                    for (Lobby lobby : lobbies) {
                        if(lobby.getName().equals(request.getLobbyName()) && lobby.getId().equals(request.getLobbyId())){
                            connection.sendTCP(lobby);
                            return;
                        }
                    }
                } else if (object instanceof LeaveLobbyRequest request) {
                    for (Lobby lobby : lobbies) {
                        if(lobby.getName().equals(request.getName()) && lobby.getId().equals(request.getLobbyId())){
                            User leavingUser = null;
                            for (User user : lobby.getUsers()) {
                                if(user.getUsername().equals(request.getUser().getUsername())){
                                    leavingUser = user;
                                }
                            }
                            if(leavingUser != null){
                                lobby.getUsers().remove(leavingUser);
                                return;
                            }

                        }
                    }
                } else if (object instanceof OfflineRequest request) {
                    User user = getUserByIdAndName(request.getName(), request.getId());
                    if (user != null) {
                        user.setConnectionId(0);
                        System.out.println("User " + user.getUsername() + " is now marked as offline.");
                    }
                } else if (object instanceof VotingRequest request) {
                    VotingGames votingGames = new VotingGames(request.getUserId() , request.getType() , request.getGameId() , 0);
                    String userName = "";
                    for (User user : allUsers) {
                        if(user.getId() == request.getUserId()){
                            userName = user.getUsername();
                        }
                    }

                    for (Character character : getGameById(request.getGameId()).getAllCharacters()) {
                      int userid = character.getUserId();
                        for (User user : allUsers) {
                            if(user.getId() == userid){
                                for (Connection serverConnection : server.getConnections()) {
                                    if(serverConnection.getID() == user.getConnectionId()){
                                        serverConnection.sendTCP(new GetVote(request.getType() , userName));
                                        votingGames.setVotesNeeded(votingGames.getVotesNeeded() + 1);
                                    }
                                }
                            }
                        }
                    }
                    if(votingGames.getVotesNeeded() >0){
                        votes.add(votingGames);
                    }
                }else if (object instanceof Vote vote) {
                    VotingGames targetVote = votes.stream().filter(vg -> vg.getGameId() == vote.getGameId()).findFirst().orElse(null);

                    if (targetVote != null) {
                        if ("Yes".equalsIgnoreCase(vote.getVote())) {
                            targetVote.setVotesAccepted(targetVote.getVotesAccepted() + 1);
                        } else if ("No".equalsIgnoreCase(vote.getVote())) {
                            targetVote.setVotesDeclined(targetVote.getVotesDeclined() + 1);
                        }
                        Game game = getGameById(targetVote.getGameId());
                        if (targetVote.getVotesAccepted() > Math.ceil(targetVote.getVotesNeeded() / 2.0)) {

                            if (targetVote.getType() == VoteType.ForceTerminate) {
                                if (game != null) {
                                    for (Character character : game.getAllCharacters()) {
                                        for (User user : allUsers) {
                                            if (user.getId() == character.getUserId()) {
                                                user.setGameId(0);
                                                for (Connection conn : server.getConnections()) {
                                                    if (conn.getID() == user.getConnectionId()) {
                                                        conn.sendTCP(NetworkRequest.KickedFromGame);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                allGames.removeIf(g -> g.getId() == targetVote.getGameId());
                                System.out.println("Game " + targetVote.getGameId() + " terminated by vote.");
                            }
                            else if (targetVote.getType() == VoteType.KickPlayer) {
                                if (game != null) {
                                    game.getAllCharacters().removeIf(ch -> ch.getUserId() == targetVote.getUserId());
                                    System.out.println("User " + targetVote.getUserId() + " kicked from game " + targetVote.getGameId());
                                    for (User user : allUsers) {
                                       if(targetVote.getUserId() == user.getId()){
                                           user.setGameId(0);
                                       }
                                    }
                                    Connection connection1 = getConnectionById(targetVote.getUserId());
                                    connection1.sendTCP(NetworkRequest.KickedFromGame);
                                }
                            }

                            votes.remove(targetVote);
                        }
                    }
                }else if (object instanceof Chat chat) {
                    for (Integer userId : chat.getUserIdList()) {
                        Connection connection1 =  getConnectionById(userId);
                        if(connection1 != null){
                            connection1.sendTCP(chat);
                        }
                    }
                }


            }
        });

        server.bind(Network.TCP_PORT, Network.UDP_PORT);
        server.start();
        loadApp();
        System.out.println("Server started on ports " + Network.TCP_PORT + "/" + Network.UDP_PORT);
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            try {
                myRepeatingFunction();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 30, TimeUnit.SECONDS);

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

    private static void myRepeatingFunction() {
        Lobby removingLobby = null;
        for (Lobby lobby : lobbies) {
            if(lobby.getUsers().isEmpty()){
                removingLobby = lobby;
            }
        }
        if(removingLobby != null){
            lobbies.remove(removingLobby);
            System.out.println("removed lobby: "+removingLobby.getName());
        }

    }

    public static void saveApp() throws IOException {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Tool.class, new ToolAdapter())
            .registerTypeAdapter(Resource.class, new ResourceAndBuildingAdapter())
            .registerTypeAdapter(Shop.class, new ShopAdapter())
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
            .registerTypeAdapter(Tool.class, new ToolAdapter())
            .registerTypeAdapter(Resource.class, new ResourceAndBuildingAdapter())
            .registerTypeAdapter(Shop.class, new ShopAdapter())
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

    private static void updateGame(Network.updateGame updatedGame , Connection connection) {
        Game original = getGameById(updatedGame.getGame().getId());
        if (original != null) {
            int id = updatedGame.getUserId();
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
    private static User getUserByIdAndName(String name , int id){
        for (User user : allUsers) {
            if(user.getUsername().equals(name) && user.getId() == id){
                return user;
            }
        }
        return null;
    }
   private static Connection getConnectionById(int id){
       for (User user : allUsers) {
           if(user.getId() == id){
               for (Connection connection : server.getConnections()) {
                   if(user.getConnectionId() == connection.getID()){
                       return connection;
                   }
               }
           }
       }
       return null;
   }
   private static User getUserById(int id){
       for (User user : allUsers) {
           if(user.getId() == id){
               return user;
           }
       }
       return null;
   }
}
