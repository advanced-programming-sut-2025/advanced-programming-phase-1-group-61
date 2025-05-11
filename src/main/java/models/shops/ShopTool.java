package models.shops;

import models.tool.Tool;

public class ShopTool {
    private final Tool tool;
    private final int limit;
    private final int price;
    public ShopTool(Tool tool, int limit, int price) {
        this.tool = tool;
        this.limit = limit;
        this.price = price;
    }
    public Tool getTool() {
        return tool;
    }
    public int getLimit() {
        return limit;
    }
    public int getPrice() {
        return price;
    }
}
