package models.resource;

public class BuildingReference extends Resource{
    private  String name;

    public BuildingReference(String name) {
        this.name = name;
    }

    public BuildingReference() {
    }

    public String getName() {
        return name;
    }
}
