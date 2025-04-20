package models.enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegisterMenuCommands {
    Register("^register -u (?<username>.*) -p (?<password>.*) (?<passwordConfirm>.*) -n (?<nickname>.*) -e" +
            " (?<email>.*) -g (?<gender>.*)$"),
    pickQuestion("^pick question\\s+-q\\s+(?<questionNumber>\\d*)\\s+" +
            "-a\\s+(?<answer>.*) -c\\s+(?<answerConfirm>.*)$");
    private String regex;
    RegisterMenuCommands(String regex) {
        this.regex = regex;
    }
    public static Matcher getMatcher(String input,RegisterMenuCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        return pattern.matcher(input);
    }
    public static boolean checkUsername(String username) {
        String regex="^[a-zA-Z0-9-]+$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(username).matches();
    }
    public static boolean checkEmail(String email) {
        String regex= "^[a-zA-Z\\d\\.](?:[A-Za-z\\d]*\\.)?[A-Za-z\\d]*@[a-z]+\\.com";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()) return false;
        int count=0;
        for(char c:email.toCharArray()){
            if(c=='.') count++;
        }
        return count <= 2;
    }
    public static boolean checkPasswordSmallLetter(String password) {
        for(char c:password.toCharArray()){
            if((c-'a')>=0 && (c-'a')<=25) return true;
        }
        return false;
    }
    public static boolean checkPasswordCapitalLetter(String password) {
        for(char c:password.toCharArray()){
            if((c-'A')>=0 && (c-'A')<=25) return true;
        }
        return false;
    }
    public static boolean checkPasswordUniqueLetter(String password) {
        char[] uniques="!@#$%^&*?><\"';:\\/|[]{}+=()%".toCharArray();
        for(char c:password.toCharArray()){
            for(char u:uniques){
                if(c==u) return true;
            }
        }
        return false;
    }
    public static boolean checkPasswordNumber(String password) {
        for(char c:password.toCharArray()){
            if((c-'0')>=0 && (c-'0')<=9) return true;
        }
        return false;
    }
}
