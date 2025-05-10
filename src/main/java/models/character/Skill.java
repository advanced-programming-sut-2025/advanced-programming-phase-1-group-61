package models.character;

public class Skill {
    private int miningSkillXP;
    private int forgingSkillXP;
    private int fishingSkillXP;
    private int farmingSkillXP;
    private int miningLVL , foragingLVL , fishingLVL , farmingLVL;

    public Skill() {
        this.miningSkillXP = 0;
        this.forgingSkillXP = 0;
        this.fishingSkillXP = 0;
        this.farmingSkillXP = 0;
        this.miningLVL = 1;
        this.foragingLVL = 1;
        this.fishingLVL = 1;
        this.farmingLVL = 1;
    }

    public int getFarmingSkillXP() {
        return farmingSkillXP;
    }

    public int getFishingSkillXP() {
        return fishingSkillXP;
    }

    public int getForgingSkillXP() {
        return forgingSkillXP;
    }

    public int getMiningSkillXP() {
        return miningSkillXP;
    }

    public void addFarmingSkillXP(int amount){
        this.farmingSkillXP +=amount;
        if(checkLVL(this.farmingSkillXP , this.farmingLVL)){
            this.farmingSkillXP = 0;
            this.farmingLVL++;
        }
    }

    public void addMiningSkillXP(int amount){
        this.miningSkillXP +=amount;
    }

    public void addFishingSkillXP(int amount){
        this.fishingSkillXP += amount;
    }

    public void  addForgingSkillXP(int amount){
        this.forgingSkillXP += amount;
    }

    private boolean checkLVL(int amount , int lvl){
           if(lvl < 4){
               if(amount == (lvl * 100 + 50)){
                   return true;
               }
           }
        return false;
    }
}
