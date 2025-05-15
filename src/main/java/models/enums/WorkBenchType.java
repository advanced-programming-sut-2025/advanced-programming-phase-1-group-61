package models.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum WorkBenchType {
    BEEHOUSE(ItemType.Wood,40,ItemType.Coal,8,ItemType.IronBar,1),
    CHEESPRESS(ItemType.Wood,45,ItemType.Stone,45,ItemType.CopperBar,1),
    KEG(ItemType.Wood,30,ItemType.CopperBar,1,ItemType.IronBar,1),
    DEHYDRATOR(ItemType.Wood,30,ItemType.Stone,20,ItemType.Fiber,30),
    CHACOALKILN(ItemType.Wood,20,ItemType.CopperBar,2,null,0),
    LOOM(ItemType.Wood,60,ItemType.Fiber,30,null,0),
    MAYONNAISEMACHINE(ItemType.Wood,15,ItemType.Stone,15,ItemType.CopperBar,1),
    OILMAKER(ItemType.Wood,100,ItemType.GoldBar,1,ItemType.IronBar,1),
    PERESERVESJAR(ItemType.Wood,50,ItemType.Stone,40,ItemType.Coal,8),
    FISHSMOKER(ItemType.Wood,30,ItemType.Stone,20,ItemType.Fiber,30),
    FURNACE(ItemType.Stone,25,ItemType.CopperOre,20,null,0);

    private final Map<ItemType,Integer> needs=new HashMap<>();

    WorkBenchType(ItemType neede1, int amount1, ItemType neede2, int amount2, ItemType neede3, int amount3) {
        needs.put(neede1, amount1);
        needs.put(neede2, amount2);
        if(neede3!=null){
            needs.put(neede3, amount3);
        }
    }
    public Map<ItemType,Integer> needs(){
        return needs;
    }
}
