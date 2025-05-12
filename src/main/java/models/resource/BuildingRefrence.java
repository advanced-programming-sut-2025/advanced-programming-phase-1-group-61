package models.resource;

import models.building.Building;
import models.enums.TileType;

public class BuildingRefrence extends Resource{
    private final String name;

    public BuildingRefrence(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
