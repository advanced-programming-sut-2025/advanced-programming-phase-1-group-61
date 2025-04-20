package models.date;

import models.enums.DaysOfTheWeek;
import models.enums.Season;

public class Date {
    private DaysOfTheWeek day;
    private Season season;
    private int dayCounter = 0;
    private int hour ;
    public Date(){
        this.hour = 9;
        day=DaysOfTheWeek.Sunday;
        season=Season.Spring;
    }
    public void increaseTime(int increase){
        hour+=increase;
        if(hour>24){
            changeDay();
            hour = 0;
        }
    }
    public void setHour(int time){
        this.hour=time;
    }
    public int getHour(){
        return hour;
    }

    public void changeDay(){
        dayCounter++;
        if(dayCounter % 7 ==0){
            this.day = DaysOfTheWeek.Sunday;
        } else if (dayCounter % 7 == 1) {
            this.day = DaysOfTheWeek.Monday;
        } else if (dayCounter % 7 == 2) {
            this.day = DaysOfTheWeek.Tuesday;
        } else if (dayCounter % 7 == 3) {
            this.day = DaysOfTheWeek.Wednesday;
        } else if (dayCounter % 7 == 4) {
            this.day = DaysOfTheWeek.Thursday;
        } else if (dayCounter % 7 == 5) {
            this.day = DaysOfTheWeek.Friday;
        } else if (dayCounter % 7 == 6) {
            this.day = DaysOfTheWeek.Saturday;
        }

        if((dayCounter / 25) % 4 == 0){
            changeSeason(Season.Spring);
        } else if ((dayCounter / 25) % 4 == 1) {
            changeSeason(Season.Summer);
        } else if ((dayCounter / 25) % 4 == 2) {
            changeSeason(Season.Fall);
        } else if ((dayCounter / 25) % 4 == 3) {
            changeSeason(Season.Winter);
        }
    }


    public void changeSeason(Season newSeason){
        this.season=newSeason;
    }

    public Season getSeason(){
        return season;
    }

    public DaysOfTheWeek getDay() {
        return day;
    }

}
