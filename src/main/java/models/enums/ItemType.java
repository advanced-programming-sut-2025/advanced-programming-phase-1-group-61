package models.enums;

public enum ItemType {
    Wood(10),
    Stone(20),
    Iron(30),
    Diamond(1000);

    private int price ;

    ItemType(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
