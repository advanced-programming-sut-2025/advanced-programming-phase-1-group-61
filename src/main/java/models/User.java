package models;

import models.enums.Gender;

import java.util.ArrayList;

public class User {
    private String username;
    private String email;
    private String password;
    private Gender gender;
    private ArrayList<Game> allGames=new ArrayList<>();
    public User(String username, String email, String password, String gender) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = Gender.getGender(gender);
        App.addUserToList(this);
    }
    public static User getUserByUsername(String username) {
        for(User user:App.getAllUsers()){
            if(user.username.equals(username)){
                return user;
            }
        }
        return null;
    }
}
