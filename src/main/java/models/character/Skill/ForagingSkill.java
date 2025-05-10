package models.character.Skill;

public class ForagingSkill extends Skill {
    public ForagingSkill(int level, int experience) {
        super(level, experience);
    }

    @Override
    public void addExperience() {
        this.experience += 10;
        super.increaseLevel();
    }

    @Override
    public String getSkillType() {
        return "ForagingSkill";
    }
}
