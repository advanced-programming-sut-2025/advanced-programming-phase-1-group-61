package models.shops;

import models.enums.ItemType;
import models.enums.TrashcanType;

public class ShopTrashcanUpgrades {
    private final TrashcanType trashcanType;
    private final int limit;
    private final int price;
    private final ItemType ingredientsItem;
    private final int quantityOfIngredients;
    private int stock;
    public ShopTrashcanUpgrades(TrashcanType trashcanType, int limit, int price, ItemType ingredientsItem, int quantityOfIngredients) {
        this.trashcanType = trashcanType;
        this.limit = limit;
        this.price = price;
        this.ingredientsItem = ingredientsItem;
        this.quantityOfIngredients = quantityOfIngredients;
        this.stock=limit;
    }
    public TrashcanType getTrashcanType() {
        return trashcanType;
    }
    public int getLimit() {
        return limit;
    }
    public int getPrice() {
        return price;
    }
    public ItemType getIngredientsItem() {
        return ingredientsItem;
    }
    public int getQuantityOfIngredients() {
        return quantityOfIngredients;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}
