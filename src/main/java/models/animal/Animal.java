package models.animal;

import models.Item;
import models.RandomNumber;
import models.building.Building;
import models.character.Character;
import models.character.Inventory;
import models.enums.AnimalType;
import models.enums.ItemType;
import models.map.Tile;

import java.util.*;


public class Animal {
    private int X = 0;
    private int Y = 0;
    private AnimalType type;
    private String name = "";
    protected boolean hunger = true;
    protected Character owner;
    protected Building house;
    private int friendship = 0;
    private List<Item> products = new ArrayList<>();
    private boolean isout=false;
    private int price;

    public Animal(AnimalType type, Character owner, Building house, String name) {
        this.type = type;
        this.owner = owner;
        this.house = house;
        this.name = name;
        this.X = house.getX();
        this.Y = house.getY();
        this.price=type.getPrice();
    }
    public int getPrice(){
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
            if (building.getBaseType().equals(house)&& building.getSize()>type.getHouseSize()) {
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
            this.friendship+=15;
            System.out.println(name+": Yeeeeee comon do it");
            if (this.friendship > 1000) {
                this.friendship = 1000;
            }
        }
        System.out.println("Go near " + name + " at " + x + "," + y+" you don't hands that long");
    }

    public void petbycheat(int amount){
        friendship=amount;
        if (this.friendship > 1000) {
            this.friendship = 1000;
        }
        System.out.println("Youre friendship with "+name+" is "+friendship+" you cheater");
    }

    public void feed(){
        hunger=false;
        System.out.println(name+": I am well fed yes");
    }

    private void setProduct(){
        if(this.type.getOutNeed()==isout && !hunger){
            ItemType itemType=null;
            for(int i=1;i<=this.type.getProductPERday();i++){
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

    public void getProducts() {
        if(this.type.getRequired()!=null){
            if(owner.getInventory().getToolByType(this.type.getRequired())==null){
                System.out.println("You don't have any"+this.type.getRequired().toString()+" just find one dam it");
                return;
            }
        }
        for (Item item : products) {
            owner.getInventory().addItem(item,1);
            System.out.println("You have got 1"+item.toString()+" goooood!");
            products.remove(item);
        }
        products.clear();
    }

    public void shepherd(int x,int y){

        Tile tile =null;

    }


    public void move() {
        //todo
    }
    public void dayEND() {
        if (!isout) hunger = true;
        setProduct();
    }

}
