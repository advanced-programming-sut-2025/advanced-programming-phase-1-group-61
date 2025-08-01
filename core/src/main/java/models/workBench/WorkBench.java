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

    public WorkBench(WorkBenchType type) {
        this.Type = type;
    }

    public WorkBenchType getType() {
        return Type;
    }

    public void addprocess(ItemType item ,int time) {
        Date date = App.getCurrentGame().getDate();
        inprocesses.add(new Inprocess(time, date.getDayCounter(), date.getHour(), item));
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

}
