package models.enums;

public enum CageType {
    COOP(6,3,"Houses 4 coop-dwelling animals."),
    BIG_COOP(6,3,"Houses 8 coop-dwelling animals. Unlocks ducks."),
    DELUXE_COOP(6,3,"Houses 12 coop-dwelling animals. Unlocks rabbits."),
    BARN(7,4,"Houses 4 barn-dwelling animals."),
    BIG_BARN(7,4,"Houses 8 barn-dwelling animals. Unlocks goats."),
    DELUXE_BARN(7,4,"Houses 12 barn-dwelling animals. Unlocks sheep and pigs."),
    WELL(3,3,"Provides a place for you to refill your watering can."),
    SHIPPING_BIN(1,1,"Items placed in it will be included in the nightly shipment.");
    private final int width;
    private final int height;
    private String description;
    CageType(int width, int height,String description) {
        this.width = width;
        this.height = height;
        this.description = description;
    }
}
