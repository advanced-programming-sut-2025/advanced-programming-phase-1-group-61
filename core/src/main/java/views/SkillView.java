package views;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import models.App;
import models.character.Skill;

public class SkillView extends Dialog {

    public SkillView(Skin skin) {
        super("Skills", skin);
        Skill skill = App.getCurrentGame().getCurrentCharacter().getSkill();

        Table content = getContentTable();
        content.pad(50);
        content.defaults().pad(25);

        content.add(createSkillRow("Farming", skill.getFarmingSkillXP(), skill.getFarmingLVL())).row();
        content.add(createSkillRow("Mining", skill.getMiningSkillXP(), skill.getMiningLVL())).row();
        content.add(createSkillRow("Fishing", skill.getFishingSkillXP(), skill.getFishingLVL())).row();
        content.add(createSkillRow("Foraging", skill.getForgingSkillXP(), skill.getForagingLVL())).row();

        button("Close");
    }

    private Table createSkillRow(String skillName, int xp, int level) {
        Table row = new Table();
        row.align(Align.left);

        Label nameLabel = new Label(skillName + " (Lv " + level + ")", getSkin());
        ProgressBar progressBar = new ProgressBar(0, level * 100 + 50, 1, false, getSkin());
        progressBar.setValue(xp);
        progressBar.setAnimateDuration(0.3f);
        progressBar.setWidth(400);

        TooltipManager tooltipManager = TooltipManager.getInstance();
        tooltipManager.initialTime = 0.2f;
        Tooltip<Label> tooltip = new Tooltip<>(new Label(getSkillDescription(skillName), getSkin()));
        nameLabel.addListener(tooltip);

        row.add(nameLabel).width(240).left();
        row.add(progressBar).width(400).padLeft(10);

        return row;
    }
    private String getSkillDescription(String skillName) {
        return switch (skillName.toLowerCase()) {
            case "farming" -> "Farming lets you grow crops and raise animals more efficiently.";
            case "mining" -> "Mining increases your efficiency at breaking rocks and finding ores.";
            case "fishing" -> "Fishing improves your ability to catch fish in rivers and oceans.";
            case "foraging" -> "Foraging increases your ability to gather wild plants and resources.";
            default -> "No description available.";
        };
    }


}
