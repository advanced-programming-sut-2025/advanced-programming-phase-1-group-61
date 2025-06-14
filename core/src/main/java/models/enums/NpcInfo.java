package models.enums;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum NpcInfo {
    // Friendship increase is for when the request is done
    Sebastian(
            List.of(ItemType.Fur, ItemType.PumpkinPie, ItemType.Pizza),
            new HashMap<>(Map.of(ItemType.Iron, 50, ItemType.PumpkinPie, 1, ItemType.Stone, 150)),
            new HashMap<>(Map.of(ItemType.Diamond, 2, ItemType.Gold, 5000, ItemType.Quartz, 50)),
            0
    ),
    Abigail(
            List.of(ItemType.Stone, ItemType.IronOre, ItemType.Coffee),
            new HashMap<>(Map.of(ItemType.Gold, 1, ItemType.Pumpkin, 1, ItemType.Wheat, 50)),
            new HashMap<>(Map.of(ItemType.Gold, 500, ItemType.WaterSprinkler, 1)),
            0
    ),
    Harvi(
            List.of(ItemType.Coffee, ItemType.Pickle, ItemType.Wine),
            new HashMap<>(Map.of(ItemType.Plant, 12, ItemType.Salmon, 1, ItemType.WineBottle, 1)),
            new HashMap<>(Map.of(ItemType.Gold, 750, ItemType.Salad, 5)),
            0
    ),
    Lia(
            List.of(ItemType.Salad, ItemType.Grape, ItemType.Wine),
            new HashMap<>(Map.of(ItemType.HardWood, 10, ItemType.Salmon, 1, ItemType.Wood, 200)),
            new HashMap<>(Map.of(ItemType.Gold, 500)),
            0
    ),
    Robin(
            List.of(ItemType.Spaghetti, ItemType.Wood, ItemType.Iron),
            new HashMap<>(Map.of(ItemType.Wood, 1000, ItemType.Iron, 10)),
            new HashMap<>(Map.of(ItemType.Gold, 1000, ItemType.BeeHouse, 3, ItemType.Pony, 25000)),
            0
    );

    private final List<ItemType> favorites;
    private final HashMap<ItemType, Integer> requests;
    private final HashMap<ItemType, Integer> rewards;
    private final int friendshipIncrease;

    NpcInfo(List<ItemType> favorites, HashMap<ItemType, Integer> requests, HashMap<ItemType, Integer> rewards, int friendshipIncrease) {
        this.favorites = favorites;
        this.requests = requests;
        this.rewards = rewards;
        this.friendshipIncrease = friendshipIncrease;
    }

    public List<ItemType> getFavorites() {
        return favorites;
    }

    public HashMap<ItemType, Integer> getRequests() {
        return requests;
    }

    public HashMap<ItemType, Integer> getRewards() {
        return rewards;
    }

    public int getFriendshipIncrease() {
        return friendshipIncrease;
    }
    public static boolean checkName(String name){
        for(NpcInfo n : NpcInfo.values()){
            if(n.name().equals(name)) return true;
        }
        return false;
    }
}
