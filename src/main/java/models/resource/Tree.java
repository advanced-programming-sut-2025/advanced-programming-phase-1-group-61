package models.resource;

import models.enums.TreeType;

public class Tree extends Resource{
    private TreeType type;

    public Tree(TreeType type) {
        this.type = type;
    }
}
