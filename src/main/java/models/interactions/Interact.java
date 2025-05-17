package models.interactions;

import models.App;

public class Interact {
    private String type;
    private Integer friend;
    private Integer owner;
    private String value;
    private Integer ID;
    public Interact(String type,Integer owner, Integer friend, String value,int id) {
        this.type = type;
        this.friend = friend;
        this.owner= owner;
        this.value = value;
        this.ID = id;
    }


    public String getType() {
        return type;
    }

    public Integer getFriend() {
        return friend;
    }

    public Integer getOwner() {
        return owner;
    }

    public String getValue() {
        return value;
    }

    public Integer getID() {
        return ID;
    }
}
