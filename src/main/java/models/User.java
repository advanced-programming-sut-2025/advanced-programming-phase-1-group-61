package models;

import models.enums.Gender;

import java.util.ArrayList;

public class User {
    private static int numberOfUsers = 0;
    private String username;
    private int id;
    private String email;
    private String password;
    private Gender gender;
    private String nickName;
    private int gamesPlayed=0;
    private ArrayList<Question> allQuestions=new ArrayList<>();
    public User(String username, String email, String password, String gender,String nickName) {
        this.nickName = nickName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = Gender.getGender(gender);
        this.id = ++numberOfUsers;

    }
    public static User getUserByUsername(String username) {
        for(User user:App.getAllUsers()){
            if(user.username.equals(username)){
                return user;
            }
        }
        return null;
    }
    public void addQuestion(Question question) {
        allQuestions.add(question);
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public Gender getGender() {
        return gender;
    }
    public String getNickName() {
        return nickName;
    }
    public int getGamesPlayed() {
        return gamesPlayed;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public void updateGamesPlayed() {
        gamesPlayed++;
    }
}
