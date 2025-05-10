package models.character.Skill;

public class Skill {
    int level;
    int experience;
    Skill(int level, int experience) {
        this.level = level;
        this.experience = experience;
    }
    public int getLevel() {
        return level;
    }
    public int getExperience() {
        return experience;
    }
    public void increaseLevel() {
        if(level<4) level++;
    }
    public void addExperience(int amount) {
        experience += amount;
        if(experience>=(100*(level+1)+50)) {
            experience = 0;
            increaseLevel();
        }
    }
}
