package models.character.Skill;

public abstract class Skill {
    protected int level;
    protected int experience;
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
        if(experience==(100*(level+1)+50)) {
            if (level < 4) {
                level++;
                experience = 0;
            }
        }
    }
    abstract public void addExperience();
    abstract public String getSkillType();
}
