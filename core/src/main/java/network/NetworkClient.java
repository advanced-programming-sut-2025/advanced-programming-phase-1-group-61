package network;


import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import controllers.PreGameMenuController;
import io.github.camera.Main;
import models.Game;
import models.User;
import models.character.Character;
import models.map.Map;
import network.Lobby.Chat;
import network.Lobby.GetVote;
import network.Lobby.Lobby;
import network.Lobby.VoteType;
import views.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class NetworkClient {
    private Client client;

    public  void start() {
        client = new Client(1024 * 1024, 1024 * 1024);
        client.start();


        registerClasses();

        try {
            client.connect(5000, "127.0.0.1", 54555, 54777);
            System.out.println("Connected to server");

            client.addListener(new Listener() {
                public void received (com.esotericsoftware.kryonet.Connection connection, Object object) {

                    if(object instanceof Game game){
                        if(Main.getApp().getCurrentGame() != null){
                            Main.getApp().getCurrentGame().setDate(game.getDate());
                            Main.getApp().getCurrentGame().setShops(game.getShops());

                            int currentUserId = Main.getApp().getLoggedInUser().getId();

                            for (Character newChar : game.getAllCharacters()) {
                                if (newChar.getUserId() != currentUserId) {
                                    Main.getApp().getCurrentGame().updateCharacter(newChar.getUserId(), newChar);
                                }
                            }

                        }else {
                            Main.getApp().setCurrentGame(game);
                        }

                    } else if (object instanceof Requsets request) {
                        NetworkRequest requestType = request.getRequestType();
                        switch (requestType){
                            case newGameId -> Main.getApp().setNewGameId(request.getGameId());
                        }
                    }else if (object instanceof ArrayList<?> list && !list.isEmpty() && list.get(0) instanceof User) {
                        ArrayList<User> receivedUsers = (ArrayList<User>) list;
                        Main.getApp().setAllUsers(receivedUsers);
                    } else if (object instanceof Map map) {
                        Main.getApp().getCurrentGame().setMap(map);
                    } else if (object instanceof ArrayList<?> list && !list.isEmpty() && list.get(0) instanceof Lobby) {
                        if (Main.getMain().getScreen() instanceof PreLobbyView view) {
                            @SuppressWarnings("unchecked")
                            List<Lobby> lobbies = (List<Lobby>) list;
                            view.getController().setAllLobbies(lobbies);
                        } else if (Main.getMain().getScreen() instanceof AllPlayersView view) {
                            @SuppressWarnings("unchecked")
                            List<Lobby> lobbies = (List<Lobby>) list;
                            view.getController().setLobbies(lobbies);
                        }
                    } else if (object instanceof Lobby lobby) {
                      try {
                          LobbyView view = (LobbyView) Main.getMain().getScreen();
                          view.getController().setLobby(lobby);
                      } catch (Exception e) {

                      }
                    } else if (object instanceof GetVote vote) {
                        if(Main.getMain().getScreen() instanceof GameView view){
                            if(vote.getType().equals(VoteType.KickPlayer)){
                                view.setVoting(true , "Kick player: "+vote.getUserName());
                            }else {
                                view.setVoting(true ,"Force Terminate");
                            }
                        }
                    } else if (object instanceof NetworkRequest r) {
                        if (r == NetworkRequest.KickedFromGame) {
                            Gdx.app.postRunnable(() -> {
                                Main.getMain().getScreen().dispose();
                                Main.getMain().setScreen(new PreGameMenu(new PreGameMenuController()));
                                Main.getApp().setCurrentGame(null);
                            });
                        }
                    } else if (object instanceof Chat chat) {
                        if (Main.getMain().getScreen() instanceof GameView view) {
                            view.addChatMessage(chat.getText(), chat.isPrivate());
                        }
                    } else if (object instanceof ScoreTableRefresh refresh) {
                        if(Main.getMain().getScreen() instanceof ScoreTableView view){
                            view.getController().refreshGameAndusers(refresh.getAllGames() , refresh.getAllUsers());
                        }
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
        client.sendTCP(message);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

}
