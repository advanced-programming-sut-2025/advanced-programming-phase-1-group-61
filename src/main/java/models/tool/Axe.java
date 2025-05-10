package models.tool;

import models.App;
import models.character.Character;
import models.enums.Direction;
import models.enums.ToolType;

import java.util.Map;


public class Axe extends Tool{

    public Axe() {
        super(ToolType.Axe);
    }

    @Override
    public String use(Direction direction) {
        Character character= App.getCurrentGame().getCurrentCharacter();
        int newEnergy=character.getEnergy()-type.getEnergyConsumption(level);
        character.setEnergy(newEnergy);
        return "used axe";
    }

    @Override
    public ToolType getType() {
        return ToolType.Axe;
    }


    public void upgrade() {
        if(this.level.equals("iridium")) return;
        String nextLevel = type.getNextLevel(level);
        if(nextLevel==null) return;
        this.level = nextLevel;
    }
}
