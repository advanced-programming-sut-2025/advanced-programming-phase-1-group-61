package models.resource;

public class Resource {
    protected int health;
    protected String texturePath;
    public Resource(String texturePath) {
        this.texturePath = texturePath;
    }
    public void draw(){}


}
