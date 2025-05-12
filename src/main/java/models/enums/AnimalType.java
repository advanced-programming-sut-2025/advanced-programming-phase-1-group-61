package models.enums;

public enum AnimalType {
    COW("","Barn",4,1500,ItemType.Milk,ItemType.BigMilk,1,false,ToolType.MilkPail,"Cow"),
    DINOSAUR("","Coop",8,14000,ItemType.DinosaurEgg,null,1,false,null,"Dinosaur"),
    DUCK("","Coop",8,1200,ItemType.DuckEgg,ItemType.DuckFeather,2,false,null,"Duck"),
    GOAT("","Barn",8,4000,ItemType.GoatMilk,ItemType.BigGoatMilk,2,false,ToolType.MilkPail,"Goat"),
    HEN("","Coop",4,800,ItemType.Egg,ItemType.BigEgg,1,false,null,"Hen/Chicken"),
    PIG("","Barn",12,16000,ItemType.Truffle,null,1,true,null,"Pig"),
    RABBIT("","Coop",12,8000,ItemType.Wool,ItemType.RabbitLeg,4,false,null,"Rabbit"),
    SHEEP("","Barn",12,8000,ItemType.Wool,null,1,false,ToolType.Shear,"Sheep"),;
    private final String texturePath;
    private final String house;
    private final int price;
    private final ItemType firstProduct;
    private final ItemType secondProduct;
    private final int productPERday;
    private final int houseSize;
    private final boolean outNeed;
    private final ToolType required;
    private final String displayName;
    AnimalType(String texturePath, String house, int houseSize, int price , ItemType firstProduct,
               ItemType secondProduct, int productPERday, boolean outNeed, ToolType required, String displayName) {
        this.house = house;
        this.texturePath = texturePath;
        this.price = price;
        this.firstProduct = firstProduct;
        this.secondProduct = secondProduct;
        this.productPERday = productPERday;
        this.houseSize = houseSize;
        this.outNeed = outNeed;
        this.required = required;
        this.displayName = displayName;
    }
    public ToolType getRequired() {
        return required;
    }
    public int getProductPerDay() {
        return productPERday;
    }
    public int getHouseSize() {
        return houseSize;
    }
    public String getTexturePath() {
        return texturePath;
    }
    public String getHouse() {
        return house;
    }
    public int getPrice() {
        return price;
    }
    public boolean getOutNeed() {
        return outNeed;
    }
    public ItemType getFirstProduct() {
        return firstProduct;
    }
    public ItemType getSecondProduct() {
        return secondProduct;
    }
    public String getDisplayName() {
        return displayName;
    }
}
