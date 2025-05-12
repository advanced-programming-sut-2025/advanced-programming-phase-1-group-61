package models.enums;

public enum CageType {
    COOP(6,3,"Houses 4 coop-dwelling animals.","Coop"),
    BIG_COOP(6,3,"Houses 8 coop-dwelling animals. Unlocks ducks.","Big Coop"),
    DELUXE_COOP(6,3,"Houses 12 coop-dwelling animals. Unlocks rabbits.","Deluxe Coop"),
    BARN(7,4,"Houses 4 barn-dwelling animals.","Barn"),
    BIG_BARN(7,4,"Houses 8 barn-dwelling animals. Unlocks goats.","Big Barn"),
    DELUXE_BARN(7,4,"Houses 12 barn-dwelling animals. Unlocks sheep and pigs.","Deluxe Barn"),
    WELL(3,3,"Provides a place for you to refill your watering can.","Well"),
    SHIPPING_BIN(1,1,"Items placed in it will be included in the nightly shipment.","Shipping Bin"),;
    private final int width;
    private final int height;
    private final String description;
    private final String displayName;
    CageType(int width, int height,String description,String displayName) {
        this.width = width;
        this.height = height;
        this.description = description;
        this.displayName = displayName;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public String getDescription() {
        return description;
    }
    public String getDisplayName() {
        return displayName;
    }
}
