package models.shops;

public class StarDrop implements Shops {
    //needs recipe
    private final static StarDrop instance = new StarDrop();
    private StarDrop() {

    }
    public static StarDrop getStarDrop() {
        return instance;
    }
}
