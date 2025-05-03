package models.enums;

public enum ItemType {
    Wood(10),
    HardWood(10),
    Pony(0),
    Stone(20),
    Iron(30),
    Fur(0),
    Pizza(0),
    Coffee(0),
    Wine(0),
    WineBottle(0),
    Pumpkin(0),
    PumpkinPie(0),
    IronOre(0),
    Pickle(0),
    BeeHouse(0),
    Salad(0),
    Grape(0),
    Spaghetti(0),
    IronIngot(0),
    GoldIngot(0),
    Wheat(0),
    Gold(0),
    Quartz(0),
    WaterSprinkler(0),
    Plant(0),
    Salmon(0),
    Diamond(1000);

    private int price ;

    ItemType(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
