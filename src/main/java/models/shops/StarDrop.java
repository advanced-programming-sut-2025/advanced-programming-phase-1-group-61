package models.shops;

import models.building.Shop;

public class StarDrop extends Shop {
    //needs recipe

    public StarDrop(String type, String name, int X, int Y) {
        super(type, name, X, Y);
    }

    @Override
    public String showAllProducts() {
        return "";
    }

    @Override
    public String showAllAvailableProducts() {
        return "";
    }

    @Override
    public String purchaseProduct(String product, int count) {
        return "";
    }

    @Override
    public void restoreStocks() {

    }
}
