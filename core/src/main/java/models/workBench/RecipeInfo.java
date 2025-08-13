package models.workBench;

import models.enums.ItemType;

import java.util.Map;

public class RecipeInfo {
    private final String description;
    private final int energy;
    private final int processingTime;
    private final Map<ItemType, Integer> ingredients;
    private final String sellPrice;

    public RecipeInfo(String description, int energy, int processingTime, Map<ItemType, Integer> ingredients, String sellPrice) {
        this.description = description;
        this.energy = energy;
        this.processingTime = processingTime;
        this.ingredients = ingredients;
        this.sellPrice = sellPrice;
    }

    public String getDescription() { return description; }
    public int getEnergy() { return energy; }
    public int getProcessingTime() { return processingTime; }
    public Map<ItemType, Integer> getIngredients() { return ingredients; }
    public String getSellPrice() { return sellPrice; }
}
