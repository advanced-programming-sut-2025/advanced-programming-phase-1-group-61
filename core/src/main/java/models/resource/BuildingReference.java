package models.resource;

public class BuildingReference extends Resource{
    private final String name;

    public BuildingReference(String name) {
        super("Crops/Blue_Jazz_Stage_5.png");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
