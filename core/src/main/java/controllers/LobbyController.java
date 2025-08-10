package controllers;

import com.badlogic.gdx.utils.Timer;
import io.github.camera.Main;
import models.Game;
import models.RandomNumber;
import models.Result;
import models.User;
import models.character.Character;
import models.map.Map;
import models.map.MapCreator.MapBuilder;
import network.Lobby.LeaveLobbyRequest;
import network.Lobby.Lobby;
import network.Lobby.LobbyRequest;
import network.NetworkRequest;
import network.Requsets;
import views.GameView;
import views.LobbyView;
import views.PreLobbyView;

import java.util.ArrayList;
import java.util.List;

public class LobbyController {
    private Lobby lobby;
    private LobbyView view;

    public void setView(LobbyView view) {
        this.view = view;
    }

    public LobbyController(Lobby lobby) {
        this.lobby = lobby;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
        view.refreshPlayerList();
    }

    public void sendUpdateRequest(){
        Main.getClient().sendMessage(new LobbyRequest(lobby.getId() , lobby.getName()));
    }
    public void startGame(){
        List<Integer> mapChoosed = new ArrayList<>();
        for (int selectedMap : view.getSelectedMaps()) {
            mapChoosed.add(selectedMap);
        }
        List<models.character.Character> characterList = new ArrayList<>();
        for (User user : lobby.getUsers()) {
            models.character.Character character = new models.character.Character(user.getId());
            characterList.add(character);
        }
        int i = lobby.getUsers().size();
        while (i < 4){
            mapChoosed.add(RandomNumber.getRandomNumberWithBoundaries(1,3));
            i++;
        }
        Map map = MapBuilder.buildFullMap(mapChoosed.get(0), mapChoosed.get(1), mapChoosed.get(2), mapChoosed.get(3),characterList);
        i = 0;
        for (Character character : characterList) {
            character.setX(map.getXSpawnPoints().get(i));
            character.setY(map.getYSpawnPoints().get(i));
            character.spawnCharacter(map.getXSpawnPoints().get(i) , map.getYSpawnPoints().get(i));
            i++;
        }
        Game game = new Game(map, characterList);
        for (User user : lobby.getUsers()) {
            user.setGameId(game.getId());
            Main.getClient().sendMessage(user);
        }
        Main.getApp().addGame(game);
        Main.getApp().setCurrentGame(game);
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new GameView(new GameMenuController(game)));
    }

    public void leave(){
        Main.getClient().sendMessage(new LeaveLobbyRequest(Main.getApp().getLoggedInUser(),lobby.getId() ,lobby.getName()));
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new PreLobbyView(new PreLobbyController()));
    }
    public void startGameByGameId(int gameId) {
        Main.getClient().sendMessage(
            new Requsets(NetworkRequest.GameRequest, gameId, Main.getApp().getLoggedInUser().getId())
        );


        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
              Main.getMain().getScreen().dispose();
              Main.getMain().setScreen(new GameView(new GameMenuController(Main.getApp().getCurrentGame())));
            }
        }, 1f);
    }

}
