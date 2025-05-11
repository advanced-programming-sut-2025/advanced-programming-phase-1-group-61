package models.shops;

public class Carpenter implements Mutual{
    private static final Carpenter instance = new Carpenter();
    private Carpenter() {

    }
    public static Carpenter getCarpenter() {
        return instance;
    }
}
