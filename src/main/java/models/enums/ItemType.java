package models.enums;

public enum ItemType {
    Wood(10,"wood",0 , false),
    HardWood(10,"hard wood" , 0 , false),
    Pony(0,"pony" , 0 , false) ,
    Stone(20 , "stone" , 0 , false),
    Iron(30 , "iron" , 0 , false),
    Fur(0 , "fur" , 0 , false),
    Pizza(300 , "pizza" , 150 , true),
    Coffee(300 , "coffe" , 100 , true),
    Wine(200 , "ab shangooli" , 25 , true),
    WineBottle(0 , "botri ab shangooli" , 0 , false),
    Pumpkin(320 , "pumpkin" , 0 , false),
    PumpkinPie(385, "pumpkin pie" , 225 , true),
    IronOre(150 , "iron ore" , 0 , false),
    Pickle(0,"pickle" , 10 , true),
    BeeHouse(0,"bee house" , 0 , false),
    Salad(110,"salad" , 113 , true),
    Grape(38,"grape" , 80 ,true ),
    Spaghetti(120 , "spaghetti" , 75, true),
    IronIngot(0,"iron" , 0 , false),
    GoldIngot(0 , "gold ingot" , 0 , false),
    Wheat(0 , "wheat" , 0 , false),
    Gold(0 , "gold" , 0 , false),
    Quartz(0 , "quartz" , 0 , false),
    WaterSprinkler(0 , "water sprinkler" , 0 , false),
    Plant(0 , "plant" , 0,false),
    Salmon(0,"salmon" , 0 , false),
    Diamond(1000 , "diamond" , 0 ,false),
    blueJazz(50,"blue jazz" , 45 , true),
    carrot(35,"carrot" , 75 , true),
    cauliFlower(175 , "cauli flower" , 75 , false),
    coffeeBeen(15 , "coffee" , 0 , false),
    commonMushroom(40 , "common mushroom" , 38 , true),
    daffodil(30 , "daffodil" , 0 , false),
    dandelion(40 , "dandelion" , 25 , true),
    leek(60 , "leek",40 , true),
    moral(150 , "moral", 20 , true),
    salmonBerry(5 , "salmon berry", 25 , true),
    springOnion(8  ,"spring onion" , 13 , true),
    wildHorseradish(50  , "wildHorseradish" , 13 , true),
    fiddleHead(90 , "fiddle head" ,25 , true),
    redMushroom(75, "red mushroom", -50, true),
    spiceBerry(80, "spice berry", 25, true),
    sweetPea(50, "sweet pea", 0, false),
    blackberry(25, "blackberry", 25, true),
    chanterelle(160, "chanterelle", 75, true),
    hazelnut(40, "hazelnut", 38, true),
    purpleMushroom(90, "purple mushroom", 30, true),
    wildPlum(80, "wild plum", 25, true),
    crocus(60, "crocus", 0, false),
    crystalFruit(150, "crystal fruit", 63, true),
    holly(80, "holly", -37, true),
    snowYam(100, "snow yam", 30, true),
    winterRoot(70, "winter root", 25, true);




    private int price ;
    private String disPlayName;
    private int energy;
    private boolean isEdible;

    ItemType(int price, String disPlayName, int energy, boolean isEdible) {
        this.price = price;
        this.disPlayName = disPlayName;
        this.energy = energy;
        this.isEdible = isEdible;
    }

    public int getPrice() {
        return price;
    }

    public String getDisPlayName() {
        return disPlayName;
    }

    public int getEnergy() {
        return energy;
    }

    public boolean isEdible() {
        return isEdible;
    }
}
