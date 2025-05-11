package models.character;

import models.App;

public class Skill {
    private int miningSkillXP;
    private int forgingSkillXP;
    private int fishingSkillXP;
    private int farmingSkillXP;
    private int miningLVL , foragingLVL , fishingLVL , farmingLVL;
    private Character character;

    public Skill() {
        this.miningSkillXP = 0;
        this.forgingSkillXP = 0;
        this.fishingSkillXP = 0;
        this.farmingSkillXP = 0;
        this.miningLVL = 1;
        this.foragingLVL = 1;
        this.fishingLVL = 1;
        this.farmingLVL = 1;
        this.character= App.getCurrentGame().getCurrentCharacter();
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

    public int getMiningLVL() {
        Buff buff=character.getBuff();
        if(buff.isMiningBuff()) return 4;
        return miningLVL;
    }

    public int getForagingLVL() {
        Buff buff=character.getBuff();
        if(buff.isForagingBuff()) return 4;
        return foragingLVL;
    }
    public int getFarmingLVL() {
        Buff buff=character.getBuff();
        if(buff.isFarmingBuff()) return 4;
        return farmingLVL;
    }
    public int getFishingLVL(){
        Buff buff=character.getBuff();
        if(buff.isFishingBuff()) return 4;
        return fishingLVL;
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
        if(checkLVL(this.miningSkillXP , this.miningLVL)){
            this.miningSkillXP =0;
            this.miningLVL++;
        }
    }

    public void addFishingSkillXP(int amount){
        this.fishingSkillXP += amount;
        if(checkLVL(this.fishingSkillXP , this.fishingLVL)){
            this.fishingSkillXP=0;
            this.fishingLVL++;
        }
    }

    public void  addForgingSkillXP(int amount){
        this.forgingSkillXP += amount;
        if(checkLVL(this.forgingSkillXP , this.foragingLVL)){
            this.foragingLVL++;
            this.forgingSkillXP = 0;
        }
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