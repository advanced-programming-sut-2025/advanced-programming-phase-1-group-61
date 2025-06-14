package controllers;

import models.*;
import models.character.Question;
import models.enums.Commands.RegisterMenuCommands;
import models.enums.Gender;
import models.enums.SecurityQuestion;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterMenuController {
    public static Result register(Matcher register){
        String username=register.group("username").trim();
        String password=register.group("password").trim();
        String confirmPassword=register.group("passwordConfirm").trim();
        String nickname=register.group("nickname").trim();
        String email=register.group("email").trim();
        String gender=register.group("gender").trim();
        User user=User.getUserByUsername(username);
        if(user!=null){
            return new Result(false , "Username already exists");
        }
        if(username.length() > 8){
            return new Result(false , "username too long");
        }
        if(!Pattern.matches(RegisterMenuCommands.USERNAME_PATTERN.getPattern(),username)){
            return new Result(false , "username format is invalid!");
        }
        if(!Pattern.matches(RegisterMenuCommands.EMAIL_PATTERN.getPattern(), email)){
            return new Result(false , "email format is invalid!");
        }
        if(password.length()<8){
            return new Result(false , "password is too short!");
        }
        if(!checkPasswordLowerCaseLetter(password)){
            return new Result(false , "password does not contain lowercase letters!");
        }
        if(!checkPasswordUpperCaseLetter(password)){
            return new Result(false , "password does not contain uppercase letters!");
        }
        if(!checkPasswordDigit(password)){
            return new Result(false , "password does not contain any numbers!");
        }
        if(!checkPasswordUniqueLetters(password)){
            return new Result(false , "password does not contain unique letters!");
        }
        if(!Pattern.matches(RegisterMenuCommands.PASSWORD_PATTERN.getPattern(), password)){
            return new Result(false , "password has invalid format");
        }

        if(!password.equals(confirmPassword)){
            return new Result(false , "repeated password is wrong");
        }

        Gender gender1 = Gender.getGender(gender);

        if(gender1==null){
            return new Result(false , "you cant choose "+gender+" as your gender!\n(male\\female)");
        }
        User newUser=new User(username , email , SHA256.toSHA256(password) ,gender,nickname);
        App.addUserToList(newUser);
        App.setRegisteredUser(newUser);
        String securityQuestions=SecurityQuestion.getQuestions();
        return new Result(true , username+" successfully registered!"+securityQuestions);
    }
    public static Result registerWithRandomPassword(Matcher register){
        String username=register.group("username").trim();
        String password=generatePassword();
        String nickname=register.group("nickname").trim();
        String email=register.group("email").trim();
        String gender=register.group("gender").trim();
        User user=User.getUserByUsername(username);
        if(user!=null) {
            return new Result(false, "Username already exists");
        }
        if(username.length() > 8){
            return new Result(false , "username too long");
        }
        if(!Pattern.matches(RegisterMenuCommands.USERNAME_PATTERN.getPattern(),username)){
            return new Result(false , "username format is invalid!");
        }
        if(!Pattern.matches(RegisterMenuCommands.EMAIL_PATTERN.getPattern(), email)){
            return new Result(false , "email format is invalid!");
        }
        User newUser=new User(username , email , SHA256.toSHA256(password) ,gender,nickname);
        App.addUserToList(newUser);
        App.setRegisteredUser(newUser);
        String securityQuestions=SecurityQuestion.getQuestions();
        return new Result(true , "your password is: "+password+"!\nsuccessfully registered!"+securityQuestions);
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
    public static Result exitGame(){
        try {
            App.saveApp();
        } catch (IOException e) {
            return new Result(false , "failed to save App :)");
        }
        return new Result(true , "App saved successfully(hopefully)");
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
    public static String generatePassword(){
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
}
