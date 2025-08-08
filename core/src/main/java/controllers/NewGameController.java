package controllers;

import io.github.camera.Main;
import models.*;
import models.character.Character;
import models.map.Map;
import models.map.MapCreator.MapBuilder;
import views.NewGameView;

import java.util.ArrayList;
import java.util.List;

public class NewGameController {
    private NewGameView view;
    private int  numOfPlayers;
    private  int[] mapNumbers;
    private List<User> userList;

    public void setView(NewGameView view) {
        this.view = view;
        numOfPlayers = 2;
        this.userList = new ArrayList<>();
        mapNumbers = new int[4];
    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public Result startGameErrors() {
        List<String> usernames = new ArrayList<>();
        userList.clear();
        usernames.add(Main.getApp().getLoggedInUser().getUsername());
        if(view.getPlayer2().isVisible()){
            usernames.add(view.getPlayer2().getText());
        }if(view.getPlayer3().isVisible()){
            usernames.add(view.getPlayer3().getText());
        }if(view.getPlayer4().isVisible()){
            usernames.add(view.getPlayer4().getText());
        }
        for (int i = 1; i < usernames.size(); i++) {
            String username = usernames.get(i);
            if (username.equals(Main.getApp().getLoggedInUser().getUsername())) {
                return new Result(false, "you can't pick yourself!");
            }
        }
        StringBuilder names = new StringBuilder();

        for (String username : usernames) {
            User user = User.getUserByUsername(username.trim());
            if (user == null){
                names.append(username).append(" is invalid!").append("\n");
                break;
            }else {
                userList.add(user);
            }
        }
        for (User user : userList) {
            if (user.getGameId() != 0) {
                return new Result(false, user.getUsername() + " is already in another game.");
            }
        }
        if (!names.isEmpty()) names.deleteCharAt(names.length() - 1);
        if (!names.isEmpty()) return new Result(false, names.toString());
        view.getButtonTable().setVisible(false);
        view.getFieldTable().setVisible(false);
        view.getMapNumber().setVisible(true);
        view.setResultMessage("choosing map for: "+usernames.getFirst());
        for (String username : usernames) {
            view.addUserName(username);
        }
        return new Result(true, "");
    }

    public Result startGame( ) {

        List<models.character.Character> characterList = new ArrayList<>();
        for (User user : userList) {
            models.character.Character character = new models.character.Character(user.getId());
            characterList.add(character);
        }
        int i = numOfPlayers;
        while (i < 4){
            mapNumbers[i] = RandomNumber.getRandomNumberWithBoundaries(1,3);
            i++;
        }
        Map map = MapBuilder.buildFullMap(mapNumbers[0], mapNumbers[1], mapNumbers[2], mapNumbers[3],characterList);

        i = 0;
        for (Character character : characterList) {
            character.setX(map.getXSpawnPoints().get(i));
            character.setY(map.getYSpawnPoints().get(i));
            character.spawnCharacter(map.getXSpawnPoints().get(i) , map.getYSpawnPoints().get(i));
            i++;
        }

        Game game = new Game(map, characterList);
        for (User user : userList) {
            user.setGameId(game.getId());
        }
        Main.getApp().addGame(game);
        Main.getApp().setCurrentGame(game.getId());
        return new Result(true, "game started successfully");
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public int[] getMapNumbers() {
        return mapNumbers;
    }

    public List<User> getUserList() {
        return userList;
    }
}
