package models.animal;

import models.App;
import models.Game;
import models.Item;
import models.RandomNumber;
import models.building.Building;
import models.character.Character;
import models.enums.AnimalType;
import models.enums.ItemType;
import models.enums.TileType;
import models.map.Tile;
import models.map.Map;

import java.util.*;


public class Animal {
    private int X = 0;
    private int Y = 0;
    private final AnimalType type;
    private String name = "";
    protected boolean hunger = true;
    protected Character owner;
    protected Building house;
    private int friendship = 0;
    private List<Item> products = new ArrayList<>();
    private boolean isout = false;
    private final int price;
    private boolean outfed = false;
    private boolean ispet=false;

    public Animal(AnimalType type, Character owner, Building house, String name) {
        this.type = type;
        this.owner = owner;
        this.house = house;
        this.name = name;
        this.X = house.getX();
        this.Y = house.getY();
        this.price = type.getPrice();
    }

    public int getPrice() {
        return price;
    }

    public static Animal buy(String type, Character owner, String name) {
        AnimalType Type = TypeOf(type);
        if (Type != null) {
            if (owner.getMoney() < Type.getPrice()) {
                System.out.println("You don't have enough money to buy this animal");
                return null;
            }
            Building House = getHouse(Type, owner);
            if (House != null) {
                if (House.getSpace() > 0) {
                    if (!owner.getAnimals().containsKey(name)) {
                        Animal animal = new Animal(Type, owner, House, name);
                        if (House.addInput(animal)) {
                            owner.addAnimal(animal, name);
                            return animal;
                        }
                        System.out.println("Can't add animal to house");
                        return null;
                    }
                    System.out.println("Name is already taken");
                    return null;
                }
            }
            System.out.println("Not enough space");
        }
        return null;
    }

    private static Building getHouse(AnimalType type, Character owner) {
        ArrayList<Building> buildings = owner.getBuildings();
        String house = type.getHouse();
        for (Building building : buildings) {
            if (building.getBaseType().equals(house) && building.getSize() > type.getHouseSize()) {
                if (building.getSpace() > 0) {
                    return building;
                }
            }
        }
        System.out.println("No empty " + house + " found");
        return null;
    }

    private static AnimalType TypeOf(String type) {
        return switch (type) {
            case "COW" -> AnimalType.COW;
            case "DINOSAUR" -> AnimalType.DINOSAUR;
            case "DUCK" -> AnimalType.DUCK;
            case "FISH" -> AnimalType.FISH;
            case "GOAT" -> AnimalType.GOAT;
            case "HEN" -> AnimalType.HEN;
            case "SHEEP" -> AnimalType.SHEEP;
            case "PIG" -> AnimalType.PIG;
            default -> {
                System.out.println("There is no such an animal here look somewhere else");
                yield null;
            }
        };
    }

    public void pet(int x, int y) {
        if (x < X + 2 && x > X - 2 && y < Y + 2 && y > Y - 2) {
            this.friendship += 15;
            ispet=true;
            System.out.println(name + ": Yeeeeee comon do it");
            if (this.friendship > 1000) {
                this.friendship = 1000;
            }
        }
        System.out.println("Go near " + name + " at " + x + "," + y + " you don't hands that long");
    }

    public void petbycheat(int amount) {
        friendship = amount;
        if (this.friendship > 1000) {
            this.friendship = 1000;
        }
        System.out.println("Youre friendship with " + name + " is " + friendship + " you cheater");
    }

    public void feed() {
        hunger = false;
        System.out.println(name + ": I am well fed yes");
    }

    private void setProduct() {
        if (this.type.getOutNeed() == isout && !hunger) {
            ItemType itemType = null;
            for (int i = 1; i <= this.type.getProductPERday(); i++) {
                itemType = null;
                if (this.type.getSecondProduct() != null) {
                    float probebility = (float) (this.friendship + (15 * RandomNumber.getRandomNumberWithBoundaries(5, 15))) / 1500;
                    if (10 * probebility >= RandomNumber.getRandomNumberWithBoundaries(0, 10)) {
                        itemType = this.type.getSecondProduct();
                    }
                }
                itemType = this.type.getFirstProduct();
                double factor = 0.0;
                double quality = (double) (((double) this.friendship / 1000) * (0.5 + 0.05 * RandomNumber.getRandomNumberWithBoundaries(0, 10)));
                if (0 < quality && quality <= 0.5) factor = 1.0;
                else if (0.5 < quality && quality <= 0.7) factor = 1.25;
                else if (0.7 < quality && quality <= 0.9) factor = 2;
                else if (0.9 < quality) factor = 2;
                Item item = new Item(itemType, factor);
                products.add(item);
            }

        }
        if (!isout) {
            hunger = true;
        }
    }

    public void getProducts() {
        if (this.type.getRequired() != null) {
            if (!owner.getInventory().checkToolInInventory(this.type.getRequired())) {
                System.out.println("You don't have any" + this.type.getRequired().toString() + " just find one dam it");
                return;
            }
        }
        for (Item item : products) {
            owner.getInventory().addItem(item, 1);
            System.out.println("You have got 1" + item.toString() + " goooood!");
            products.remove(item);
        }
        if (this.type == AnimalType.SHEEP ||
                this.type == AnimalType.COW ||
                this.type == AnimalType.GOAT) {
            friendship += 5;
            if (friendship > 1000) {
                friendship = 1000;
            }
        }
        products.clear();
    }

    public void shepherd(int x, int y) {
        Game game = App.getCurrentGame();
        assert game != null;
        Map map = game.getMap();
        Tile tile = map.getTileByCordinate(x, y);
        if (tile.getType() == TileType.grass || tile.getType() == TileType.soil) {
            switch (tile.getResource().getResourceType()) {
                case "Crop" -> {
                    System.out.println("Nooo in the crops");
                    return;
                }
                case "Barn" -> {
                    Building barn = (Building) tile.getResource();
                    if (barn.getSpace() > 0 && barn.getSize() >= this.type.getHouseSize() &&
                            this.type.getHouse().equals("Barn")) {
                        System.out.println(this.name + "is in " + barn.getName());
                        this.isout = false;
                        this.X = x;
                        this.Y = y;
                    }
                    return;
                }
                case "Coop" -> {
                    Building coop = (Building) tile.getResource();
                    if (coop.getSpace() > 0 && coop.getSize() >= this.type.getHouseSize() &&
                            this.type.getHouse().equals("Coop")) {
                        System.out.println(this.name + "is in " + coop.getName());
                        this.isout = false;
                        this.X = x;
                        this.Y = y;
                    }
                    return;
                }
            }
            if (!outfed) friendship += 8;
            if (friendship > 1000) {
                friendship = 1000;
            }
            outfed = true;
            hunger = false;
            this.isout = true;
            this.X = x;
            this.Y = y;
            return;
        }
        System.out.println("Nooooo there");
    }

    public void move() {
        //todo
    }

    public void dayEND() {
        if (hunger) {
            friendship -= 20;
            if (friendship < 0) {
                friendship = 0;
            }
        }else {
            setProduct();
        }
        if (!isout) {
            outfed = false;
        }else{
            friendship -= 20;
            if (friendship < 0) {
                friendship = 0;
            }
        }
        if(!ispet){
            friendship -= (10+friendship/200);
            if (friendship < 0) {
                friendship = 0;
            }
        }
        ispet = false;

    }

}
