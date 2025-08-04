package models.interactions;

import models.App;
import models.character.Character;
import models.enums.ItemType;
import java.util.*;

public class Iteractions {
    private final List<Interact> Interacts = new ArrayList<>();
    private List<Interact> newInteracts = new ArrayList<>();
    private final Map<Integer, Integer> friendships = new HashMap<>();
    private final Integer owner;

    public Iteractions(int userId) {
        this.owner = userId;
    }

    public void addnew(Interact interact) {
        newInteracts.add(interact);
    }

    public void addfriendship(Integer friend, Integer amount) {
        if (friendships.containsKey(friend)) {
            amount += friendships.get(friend);
            friendships.put(friend, amount);
        } else {
            friendships.put(friend, 0);
        }
    }

    public boolean addInteract(String type,String kind, Integer friend, String value, ItemType item1, Integer amount1,
                               ItemType item2, Integer amount2) {
        Character Friend = App.getCurrentGame().getCharacterByID(friend);
        Character Owner = App.getCurrentGame().getCurrentCharacter();
        int id = this.Interacts.size()+1;
        switch (type.toLowerCase()) {
            case "massage": {
                Interact interact = new InteractTALK("massage", this.owner, friend, value,id);
                Interacts.add(interact);
                Friend.getIteractions().addnew(interact);
                addfriendship(friend, 20);
                Friend.getIteractions().addfriendship(this.owner, 20);
                return true;
            }
            case "gift": {
                if (Owner.getInventory().getCountOfItem(item1) >= amount1) {
                    Owner.getInventory().removeItem(item1, amount1);
                    App.getCurrentGame().getCharacterByID(friend).getInventory().addItem(item1, amount1);
                    Interact interact =new InteractGIFT("gift", this.owner, friend,item1,amount1, value,id);
                    Interacts.add(interact);
                    Friend.getIteractions().addnew(interact);
                    addfriendship(friend, 0);
                    Friend.getIteractions().addfriendship(this.owner, 0);
                    return true;
                }
                return false;
            }
            case "hug": {
                if (Math.abs(Friend.getX() - Owner.getX()) > 1 || Math.abs(Friend.getY() - Owner.getY()) > 1) {
                    return false;
                }
                addfriendship(friend, 60);
                Friend.getIteractions().addfriendship(this.owner, 60);
                Interact interact = new InteractHUG("hug", this.owner, friend, value,id);
                this.Interacts.add(interact);
                Friend.getIteractions().addnew(interact);
            }
            case "flower": {
                if (Math.abs(Friend.getX() - Owner.getX()) > 1 || Math.abs(Friend.getY() - Owner.getY()) > 1) {
                    return false;
                }
                if(Owner.getInventory().getCountOfItem(item1) < 1) { return false; }
                Owner.getInventory().removeItem(item1, 1);
                if(getfrienshiplevel(friend)==2){
                    Friend.getInventory().addItem(item1, 100);
                    Friend.getIteractions().addfriendship(this.owner, 100);
                }
                Interact interact = new InteractFLOWER("flower", this.owner, friend,item1, value,id);
                this.Interacts.add(interact);
                Friend.getIteractions().addnew(interact);
            }
            case "marrige": {
                if (Owner.getInventory().getCountOfItem(item1) >= 1) {
                    if(getfrienshiplevel(friend)!=3){
                        return false;
                    }
                    Owner.getInventory().removeItem(item1, 1);
                    Interact interact =new InteractMARRIGE("marrige", this.owner, friend,item1, value,id);
                    Interacts.add(interact);
                    Friend.getIteractions().addnew(interact);
                    return true;
                }
                return false;
            }
            case "trade":{
                Friend.getInventory().addItem(item1, 0);
                Friend.getIteractions().addfriendship(this.owner, 0);
                if(Objects.equals(kind, "offer")){
                    if(Owner.getInventory().getCountOfItem(item1) >= amount1){
                        Owner.getInventory().removeItem(item1, amount1);
                    }else return false;

                }else if(Objects.equals(kind, "request")){
                    if(item2==null){
                        if(Owner.getMoney()<amount2){ return false; }
                        Owner.spend(amount2);
                    }else if(Owner.getInventory().getCountOfItem(item2) >= amount2){
                        Owner.getInventory().removeItem(item2, amount2);
                    }else return false;
                }
                Interact interact =new InteractTRADE("trade",kind, this.owner, friend,item1,amount1
                        ,item2,amount2, value,id);
                Interacts.add(interact);
                Friend.getIteractions().addnew(interact);
                return true;
            } default: return false;
        }
    }

