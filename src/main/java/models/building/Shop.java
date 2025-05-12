package models.building;


public abstract class Shop extends Building {
    protected String owner;
    public Shop(String type, String name , int X, int Y) {
        super(String.valueOf(type), name, X, Y);
    }
    public String getOwnerName() {
        return owner;
    }
    abstract public String showAllProducts();
}
