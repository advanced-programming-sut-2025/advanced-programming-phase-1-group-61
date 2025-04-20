package models.character;

import models.Item;

public class Request {
    private Item reward;
    private Item request;

    public Request(Item request, Item reward) {
        this.request = request;
        this.reward = reward;
    }
}