    public List<Interact> newInteracts() {
        Interacts.addAll(newInteracts);
        List<Interact> news = newInteracts;
        newInteracts = new ArrayList<>();
        return news;
    }

    private Integer getfrienshiplevel(Integer friend) {
        if (friendships.containsKey(friend)) {
            return ((friendships.get(friend) / 100) - 1);
        }
        return -1;
    }

    public List<Interact> talkhistory(Integer friend) {
        List<Interact> talkinteracts = new ArrayList<>();
        for (Interact interact : Interacts) {
            if (interact.getType().equalsIgnoreCase("talk")) {
                talkinteracts.add(interact);
            }
        }
        return talkinteracts;
    }

    public List<Interact> giftlist() {
        List<Interact> gifts = new ArrayList<>();
        for (Interact interact : Interacts) {
            if (interact.getType().equalsIgnoreCase("gift") &&
                    interact.getFriend().equals(this.owner)) {
                gifts.add(interact);
            }
        }
        return gifts;
    }

    public List<Interact> gifthistory(Integer friend) {
        List<Interact> gifts = new ArrayList<>();
        for (Interact interact : Interacts) {
            if (interact.getType().equalsIgnoreCase("gift")) {
                gifts.add(interact);
            }
        }
        return gifts;
    }

    public boolean giftrate(Integer gift, int rate) {
        for (Interact interact : Interacts) {
            if (interact.getID().equals(gift)) {
                int amount = (friendships.get(interact.getFriend()) * rate) * 30 + 15;
                friendships.put(interact.getFriend(), amount);
                App.getCurrentGame().getCharacterByID(interact.getFriend()).getIteractions().friendships.
                        put(this.owner, amount);
                return true;
            }
        }
        return false;
    }

    public boolean marriagerate(boolean answer) {
        if (answer) {
            for (Interact interact : Interacts) {
                if (interact.getType().equalsIgnoreCase("marriage")) {
                    App.getCurrentGame().getCharacterByID(owner).getInventory()
                            .addItem(((InteractMARRIGE) interact).getItem(),1 );
                    App.getCurrentGame().getCharacterByID(owner).setPartner(interact.getOwner());
                    App.getCurrentGame().getCharacterByID(interact.getFriend()).setPartner(this.owner);
                }
            }
        }
        return answer;
    }
    public List<Interact> tradelist(){
        List<Interact> trades = new ArrayList<>();
        for(Interact interact : Interacts){
            if(interact.getType().equalsIgnoreCase("trade")&&!((InteractTRADE) interact).isHandeled()&&
            !interact.getOwner().equals(owner)){
                trades.add(interact);
            }
        }
        return trades;
    }

    public boolean traderesponse(boolean answer,int id) {
        for (Interact interact : Interacts) {
            if(interact.getID().equals(id)){
                if(!answer){
                    ((InteractTRADE) interact).setHandeled(true);
                    return true;
                }else {
                    InteractTRADE trade = ((InteractTRADE) interact);
                    Character Owner = App.getCurrentGame().getCharacterByID(owner);
                    Character Friend = App.getCurrentGame().getCharacterByID(trade.getOwner());
                    if(trade.getKind().equalsIgnoreCase("request")){
                        if(Owner.getInventory().getCountOfItem(trade.getItem1())< trade.getAmount1()) return false;
                        trade.setHandeled(true);
                        Owner.getInventory().removeItem(trade.getItem1(), trade.getAmount1());
                        if(trade.getItem2()==null){
                            Owner.spend(-trade.getAmount2());
                        }else{
                            Owner.getInventory().addItem(trade.getItem2(),trade.getAmount2());
                        }
                        trade.setHandeled(true);
                        return true;
                    }else{
                        if(trade.getItem2()==null){
                            if(Owner.getMoney()< trade.getAmount2()) return false;
                            Owner.spend(trade.getAmount2());
                            Owner.getInventory().addItem(trade.getItem1(),trade.getAmount1());
                        }else{
                            if(Owner.getInventory().getCountOfItem(trade.getItem2())< trade.getAmount2()) return false;
                            Owner.getInventory().addItem(trade.getItem1(),trade.getAmount1());
                            Owner.getInventory().removeItem(trade.getItem2(),trade.getAmount2());
                            trade.setHandeled(true);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public List<Interact> tradehistory(Integer friend) {
        List<Interact> trades = new ArrayList<>();
        for(Interact interact : Interacts){
            if(interact.getType().equalsIgnoreCase("trade")&&((InteractTRADE) interact).isHandeled()){
                trades.add(interact);
            }
        }
        return trades;
    }


}
