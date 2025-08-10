package controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.camera.Main;
import models.*;
import models.character.Question;
import models.enums.Commands.RegisterMenuCommands;
import models.enums.Gender;
import models.enums.SecurityQuestion;
import views.LoginMenu;
import views.RegisterMenu;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterMenuController {
    private Gender gender;
    private String username , password , confirmPassword , nickname , email;
    private RegisterMenu view;
    public Result register(){
        User user=User.getUserByUsername(username);
        if(user!=null){
            AlertGenerator.showAlert("error!","Username already exists",view.getStage());
            return new Result(false , "Username already exists!");
        }
        if(username.length() > 8){
            AlertGenerator.showAlert("error!","Username too long",view.getStage());
            return new Result(false , "username is too long!");
        }
        if(!Pattern.matches(RegisterMenuCommands.USERNAME_PATTERN.getPattern(),username)){
            AlertGenerator.showAlert("error!","Invalid username!",view.getStage());
            return new Result(false , "username format is invalid!");
        }
        if(!Pattern.matches(RegisterMenuCommands.EMAIL_PATTERN.getPattern(), email)){
            AlertGenerator.showAlert("error!","Invalid email!",view.getStage());
            return new Result(false , "email format is invalid!");
        }
        if(password.length()<8){
            AlertGenerator.showAlert("error!","Password is too short!",view.getStage());
            return new Result(false , "Invalid password!");
        }
        if(!checkPasswordLowerCaseLetter(password)){
            AlertGenerator.showAlert("error!","Invalid password!",view.getStage());
            return new Result(false , "password does not contain lowercase letters!");
        }
        if(!checkPasswordUpperCaseLetter(password)){
            AlertGenerator.showAlert("error!","Invalid password!",view.getStage());
            return new Result(false , "password does not contain uppercase letters!");
        }
        if(!checkPasswordDigit(password)){
            AlertGenerator.showAlert("error!","Invalid password!",view.getStage());
            return new Result(false , "password does not contain any numbers!");
        }
        if(!checkPasswordUniqueLetters(password)){
            AlertGenerator.showAlert("error!","Invalid password!",view.getStage());
            return new Result(false , "password does not contain unique letters!");
        }
        if(!Pattern.matches(RegisterMenuCommands.PASSWORD_PATTERN.getPattern(), password)){
            AlertGenerator.showAlert("error!","Invalid password!",view.getStage());
            return new Result(false , "password has invalid format");
        }

        if(!password.equals(confirmPassword)){
            AlertGenerator.showAlert("error!","Passwords do not match!",view.getStage());
            return new Result(false , "repeated password is wrong");
        }


        User newUser=new User(username , email , SHA256.toSHA256(password) ,gender,nickname);
        Main.getApp().setLoggedInUser(newUser);
        return new Result(true , username+" successfully registered!");
    }

    public static Result pickQuestion(Matcher matcher,User user){
        String answer=matcher.group("answer").trim();
        String answerConfirm=matcher.group("answerConfirm").trim();
        int questionNumber;
        try{
            questionNumber=Integer.parseInt(matcher.group("questionNumber").trim());
        }catch (Exception e){
            return new Result(false , "please enter a valid number");
        }
        if(!answerConfirm.equals(answer))
            return new Result(false , "answers do not match");
        try{
            user.setQuestion(new Question(SecurityQuestion.getSecurityQuestion(questionNumber),answer));
        }catch (Exception e){
            return new Result(false,"the question you picked does not exist!");
        }
        return new Result(true,"question successfully picked!");
    }

    public  void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static boolean checkPasswordLowerCaseLetter(String password){
        return password.matches("^.*[a-z].*$");
    }
    public static boolean checkPasswordUpperCaseLetter(String password){
        return password.matches("^.*[A-Z].*$");
    }
    public static boolean checkPasswordDigit(String password){
        return password.matches("^.*[0-9].*$");
    }
    public static boolean checkPasswordUniqueLetters(String password){
        return password.matches("^.*[()!@#$%^&*?<>\\[\\]/+=}{].*$");
    }
    public String generatePassword(){
        final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String LOWER = "abcdefghijklmnopqrstuvwxyz";
        final String DIGITS = "0123456789";
        final String SPECIAL = "!@#$%^&*()-_=+[]{}|;:,.<>?";
        final String ALL = UPPER + LOWER + DIGITS + SPECIAL;

        int length = RandomNumber.getRandomNumberWithBoundaries(8, 13);

        List<Character> passwordChars = new ArrayList<>();

        passwordChars.add(UPPER.charAt(RandomNumber.getRandomNumberWithBoundaries(0, UPPER.length())));
        passwordChars.add(LOWER.charAt(RandomNumber.getRandomNumberWithBoundaries(0, LOWER.length())));
        passwordChars.add(DIGITS.charAt(RandomNumber.getRandomNumberWithBoundaries(0, DIGITS.length())));
        passwordChars.add(SPECIAL.charAt(RandomNumber.getRandomNumberWithBoundaries(0, SPECIAL.length())));

        for (int i = 4; i < length; i++) {
            passwordChars.add(ALL.charAt(RandomNumber.getRandomNumberWithBoundaries(0, ALL.length())));
        }

        Collections.shuffle(passwordChars);

        StringBuilder password = new StringBuilder();
        for (char c : passwordChars) {
            password.append(c);
        }

        return password.toString();
    }
    public void setView(RegisterMenu view){
        this.view = view;
    }
}
