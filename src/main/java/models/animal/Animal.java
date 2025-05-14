package models.animal;

import models.App;

import models.Item;
import models.RandomNumber;
import models.building.Building;
import models.character.Character;
import models.enums.AnimalType;
import models.enums.ItemType;
import java.util.*;


public class Animal {
    private int X ;
    private int Y ;
    private final AnimalType type;
    private final String name;
    protected boolean hunger = true;
    protected String house;
    private int friendship = 0;
    private final List<Item> products = new ArrayList<>();
    private boolean isOut = false;
    private final int price;
    private boolean isPet = false;
    private boolean outFed = false;
    private boolean collected = false;

    public Animal(AnimalType type, String house, String name) {
        this.type = type;
        this.house = house;
        this.name = name;
        this.X = App.getCurrentGame().getCurrentCharacter().getBuilding(house).getX();
        this.Y = App.getCurrentGame().getCurrentCharacter().getBuilding(house).getY();
        this.price = type.getPrice();
    }

    public int getPrice() {
        return price;
    }

    public static boolean buy(AnimalType Type, String House, String name) {
        Character Owner = App.getCurrentGame().getCurrentCharacter();
        Animal animal = new Animal(Type, House, name);
        if (Owner.getBuilding(House).addInput(animal)) {
            Owner.addAnimal(animal, name);
            return true;
        }
        return false;


    }

    public static String getHouse(AnimalType type) {
        Character owner = App.getCurrentGame().getCurrentCharacter();
        ArrayList<Building> buildings = owner.getBuildings();
        String house = type.getHouse();
        for (Building building : buildings) {
            if (building.getBaseType().equals(house) && building.getSize() > type.getHouseSize()) {
                if (building.getSpace() > 0) {
                    return building.getName();
                }
            }
        }
        return null;
    }

    public static AnimalType TypeOf(String type) {
        return switch (type) {
            case "COW" -> AnimalType.COW;
            case "DINOSAUR" -> AnimalType.DINOSAUR;
            case "DUCK" -> AnimalType.DUCK;
            case "GOAT" -> AnimalType.GOAT;
            case "HEN" -> AnimalType.HEN;
            case "SHEEP" -> AnimalType.SHEEP;
            case "PIG" -> AnimalType.PIG;
            default -> null;
        };
    }

    public boolean pet(int x, int y) {
        if (x < X + 2 && x > X - 2 && y < Y + 2 && y > Y - 2) {
            this.friendship += 15;
            if (this.friendship > 1000) {
                this.friendship = 1000;
            }
            this.isPet = true;
            return true;
        }
        return false;
    }

    public void petByCheat(int amount) {
        friendship = amount;
        if (this.friendship > 1000) {
            this.friendship = 1000;
        }
    }

    public void feed() {
        hunger = false;
    }

    private void setProduct() {
        if(this.type.getOutNeed()){
            if(!this.outFed){
                return;
            }
        }
        if (!this.hunger) {
            ItemType itemType;
            for (int i = 1; i <= this.type.getProductPerDay(); i++) {
                itemType=this.type.getFirstProduct();
                if (this.type.getSecondProduct() != null) {
                    float probability = (float) (this.friendship + (15 * RandomNumber.getRandomNumberWithBoundaries(5, 15))) / 1500;
                    if (10 * probability >= RandomNumber.getRandomNumberWithBoundaries(0, 10)) {
                        itemType=this.type.getSecondProduct();
                    }
                }
                double quality = ((double) this.friendship / 1000) * (0.5 + 0.05 * RandomNumber.getRandomNumberWithBoundaries(0, 10));
                if (0.5 < quality && quality <= 0.7) itemType=itemType.getKind("Silver");
                else if (0.7 < quality && quality <= 0.9) itemType=itemType.getKind("Gold");
                else if (0.9 < quality) itemType=itemType.getKind("Irid");
                Item item = new Item(itemType);
                products.add(item);
            }

        }
        if (!this.isOut) {
            this.hunger = true;
        }
    }

    public boolean getProducts() {
        Character Owner = App.getCurrentGame().getCurrentCharacter();
        if (this.type.getRequired() != null) {
            if (!Owner.getInventory().checkToolInInventory(this.type.getRequired())) {
                return false;
            }
        }
        for (Item item : products) {
            Owner.getInventory().addItem(item.getItemType(), 1);
            products.remove(item);
        }
        products.clear();
        collected = true;
        return true;
    }

    public void shepherd(int x, int y, boolean isout) {
        this.X = x;
        this.Y = y;
        if (this.isOut != isout) {
            if (isout) {
                friendship += 8;
                if (friendship > 1000) {
                    friendship = 1000;
                }
                this.outFed= true;
            }
            this.isOut = isout;
        }
    }


    public void move() {
        //todo
    }

    public void dayEND() {
        if (hunger) friendship -= 20;
        if (isOut) friendship -= 10;
        if (!isPet) {
            friendship -= (friendship / 200) + 10;
        }
        setProduct();
        isPet = false;
        if (this.type == AnimalType.SHEEP || this.type == AnimalType.COW || this.type == AnimalType.GOAT) {
            if (collected) friendship += 5;
        }
        collected = false;

    }

    public String getName() {
        return name;
    }

    public int getFriendship() {
        return friendship;
    }

    public boolean isHunger() {
        return hunger;
    }

    public boolean isPet() {
        return isPet;
    }

    public List<Item> products() {
        return products;
    }

    public AnimalType getType() {
        return type;
    }

}
