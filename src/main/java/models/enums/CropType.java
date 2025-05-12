package models.enums;

import models.Item;

public enum CropType {

    BlueJazz(new int[]{1, 2, 2, 2}, -1, false, ItemType.BlueJazz, ItemType.JazzSeed),
    Carrot(new int[]{1, 1, 1}, -1, false, ItemType.Carrot, ItemType.CarrotSeed),
    Cauliflower(new int[]{1, 2, 4, 4, 1}, -1, true, ItemType.CauliFlower, ItemType.CauliflowerSeed),
    CoffeeBean(new int[]{1, 2, 2, 3, 2}, 2, false, ItemType.CoffeeBean, ItemType.CoffeeBean),
    Garlic(new int[]{1, 1, 1, 1}, -1, false, ItemType.Garlic, ItemType.GarlicSeed),
    GreenBean(new int[]{1, 1, 1, 3, 4}, 3, false, ItemType.GreenBean, ItemType.BeanStarter),
    Kale(new int[]{1, 2, 2, 1}, -1, false, ItemType.Kale, ItemType.KaleSeed),
    Parsnip(new int[]{1, 1, 1, 1}, -1, false, ItemType.Parsnip, ItemType.ParsnipSeed),
    Potato(new int[]{1, 1, 1, 2, 1}, -1, false, ItemType.Potato, ItemType.PotatoSeed),
    Rhubarb(new int[]{2, 2, 2, 3, 4}, -1, false, ItemType.Rhubarb, ItemType.RhubarbSeed),
    Strawberry(new int[]{1, 1, 2, 2, 2}, 4, false, ItemType.Strawberry, ItemType.StrawberrySeed),
    Tulip(new int[]{1, 1, 2, 2}, -1, false, ItemType.Tulip, ItemType.TulipBulb),
    UnmilledRice(new int[]{1, 2, 2, 3}, -1, false, ItemType.UnmilledRice, ItemType.RiceShoot),
    Blueberry(new int[]{1, 3, 3, 4, 2}, 4, false, ItemType.Blueberry, ItemType.BlueberrySeed),
    Corn(new int[]{2, 3, 3, 3, 3}, 4, false, ItemType.Corn, ItemType.CornSeed),
    Hops(new int[]{1, 1, 2, 3, 4}, 1, false, ItemType.Hops, ItemType.HopsStarter),
    HotPepper(new int[]{1, 1, 1, 1, 1}, 3, false, ItemType.HotPepper, ItemType.PepperSeed),
    Melon(new int[]{1, 2, 3, 3, 3}, -1, true, ItemType.Melon, ItemType.MelonSeed),
    Poppy(new int[]{1, 2, 2, 2}, -1, false, ItemType.Poppy, ItemType.PoppySeed),
    Radish(new int[]{2, 1, 2, 1}, -1, false, ItemType.Radish, ItemType.RadishSeed),
    RedCabbage(new int[]{2, 1, 2, 2, 2}, -1, false, ItemType.RedCabbage, ItemType.RedCabbageSeed),
    Starfruit(new int[]{2, 3, 2, 3, 3}, -1, false, ItemType.Starfruit, ItemType.StarfruitSeed),
    SummerSpangle(new int[]{1, 2, 3, 1}, -1, false, ItemType.SummerSpangle, ItemType.SpangleSeed),
    Sunflower(new int[]{1, 2, 3, 2}, -1, false, ItemType.Sunflower, ItemType.SunflowerSeed),
    Tomato(new int[]{2, 2, 2, 2, 3}, 4, false, ItemType.Tomato, ItemType.TomatoSeed),
    Wheat(new int[]{1, 1, 1, 1}, -1, false, ItemType.Wheat, ItemType.WheatSeed),
    Amaranth(new int[]{1, 2, 2, 2}, -1, false, ItemType.Amaranth, ItemType.AmaranthSeed),
    Artichoke(new int[]{2, 2, 1, 2, 1}, -1, false, ItemType.Artichoke, ItemType.ArtichokeSeed),
    Beet(new int[]{1, 1, 2, 2}, -1, false, ItemType.Beet, ItemType.BeetSeed),
    BokChoy(new int[]{1, 1, 1, 1}, -1, false, ItemType.BokChoy, ItemType.BokChoySeed),
    Broccoli(new int[]{2, 2, 2, 2}, 4, false, ItemType.Broccoli, ItemType.BroccoliSeed),
    Cranberries(new int[]{1, 2, 1, 1, 2}, 5, false, ItemType.Cranberries, ItemType.CranberrySeed),
    SweetGemBerry(new int[]{2, 4, 6, 6, 6}, -1, false, ItemType.SweetGemBerry, ItemType.RareSeed),
    Powdermelon(new int[]{1, 2, 1, 2, 1}, -1, true, ItemType.Powdermelon, ItemType.PowdermelonSeed),
    AncientFruit(new int[]{2, 7, 7, 7, 5}, 7, false, ItemType.AncientFruit, ItemType.AncientSeed);



    private int[] stages ;
    private int reGrowthTime ;
    private boolean canBecomeGiant;
    private ItemType product , source;

    CropType( int[] stages, int reGrowthTime, boolean canBecomeGiant,ItemType product,ItemType source) {
        this.stages = stages;
        this.reGrowthTime = reGrowthTime;
        this.canBecomeGiant = canBecomeGiant;
        this.source = source;
        this.product = product;
    }
    public int getStageByCropAge(int age){
        int stage = 0;
        int dayCounter =0;
        for (int i = 0; i < this.stages.length; i++) {
            dayCounter += stages[i];
            if(dayCounter < age){
                stage++;
            }else {
                return stage;
            }
        }
        return 1;
    }
    public int getTotalHarvestTime(){
        int counter =0;
        for (int stage : stages) {
            counter += stage;
        }
        return counter;
    }

    public boolean CanBecomeGiant() {
        return canBecomeGiant;
    }

    public ItemType getProduct() {
        return product;
    }

    public ItemType getSource() {
        return source;
    }

    public int getReGrowthTime() {
        return reGrowthTime;
    }
}
