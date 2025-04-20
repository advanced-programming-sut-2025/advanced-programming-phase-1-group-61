package models.enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegisterMenuCommands {
    Register("^register -u (?<username>.*) -p (?<password>.*) (?<password_confirm>.*) -n (?<nickname>.*) -e" +
            " (?<email>.* -g (?<gender>.*)$");
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
        String regex="^[a-zA-Z0-9]+([a-zA-Z0-9]*[.-_]+[a-zA-Z0-9])*@[a-zA-Z0-9][a-zA-Z0-9-.]*$";
        Pattern pattern = Pattern.compile(regex);
        if(!pattern.matcher(email).matches()) return false;
        String username=email.split("@")[0];
        for(int i=0;i<email.length();i++){
            if(i!=email.length()-1 && email.charAt(i)=='.' && email.charAt(i+1)=='.') return false;
        }
        String domain=email.split("@")[1];
        int dotCount=0;
        for(char c:username.toCharArray()){
            if(c=='.') dotCount++;
        }
        if(dotCount==0) return false;
        String[] parts=domain.split("\\.");
        String related=parts[parts.length-1];
        if(related.length()<2) return false;
        return true;
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
