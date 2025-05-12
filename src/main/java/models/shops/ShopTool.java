package models.shops;

import models.enums.ToolType;
import models.tool.Tool;

public class ShopTool {
    private final ToolType tool;
    private final int limit;
    private final int price;
    private final String description;
    private int stock;
    public ShopTool(ToolType tool, int limit, int price, String description) {
        this.tool = tool;
        this.limit = limit;
        this.price = price;
        this.description = description;
        stock=limit;
    }
    public ToolType getTool() {
        return tool;
    }
    public int getLimit() {
        return limit;
    }
    public int getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public void restoreStock(){
        stock=limit;
    }
}
