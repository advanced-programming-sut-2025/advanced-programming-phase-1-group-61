package models.workBench;

import models.App;
import models.character.Character;
import models.date.Date;
import models.enums.ItemType;
import models.enums.WorkBenchType;
import models.resource.Resource;
import java.util.*;


public class WorkBench extends Resource {
    private final WorkBenchType type;
    private final List<Inprocess> inprocesses=new ArrayList<>();
    private final Map<String, List<String>> itemKinds = new HashMap<>();

    public WorkBench(WorkBenchType type) {
        this.type = type;


        List<String> vegetables = new ArrayList<>(Arrays.asList(
                "Corn", "Tomato", "Potato", "Blueberry", "Melon",
                "RedCabbage", "Radish", "Amaranth", "Kale", "Beet",
                "Parsnip", "EggPlant", "Carrot", "CauliFlower", "SpringOnion",
                "WildHorseradish", "Garlic", "GreenBean", "Rhubarb",
                "Artichoke", "BokChoy", "Broccoli"
        ));
        itemKinds.put("Vegetable", vegetables);

        List<String> fruits = new ArrayList<>(Arrays.asList(
                "Apricot", "Apple", "Banana", "Cherry", "Mango",
                "Orange", "Peach", "Pomegranate", "Starfruit", "AncientFruit", "Grapes"
        ));
        itemKinds.put("Fruit", fruits);

        fruits.remove("Grapes");
        itemKinds.put("DryableFruit", fruits);

        List<String> mushrooms = new ArrayList<>(Arrays.asList(
                "RedMushroom", "PurpleMushroom", "Chanterelle", "CommonMushroom"
        ));
        itemKinds.put("Mushroom", mushrooms);
    }

    public WorkBenchType getType() {
        return type;
    }

    public boolean addprocess(ItemType item, ItemType need1, ItemType need2) {
        Map<String, Integer> needed = getneeded(item);
        Date date = App.getCurrentGame().getDate();
        Character character = App.getCurrentGame().getCurrentCharacter();
        if (needed == null) {
            return false;
        }
        if (needed.size() == 1) {
            inprocesses.add(new Inprocess(needed.get("Time"), date.getDayCounter(), date.getHour(), item));
            return true;
        }
        ItemType Need1=null;
        ItemType Need2=null;
        for (String key : needed.keySet()) {
            if (key.startsWith("?")) {
                key = key.replaceFirst("\\?", "");
                if(itemKinds.get(key).contains(need1.name())) {
                    Need1 = need1;
                    Need2 = need2;
                }
                else if(itemKinds.get(key).contains(need2.name())) {
                    Need1 = need2;
                    Need2 = need1;
                }else return false;
                break; // نیازی به بررسی بیشتر نیست
            }
        }
        if(Need2==null&&Need1==null) {
            if (!needed.containsKey(need1.toString())) {
                if (!needed.containsKey(need2.toString())) {
                    return false;
                }
                Need1 = need2;
                Need2 = need1;
            } else {
                Need1 = need1;
                Need2 = need2;
            }
        }

        if (character.getInventory().getCountOfItem(Need1) < needed.get(Need1.name())) {
            return false;
        }
        if (Need2!=null && needed.containsKey("+" + Need2.name())) {
            if (character.getInventory().getCountOfItem(Need2) < needed.get(Need2.name())) {
                return false;
            }
            character.getInventory().removeItem(Need2, needed.get(Need2.name()));
        }
        character.getInventory().removeItem(Need1, needed.get(Need1.name()));
        inprocesses.add(new Inprocess(needed.get("Time"), date.getDayCounter(), date.getHour(), item));
        return true;
    }

    public boolean Collect(){
        Date date = App.getCurrentGame().getDate();
        Character character = App.getCurrentGame().getCurrentCharacter();
        ItemType Item;
        boolean added=false;
        for(Inprocess inprocess : inprocesses) {
            Item =inprocess.isready(date.getDayCounter(),date.getHour());
            if(Item!=null){
                character.getInventory().addItem(Item,1);
                inprocesses.remove(inprocess);
                added=true;
            }
        }
        return added;
    }

