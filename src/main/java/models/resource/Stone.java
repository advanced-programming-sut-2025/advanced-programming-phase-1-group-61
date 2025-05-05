package models.resource;

import models.enums.StoneType;

public class Stone extends Resource{
    private StoneType type;

    public Stone(StoneType type) {
        this.type = type;
    }
}
