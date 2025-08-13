package network;

import models.enums.ItemType;

public class GiftSent {
    private ItemType gift;
    private int senderUserId , gameId , receiveUserId;

    public GiftSent() {
    }

    public GiftSent(ItemType gift, int senderUserId, int gameId, int receiveUserId) {
        this.gift = gift;
        this.senderUserId = senderUserId;
        this.gameId = gameId;
        this.receiveUserId = receiveUserId;
    }

    public ItemType getGift() {
        return gift;
    }

    public int getSenderUserId() {
        return senderUserId;
    }

    public int getGameId() {
        return gameId;
    }

    public int getReceiveUserId() {
        return receiveUserId;
    }
}
