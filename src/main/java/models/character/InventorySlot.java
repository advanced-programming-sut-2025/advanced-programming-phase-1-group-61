package models.character;

import models.enums.Quality;

public class InventorySlot {
    private int count;
    private Quality quality;

    public InventorySlot(int count, Quality quality) {
        this.count = count;
        this.quality = quality;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }
}
