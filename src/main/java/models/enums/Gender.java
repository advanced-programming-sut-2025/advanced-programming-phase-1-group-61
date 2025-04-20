package models.enums;

public enum Gender {
    Male, Female;
    public static Gender getGender(String gender) {
        switch (gender.toLowerCase()){
            case "male": return Male;
            case "female": return Female;
            default: return null;
        }
    }
}
