package models.animal;

import models.App;
import models.Game;
import models.Item;
import models.RandomNumber;
import models.building.Building;
import models.character.Character;
import models.enums.AnimalType;
import models.enums.BuildingType;
import models.enums.ItemType;
import models.enums.TileType;
import models.map.Map;
import models.map.Tile;

import java.util.*;


public class Animal {
    private int X = 0;
    private int Y = 0;
    private AnimalType type;
    private String name = "";
    protected boolean hunger = true;
    protected String house;
    private int friendship = 0;
    private List<Item> products = new ArrayList<>();
    private boolean isout=false;
    private int price;
    private boolean ispet=false;
    private boolean outfed=false;

    public Animal(AnimalType type, String house, String name) {
        this.type = type;
        this.house = house;
        this.name = name;
        this.X = App.getCurrentGame().getCurrentCharacter().getBuilding(house).getX();
        this.Y = App.getCurrentGame().getCurrentCharacter().getBuilding(house).getY();
        this.price=type.getPrice();
    }
    public int getPrice(){
        return price;
    }

    public static boolean buy(String type, int ID, String name) {
        AnimalType Type = TypeOf(type);
        Character Owner = App.getCurrentGame().getCurrentCharacter();
        if (Type != null) {
            if (Owner.getMoney() < Type.getPrice()) {
                System.out.println("You don't have enough money to buy this animal");
                return false;
            }
            String House = getHouse(Type, Owner);
            if (House != null) {
                    if (!Owner.getAnimals().containsKey(name)) {
                        Animal animal = new Animal(Type, House, name);
                        if (Owner.getBuilding(House).addInput(animal)) {
                            Owner.addAnimal(animal, name);
                            return true;
                        }
                    System.out.println("Name is already taken");
                    return false;
                }
            }
            System.out.println("Not enough space");
        }
        return false;
    }

    private static String getHouse(AnimalType type, Character owner) {
        ArrayList<Building> buildings = owner.getBuildings();
        String house = type.getHouse();
        for (Building building : buildings) {
            if (building.getBaseType().equals(house)&& building.getSize()>type.getHouseSize()) {
                if (building.getSpace() > 0) {
                    return building.getName();
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

    public boolean pet(int x, int y) {
        if (x < X + 2 && x > X - 2 && y < Y + 2 && y > Y - 2) {
            this.friendship+=15;
            if (this.friendship > 1000) {
                this.friendship = 1000;
            }
            this.ispet=true;
            return true;
        }
        return false;
    }

    public void petbycheat(int amount){
        friendship=amount;
        if (this.friendship > 1000) {
            this.friendship = 1000;
        }
    }

    public void feed(){
        hunger=false;
    }

    private void setProduct(){
        if(this.type.getOutNeed()==isout && !hunger){
            ItemType itemType=null;
            for(int i=1;i<=this.type.getProductPerDay();i++){
                itemType=null;
                if(this.type.getSecondProduct()!=null){
                    float probebility = (float) (this.friendship + (15 * RandomNumber.getRandomNumberWithBoundaries(5, 15))) /1500;
                    if(10*probebility>=RandomNumber.getRandomNumberWithBoundaries(0,10)){
                        itemType=this.type.getSecondProduct();
                    }
                }
                itemType=this.type.getFirstProduct();
                double factor=0.0;
                double quality = (double) (((double) this.friendship /1000)*(0.5+0.05*RandomNumber.getRandomNumberWithBoundaries(0,10)));
                if(0<quality&&quality<=0.5) factor=1.0;
                else if(0.5<quality&&quality<=0.7) factor=1.25;
                else if(0.7<quality&&quality<=0.9) factor=2;
                else if(0.9<quality) factor=2;
                Item item = new Item(itemType,factor);
                products.add(item);
            }

        }
        if(!isout) {
            hunger = true;
        }
    }

    public boolean getProducts() {
        Character Owner = App.getCurrentGame().getCurrentCharacter();
        if(this.type.getRequired()!=null){
            if(!Owner.getInventory().checkToolInInventory(this.type.getRequired())){
                return false;
            }
        }
        for (Item item : products) {
            Owner.getInventory().addItem(item.getItemType(),1);
            System.out.println("You have got 1"+item.toString()+" goooood!");
            products.remove(item);
        }
        products.clear();
        return true;
    }

    public boolean shepherd(int x, int y) {
        Game game = App.getCurrentGame();
        assert game != null;
        Map map = game.getMap();
        Tile tile = map.getTileByCordinate(x, y);
        if (tile.getType() == TileType.Grass || tile.getType() == TileType.Soil) {
            switch (tile.getResource().getResourceType()) {
                case "Crop" -> {
                    System.out.println("Nooo in the crops");
                    return false;
                }
                case "Barn" -> {
                    Building barn = (Building) tile.getResource();
                    if (barn.getSpace() > 0 && barn.getSize() >= this.type.getHouseSize() &&
                            this.type.getHouse().equals("Barn")) {
                        barn.addInput(this);
                        System.out.println(this.name + "is in " + barn.getName());
                        this.isout = false;
                        this.X = x;
                        this.Y = y;
                        return true;
                    }
                    return false;
                }
                case "Coop" -> {
                    Building coop = (Building) tile.getResource();
                    if (coop.getSpace() > 0 && coop.getSize() >= this.type.getHouseSize() &&
                            this.type.getHouse().equals("Coop")) {
                        System.out.println(this.name + "is in " + coop.getName());
                        this.isout = false;
                        this.X = x;
                        this.Y = y;
                        return true;
                    }
                    return false;
                }
            }
            System.out.println(this.name + "is in good palce");
            if (!outfed) friendship += 8;
            if (friendship > 1000) {
                friendship = 1000;
            }
            return true;
        }
        return false;
    }



            public void move() {
        //todo
    }
    public void dayEND() {
        if (isout)friendship-=10;
        setProduct();
        ispet=false;

    }
    public void show(){
        System.out.println(this.type+": "+this.name+" || friendship: "+this.friendship+" || hunger: "+this.hunger+" || is: "+this.ispet);
    }

    public void showproducts(){
        System.out.println(this.type+": "+this.name);
        for( Item item : products) {
            System.out.println(item.getItemType().getDisPlayName());
        }
    }

}
