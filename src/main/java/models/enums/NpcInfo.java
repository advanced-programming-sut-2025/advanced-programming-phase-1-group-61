package models.enums;

import models.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum NpcInfo {
    //friendship increase is for when the request is done
    Sebastian(List.of(new Item(ItemType.Fur),new Item(ItemType.PumpkinPie),new Item(ItemType.Pizza)),
            new HashMap<>(Map.of(new Item(ItemType.Iron),50,new Item(ItemType.PumpkinPie),1,new Item(ItemType.Stone),150)),
            new HashMap<>(Map.of(new Item(ItemType.Diamond),2,new Item(ItemType.Gold),5000,new Item(ItemType.Quartz),50)),0),
    Abigail(List.of(new Item(ItemType.Stone),new Item(ItemType.IronOre),new Item(ItemType.Coffee)),
            new HashMap<>(Map.of(new Item(ItemType.Gold),1,new Item(ItemType.Pumpkin),1,new Item(ItemType.Wheat),50)),
            new HashMap<>(Map.of(new Item(ItemType.Gold),500,new Item(ItemType.WaterSprinkler),1)),0),
    Harvi(List.of(new Item(ItemType.Coffee),new Item(ItemType.Pickle),new Item(ItemType.Wine)),
            new HashMap<>(Map.of(new Item(ItemType.Plant),12,new Item(ItemType.Salmon),1,new Item(ItemType.WineBottle),1)),
            new HashMap<>(Map.of(new Item(ItemType.Gold),750,new Item(ItemType.Salad),5)),0),
    Lia(List.of(new Item(ItemType.Salad),new Item(ItemType.Grape),new Item(ItemType.Wine)),
            new HashMap<>(Map.of(new Item(ItemType.HardWood),10,new Item(ItemType.Salmon),1,new Item(ItemType.Wood),200)),
            new HashMap<>(Map.of(new Item(ItemType.Gold),500)),0),
    Robin(List.of(new Item(ItemType.Spaghetti),new Item(ItemType.Wood),new Item(ItemType.Iron)),
            new HashMap<>(Map.of(new Item(ItemType.Wood),80,new Item(ItemType.Iron),10,new Item(ItemType.Wood),1000)),
            new HashMap<>(Map.of(new Item(ItemType.Gold),1000,new Item(ItemType.BeeHouse),3,new Item(ItemType.Pony),25000)),0);
    private final List<Item> favorites;
    private final HashMap<Item, Integer> requests;
    private final HashMap<Item, Integer> rewards;
    private final int friendshipIncrease;
    NpcInfo(List<Item> favorites, HashMap<Item, Integer> requests, HashMap<Item, Integer> rewards,int friendshipIncrease) {
        this.favorites = favorites;
        this.requests = requests;
        this.rewards = rewards;
        this.friendshipIncrease = friendshipIncrease;
    }
    public List<Item> getFavorites() {
        return favorites;
    }
    public HashMap<Item, Integer> getRequests() {
        return requests;
    }
    public HashMap<Item, Integer> getRewards() {
        return rewards;
    }
    public int getFriendshipIncrease() {
        return friendshipIncrease;
    }


}
