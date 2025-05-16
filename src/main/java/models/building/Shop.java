package models.building;


public abstract class Shop extends Building {
    protected String owner;
    public Shop( String name , int X, int Y) {
        super( name, X, Y);
    }
    public String getOwnerName() {
        return owner;
    }
    abstract public String showAllProducts();
    abstract public String showAllAvailableProducts();
    abstract public String purchaseProduct(String product,int count);
    abstract public void restoreStocks();
}
