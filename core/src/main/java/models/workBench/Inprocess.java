package models.workBench;

import models.enums.ItemType;

public class Inprocess {
    private  int timeneeded;
    private  int startday;
    private  int starthour;
    private  ItemType type;
    public Inprocess( int Timeneeded,int startday, int starthour, ItemType type){

        this.timeneeded = Timeneeded;
        this.startday = startday;
        this.starthour = starthour;
        this.type = type;
    }

    public Inprocess() {
    }

    public ItemType isready(int day, int hour){
        if (day>startday){
            return this.type;
        }
        if(timeneeded <= hour - starthour){
            return this.type;
        }
        System.out.println(timeneeded);
        return null;
    }

    public float getProgressPercent(int currentDay, int currentHour) {
        int elapsedHours = (currentDay - startday) * 24 + (currentHour - starthour);
        return Math.min(1f, elapsedHours / (float) timeneeded);
    }
    public ItemType getType(){
        return type;
    }


}
