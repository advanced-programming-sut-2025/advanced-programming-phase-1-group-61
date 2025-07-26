package models.workBench;

import models.App;
import models.character.Character;
import models.date.Date;
import models.enums.ItemType;
import models.enums.WorkBenchType;
import models.resource.Resource;

import java.util.*;


public class WorkBench extends Resource {
    private final WorkBenchType Type;
    private final List<Inprocess> inprocesses = new ArrayList<>();
    private final Map<String, List<String>> itemKinds;

    public WorkBench(WorkBenchType type) {
        this.Type = type;
        this.itemKinds = new ItemKinds().getItemKinds();
    }

    public WorkBenchType getType() {
        return Type;
    }

    public boolean addprocess(ItemType item, ItemType need1, ItemType need2) {
        Map<String, Integer> needed = getneeded(item);
        Date date = App.getCurrentGame().getDate();
        Character character = App.getCurrentGame().getCurrentCharacter();
        if (needed == null) {
            return false;
        }
        ItemType Need1 = null;
        ItemType Need2 = null;
        if (needed.size() == 1) {
            inprocesses.add(new Inprocess(needed.get("Time"), date.getDayCounter(), date.getHour(), item));
            return true;
        }
        for (Map.Entry<String, Integer> entry : needed.entrySet()) {
            if (entry.getKey().equals("Time")) {
                continue;
            }
            ItemType x1 = null;
            if (entry.getKey().contains("?")) {
                String key = entry.getKey().replaceFirst("\\?", "");
                List<String> items = itemKinds.get(key);
                if (items == null) {
                    return false;
                }
                if (need1 != null && items.contains(need1.name())) {
                    x1 = need1;
                    need1 = null;
                } else if (need2 != null && items.contains(need2.name())) {
                    x1 = need2;
                    need2 = null;
                } else return false;
                Need1 = x1;
                continue;
            }
            if (entry.getKey().contains("+")) {
                String key = entry.getKey().replaceFirst("\\+", "");
                if (need1 != null && key.equals(need1.name())) {
                    x1 = need1;
                    need1 = null;
                } else if (need2 != null && key.equals(need2.name())) {
                    x1 = need2;
                    need2 = null;
                } else return false;
                Need2 = x1;
                continue;
            }
            if (need1 != null && entry.getKey().equals(need1.name())) {
                x1 = need1;
                need1 = null;
            } else if (need2 != null && entry.getKey().equals(need2.name())) {
                x1 = need2;
                need2 = null;
            }
            if(x1!=null){
                Need1 = x1;
            }
        }
        if (Need1 == null ) {
            return false;
        } else {
            if (character.getInventory().getCountOfItem(Need1) < needed.get(Need1.name())) {
                return false;
            }
            character.getInventory().removeItem(Need1, needed.get(Need1.name()));
            if(Need2!= null ){
                if (character.getInventory().getCountOfItem(Need2) < needed.get(Need2.name())) {
                    return false;
                }
                character.getInventory().removeItem(Need2, needed.get(Need2.name()));
            }
            inprocesses.add(new Inprocess(needed.get("Time"), date.getDayCounter(), date.getHour(), item));
            return true;
        }
    }

    public boolean Collect() {
        Date date = App.getCurrentGame().getDate();
        Character character = App.getCurrentGame().getCurrentCharacter();
        ItemType Item;
        boolean added = false;
        for (Inprocess inprocess : inprocesses) {
            Item = inprocess.isready(date.getDayCounter(), date.getHour());
            if (Item != null) {
                character.getInventory().addItem(Item, 1);
                inprocesses.remove(inprocess);
                added = true;
            }
        }
        return added;
    }

    private Map<String, Integer> getneeded(ItemType type) {
        switch (this.Type.name()) {
            case "BEEHOUSE": {
                if (!type.name().equals(ItemType.Honey.name())) return null;
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 96);
                return map;
            }
            case "CHEESPRESS": {
                switch (type.name()) {
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
                    default:
                        return null;
                }
            }
            case "KEG": {
                switch (type.name()) {
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
                    default:
                        return null;
                }
            }
            case "DEHYDRATOR": {
                switch (type.name()) {
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
                    default:
                        return null;
                }
            }
            case "CHACOALKILN": {
                if (!type.name().equals(ItemType.Coal.name())) return null;
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 1);
                map.put(ItemType.Wood.toString(), 10);
                return map;

            }
            case "LOOM": {
                if (!type.name().equals(ItemType.Cloth.name())) return null;
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 4);
                map.put(ItemType.Wood.toString(), 1);
                return map;

            }
            case "MAYONNAISEMACHINE": {
                switch (type.name()) {
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
                    default:
                        return null;
                }
            }
            case "OILMAKER": {
                switch (type.name()) {
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
                    default:
                        return null;
                }
            }
            case "PERESERVESJAR": {
                switch (type.name()) {
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
                    default:
                        return null;
                }
            }
            case "FISHSMOKER": {
                if (!type.name().equals(ItemType.SmokedFish.name())) return null;
                Map<String, Integer> map = new HashMap<>();
                map.put("Time", 96);
                map.put("?Fish", 1);
                map.put("+" + ItemType.Coal.name(), 1);
                return map;
            }
            case "FURNACE": {
                switch (type.name()) {
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
                    default:
                        return null;
                }
            }
            default:
                return null;
        }
    }
}