    private Map<String, Integer> getneeded(ItemType type) {
        switch (type.toString()) {
            case "Honey": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 96);
                return map;
            }
            case "Cheese": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 3);
                map.put(ItemType.Milk.toString(), 1);
                return map;
            }
            case "BigCheese": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 3);
                map.put(ItemType.BigMilk.toString(), 1);
                return map;
            }
            case "GoatCheese": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 3);
                map.put(ItemType.GoatMilk.toString(), 1);
                return map;
            }
            case "BigGoatCheese": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 3);
                map.put(ItemType.BigGoatMilk.toString(), 1);
                return map;
            }
            case "Beer": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 24);
                map.put(ItemType.Wheat.toString(), 1);
                return map;
            }
            case "Vinegar": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 10);
                map.put(ItemType.Rice.toString(), 1);
                return map;
            }
            case "Coffee": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 2);
                map.put(ItemType.CoffeeBean.toString(), 5);
                return map;
            }
            case "Juice": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 96);
                map.put("?Vegetable", 1);
                return map;
            }
            case "Mead": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 10);
                map.put(ItemType.Honey.toString(), 1);
                return map;
            }
            case "PaleAle": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 72);
                map.put(ItemType.Hops.toString(), 1);
                return map;
            }
            case "Wine": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 168);
                map.put("?Fruit", 1);
                return map;
            }
            case "DriedFruit": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", -1);
                map.put("?DryableFruit", 5);
                return map;
            }
            case "DriedMushrooms": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", -1);
                map.put("?Mushroom", 5);
                return map;
            }
            case "Raisins": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", -1);
                map.put(ItemType.Grape.toString(), 5);
                return map;
            }
            case "Coal": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 1);
                map.put(ItemType.Wood.toString(), 10);
                return map;
            }
            case "Cloth": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 4);
                map.put(ItemType.Wood.toString(), 1);
                return map;
            }
            case "Mayonnaise": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 3);
                map.put(ItemType.Egg.toString(), 1);
                return map;
            }
            case "BigMayonnaise": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 3);
                map.put(ItemType.BigEgg.toString(), 1);
                return map;
            }
            case "DuckMayonnaise": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 3);
                map.put(ItemType.DuckEgg.toString(), 1);
                return map;
            }
            case "DinosaurMayonnaise": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 3);
                map.put(ItemType.DinosaurEgg.toString(), 1);
                return map;
            }
            case "TruffleOil": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 6);
                map.put(ItemType.Truffle.toString(), 1);
                return map;
            }
            case "Oil": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 10);
                map.put(ItemType.Corn.toString(), 1);
                map.put(ItemType.SunflowerSeed.toString(), 1);
                map.put(ItemType.Sunflower.toString(), 1);
                return map;
            }
            case "Pickles": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 6);
                map.put("?Vegetable", 1);
                return map;
            }
            case "Jelly": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 72);
                map.put("?Fruit", 1);
                return map;
            }
            case "SmokedFish": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 96);
                map.put("?Fish", 1);
                map.put("+" + ItemType.Coal.name(), 1);
                return map;
            }
            case "CopperBar": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 4);
                map.put(ItemType.CopperOre.name(), 5);
                map.put("+" + ItemType.Coal.name(), 1);
                return map;
            }
            case "IronBar": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 4);
                map.put(ItemType.IronOre.name(), 5);
                map.put("+" + ItemType.Coal.name(), 1);
                return map;
            }
            case "GoldBar": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 4);
                map.put(ItemType.GoldOre.name(), 5);
                map.put("+" + ItemType.Coal.name(), 1);
                return map;
            }
            case "IridiumBar": {
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 4);
                map.put(ItemType.IridiumOre.name(), 5);
                map.put("+" + ItemType.Coal.name(), 1);
                return map;
            }
            default: {
                return null;
            }
        }
    }
}
