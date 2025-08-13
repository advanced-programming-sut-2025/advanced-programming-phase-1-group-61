package models.workBench;

import io.github.camera.Main;
import models.App;
import models.character.Character;
import models.date.Date;
import models.enums.ItemType;
import models.enums.WorkBenchType;
import models.resource.Resource;

import java.util.*;


public class WorkBench extends Resource {
    private  WorkBenchType Type;
    private final List<Inprocess> inprocesses = new ArrayList<>();

    public WorkBench() {
    }

    public WorkBench(WorkBenchType type) {
        this.Type = type;
    }

    public WorkBenchType getType() {
        return Type;
    }

    public void addprocess(ItemType item ,int time) {
        Date date = Main.getApp().getCurrentGame().getDate();
        inprocesses.add(new Inprocess(time, date.getDayCounter(), date.getHour(), item));
        System.out.println("process added");
    }

    public boolean Collect() {
        Date date = Main.getApp().getCurrentGame().getDate();
        Character character = Main.getApp().getCurrentGame().getCurrentCharacter();
        boolean added = false;

        List<Inprocess> toRemove = new ArrayList<>();

        for (Inprocess inprocess : inprocesses) {
            ItemType item = inprocess.isready(date.getDayCounter(), date.getHour());
            if (item != null) {
                character.getInventory().addItem(item, 1);
                toRemove.add(inprocess);
                added = true;
            }
        }

        inprocesses.removeAll(toRemove);
        return added;
    }


    public List<Inprocess> getInprocesses() {
        return new ArrayList<>(inprocesses);
    }



    public boolean cancelProcess(Inprocess inprocess) {
        RecipeInfo recipe = Type.getRecipes().get(inprocess.getType());
        if (recipe != null) {
            Character character = Main.getApp().getCurrentGame().getCurrentCharacter();
            for (Map.Entry<ItemType, Integer> ingredient : recipe.getIngredients().entrySet()) {
                character.getInventory().addItem(ingredient.getKey(), ingredient.getValue());
            }
            inprocesses.remove(inprocess);
            return true;
        }
        return false;
    }


}
