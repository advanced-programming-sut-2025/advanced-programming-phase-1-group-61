package models.enums;

import com.badlogic.gdx.graphics.Texture;

public enum AnimalType {
    COW("Animal/White_Cow.png",200,"Cow",ItemType.Milk),
    DINOSAUR("Animal/Dinosaur.png",1000,"Dinosaur",ItemType.Diamond),
    DUCK("Animal/Duck.png",100,"Duck",ItemType.DuckFeather),
    GOAT("Animal/Goat.png",200,"Goat",ItemType.GoatMilk),
    HEN("Animal/White_Chicken.png",50,"Hen/Chicken",ItemType.Egg),
    PIG("Animal/Pig.png",100,"Pig" ,ItemType.CommonMushroom),
    RABBIT("Animal/Rabbit.png",150,"Rabbit",ItemType.RabbitLeg),
    SHEEP("Animal/Sheep.png",75,"Sheep",ItemType.Wool);
    private final String texturePath;

    private final int price;
    private Texture texture;
    private final String displayName;
    private ItemType product;

    AnimalType(String texturePath, int price , String displayName,ItemType product) {
        this.texturePath = texturePath;
        this.price = price;
        this.displayName = displayName;
        this.product = product;
    }

    public int getPrice() {
        return price;
    }

    public String getDisplayName() {
        return displayName;
    }
    public Texture getTexture(){
        if(texture == null){
            texture = new Texture(texturePath);
        }
        return texture;
    }

    public ItemType getProduct() {
        return product;
    }
}
