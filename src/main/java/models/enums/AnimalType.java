package models.enums;

public enum AnimalType {
    COW("","Barn",4,1500,ItemType.milk,ItemType.bigmilk,1,false,ToolType.MilkPail),
    DINOSAUR("","Coop",8,14000,ItemType.dinosaureegg,null,1,false,null),
    DUCK("","Coop",8,1200,ItemType.duckegg,ItemType.duckfeather,2,false,null),
    FISH("","Lake",0,0,null,null,1,false,null),
    GOAT("","Barn",8,4000,ItemType.goatmilk,ItemType.biggoatmilk,2,false,ToolType.MilkPail),
    HEN("","Coop",4,800,ItemType.egg,ItemType.bigegg,1,false,null),
    PIG("","Barn",12,16000,ItemType.truffle,null,1,true,null),
    RABBIT("","Coop",12,8000,ItemType.wool,ItemType.rabbitleg,4,false,null),
    SHEEP("","Barn",12,8000,ItemType.wool,null,1,false,ToolType.Shear),;
    private String texturePath;
    private String house;
    private int price;
    private ItemType firstProduct;
    private ItemType secondProduct;
    private int productPERday;
    private int houseSize;
    private boolean outNeed;
    private ToolType required;
    AnimalType(String texturePath, String house, int houseSize, int price , ItemType firstProduct,
               ItemType secondProduct, int productPERday, boolean outNeed, ToolType required) {
        this.house = house;
        this.texturePath = texturePath;
        this.price = price;
        this.firstProduct = firstProduct;
        this.secondProduct = secondProduct;
        this.productPERday = productPERday;
        this.houseSize = houseSize;
        this.outNeed = outNeed;
        this.required = required;

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

}
