package controllers;

import io.github.camera.Main;
import network.Lobby.AddUserLobbyRequest;
import network.Lobby.Lobby;
import network.Lobby.LobbyPrivacy;
import network.Lobby.LobbyType;
import network.NetworkRequest;
import views.CreateLobbyView;
import views.LobbyView;
import views.PreLobbyView;

import java.util.ArrayList;
import java.util.List;

public class PreLobbyController {
    private PreLobbyView view;
    private List<Lobby> allLobbies = new ArrayList<>();

    public void setView(PreLobbyView view) {
        this.view = view;
    }

    public void refreshLobbyRequest() {
        Main.getClient().sendMessage(NetworkRequest.UpdateLobbies);
    }

    public void setAllLobbies(List<Lobby> allLobbies) {
        this.allLobbies = allLobbies;
        if (view != null) {
            view.updateLobbyList(allLobbies);
        }
    }

    public void tryJoinLobby(Lobby lobby, String password) {
        if(lobby.getPrivacy().equals(LobbyPrivacy.Private)){
            if(lobby.getPassword().equals(password)){
                Main.getClient().sendMessage(new AddUserLobbyRequest(Main.getApp().getLoggedInUser() , lobby.getId()));
            }
        }else{
            Main.getClient().sendMessage(new AddUserLobbyRequest(Main.getApp().getLoggedInUser() , lobby.getId()));
        }
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new LobbyView(new LobbyController(lobby)));
    }

    public void tryJoinLobbyById(String id, String password) {
        Lobby target = allLobbies.stream()
            .filter(l -> l.getId().equals(id) || l.getType() == LobbyType.Invisible)
            .findFirst()
            .orElse(null);

        if (target != null) {
            if (target.getPrivacy() == LobbyPrivacy.Private && !password.equals(target.getPassword())) {
                System.out.println("Wrong password!");
                return;
            }
            tryJoinLobby(target, password);
        } else {
            System.out.println("Lobby not found");
        }
    }

    public void openCreateLobby() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new CreateLobbyView(new CreateLobbyController()));
    }
}

