package models.shops;

public class Pierre implements Mutual{
    private final static Pierre instance = new Pierre();
    private Pierre() {

    }
    public static Pierre getPierre() {
        return instance;
    }
}
