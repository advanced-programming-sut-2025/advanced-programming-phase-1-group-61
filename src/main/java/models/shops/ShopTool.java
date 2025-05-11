package models.shops;

import models.tool.Tool;

public class ShopTool {
    private final Tool tool;
    private final int limit;
    private final int price;
    private final String description;
    public ShopTool(Tool tool, int limit, int price, String description) {
        this.tool = tool;
        this.limit = limit;
        this.price = price;
        this.description = description;
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
    public String getDescription() {
        return description;
    }
}
