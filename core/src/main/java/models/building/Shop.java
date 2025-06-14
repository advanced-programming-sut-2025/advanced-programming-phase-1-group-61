package models.building;


public class Shop extends Building {
    protected String owner;
    public Shop( String name , int X, int Y) {
        super( name, X, Y);
    }
    public String getOwnerName() {
        return owner;
    }
    public String showAllProducts(){
        return "salam shop";
    }
    public String showAllAvailableProducts(){
        return "hello shop";
    }
    public String purchaseProduct(String product,int count){
        return "by shop";
    }
    public void restoreStocks(){
        return;
    }
    public int getOpenHour(){
        return 0;
    }
    public int getCloseHour(){
        return 0;
    }
}
