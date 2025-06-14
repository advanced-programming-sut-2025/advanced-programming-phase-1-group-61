package models.workBench;

import models.enums.ItemType;

public class Inprocess {
    private final int timeneeded;
    private final int startday;
    private final int starthour;
    private final ItemType type;
    public Inprocess( int Timeneeded,int startday, int starthour, ItemType type){
        this.timeneeded = Timeneeded;
        this.startday = startday;
        this.starthour = starthour;
        this.type = type;
    }

    public ItemType isready(int day, int hour){
        if(timeneeded<0){
            if (day>startday){
                return this.type;
            }
        }
        if(timeneeded<=(24*(day-startday)+(hour-starthour))){
            return this.type;
        }
        return null;
    }


}
