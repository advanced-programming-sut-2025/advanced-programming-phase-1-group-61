package models.character.Skill;

public class FarmingSkill extends Skill {
    public FarmingSkill(int level, int experience) {
        super(level, experience);
    }

    @Override
    public void addExperience() {
        this.experience += 5;
        super.increaseLevel();
    }

    @Override
    public String getSkillType() {
        return "FarmingSkill";
    }
}
