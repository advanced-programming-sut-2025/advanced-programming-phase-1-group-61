package models.character;

public class Skill {
    private int miningSkill;
    private int forgingSkill;
    private int fishingSkill;
    private int farmingSkill;

    public Skill() {
        this.miningSkill = 0;
        this.forgingSkill = 0;
        this.fishingSkill = 0;
        this.farmingSkill = 0;
    }

    public int getFarmingSkill() {
        return farmingSkill;
    }

    public int getFishingSkill() {
        return fishingSkill;
    }

    public int getForgingSkill() {
        return forgingSkill;
    }

    public int getMiningSkill() {
        return miningSkill;
    }

    public void addFarmingSkill(int amount){
        this.farmingSkill+=amount;
    }

    public void addMiningSkill(int amount){
        this.miningSkill+=amount;
    }

    public void addFishingSkill(int amount){
        this.fishingSkill += amount;
    }

    public void  addForgingSkill(int amount){
        this.forgingSkill += amount;
    }
}
