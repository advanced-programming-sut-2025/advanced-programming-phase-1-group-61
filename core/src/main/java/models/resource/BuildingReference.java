package models.resource;

public class BuildingReference extends Resource{
    private final String name;

    public BuildingReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
