package controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.camera.Main;
import models.*;
import views.LoginMenu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;

public class LoginMenuController {
    private LoginMenu view;
    private boolean stayLoggedIn;
    private String username , password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setView(LoginMenu view) {
        this.view = view;
    }

    public void setStayLoggedIn(boolean stayLoggedIn) {
        this.stayLoggedIn = stayLoggedIn;
    }

    public Result login(){
        User user=User.getUserByUsername(username);
        if(user==null){
            return new Result(false , username+" dose not exist");
        }
        if(!user.getPassword().equals(SHA256.toSHA256(password))){
            return new Result(false , "password is wrong");
        }
        if(stayLoggedIn){
            try {
                saveLoggedInUser(user);
            } catch (IOException e) {
                return new Result(false , "failed to keep you logged in");
            }
        }
        Main.getApp().setLoggedInUser(user.getId());
        return new Result(true , username + " logged in! "+"Redirecting to mainMenu..");
    }



    private void saveLoggedInUser(User user) throws IOException {
        FileWriter fileWriter = new FileWriter("loggedInUser.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(user.getId() , fileWriter);
        fileWriter.close();

    }
}
