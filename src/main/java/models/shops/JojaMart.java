package models.shops;

import models.Item;
import models.enums.ItemType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JojaMart implements Mutual{
    private static final JojaMart instance = new JojaMart();
    private final ArrayList<ShopItem> items;
    private JojaMart() {
        items = new ArrayList<>(List.of(
                new ShopItem(new Item(ItemType.JojaCola),Integer.MAX_VALUE,75),
                new ShopItem(new Item(ItemType.Rice))
        ));
    }
    public static JojaMart getJojaMart() {
        return instance;
    }
}
