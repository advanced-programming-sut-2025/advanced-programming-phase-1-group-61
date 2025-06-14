package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;

public class LoginMenuController {
    public Result login(Matcher login){
        String username=login.group("username");
        String password=login.group("password").trim();
        String stayLoggedIn = login.group("stayLoggedIn");
        User user=User.getUserByUsername(username);
        if(user==null){
            return new Result(false , username+" dose not exist");
        }
        if(!user.getPassword().equals(SHA256.toSHA256(password))){
            return new Result(false , "password is wrong");
        }
        if(stayLoggedIn != null){
            try {
                saveLoggedInUser(user);
            } catch (IOException e) {
                return new Result(false , "failed to keep you logged in");
            }
        }
        App.setLoggedInUser(user.getId());
        return new Result(true , username + " logged in! "+"Redirecting to mainMenu..");
    }



    private void saveLoggedInUser(User user) throws IOException {
        FileWriter fileWriter = new FileWriter("loggedInUser.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(user.getId() , fileWriter);
        fileWriter.close();

    }
}