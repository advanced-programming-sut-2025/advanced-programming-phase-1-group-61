package models.workBench;

import java.util.*;

public class ItemKinds {
    private final Map<String, List<String>> itemKinds = new HashMap<>();
    public ItemKinds() {
        List<String> vegetables = new ArrayList<>(Arrays.asList(
                "Corn", "Tomato", "Potato", "Blueberry", "Melon",
                "RedCabbage", "Radish", "Amaranth", "Kale", "Beet",
                "Parsnip", "EggPlant", "Carrot", "CauliFlower", "SpringOnion",
                "WildHorseradish", "Garlic", "GreenBean", "Rhubarb",
                "Artichoke", "BokChoy", "Broccoli"
        ));
        itemKinds.put("Vegetable", vegetables);

        // لیست میوه‌ها
        List<String> fruits = new ArrayList<>(Arrays.asList(
                "Apricot", "Apple", "Banana", "Cherry", "Mango",
                "Orange", "Peach", "Pomegranate", "Starfruit", "AncientFruit", "Grapes"
        ));
        itemKinds.put("Fruit", fruits);

        List<String> DryableFruit = new ArrayList<>(Arrays.asList(
                "Apricot", "Apple", "Banana", "Cherry", "Mango",
                "Orange", "Peach", "Pomegranate", "Starfruit", "AncientFruit"
        ));
        itemKinds.put("DryableFruit", DryableFruit);

        // لیست قارچ‌ها
        List<String> mushrooms = new ArrayList<>(Arrays.asList(
                "RedMushroom", "PurpleMushroom", "Chanterelle", "CommonMushroom"
        ));
        itemKinds.put("Mushroom", mushrooms);
        List<String> fishes = new ArrayList<>(Arrays.asList(
                "Squid", "Tuna", "Perch", "LionFish", "Herring", "GhostFish",
                "Tilapia", "Dorado", "Sunfish", "RainbowTrout", "Legend", "Glacierfish",
                "Angler", "Crimsonfish"
        ));
        itemKinds.put("Fish", fishes);
    }
    public Map<String, List<String>> getItemKinds() {
        return itemKinds;
    }

}
