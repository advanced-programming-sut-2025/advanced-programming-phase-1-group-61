package models.shops;

public class FishShop implements Mutual{
    private final static FishShop instance = new FishShop();
    private FishShop() {

    }
    public static FishShop getInstance() {
        return instance;
    }
}
