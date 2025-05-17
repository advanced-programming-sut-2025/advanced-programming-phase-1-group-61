package models.enums;

public enum Quality {
    Normal(1),Silver(2),Gold(3),Iridium(4);
    private int number;

    Quality(int number) {
        this.number = number;
    }

    public static Quality getQualityByNumber(int i){
        for (Quality value : Quality.values()) {
            if(value.number == i){
                return value;
            }
        }
        return Normal;
    }

    public int getNumber() {
        return number;
    }
}
