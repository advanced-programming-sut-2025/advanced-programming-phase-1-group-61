package views.NPCPages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
import controllers.NPCPagesControllers.QuestsPageController;
import io.github.camera.Main;
import models.AssetManager;
import models.NPC.ItemWithCount;
import models.NPC.NPC;
import models.NPC.NPCQuests;

import java.util.ArrayList;

public class QuestsPageView implements Screen {
    private final QuestsPageController controller=new QuestsPageController(this);
    private final NPC npc;
    private final Stage stage;
    private final TextButton backToGame;
    private final TextButton takeQuests;
    private final TextButton doQuests;
    private final TextField item;
    private final TextField count;
    private final TextButton submit;
    private final TextButton back;
    ArrayList<TextButton> questButtons=new ArrayList<>();
    public QuestsPageView(NPC npc) {
        this.npc=npc;
        stage=new Stage(new FitViewport(1920, 1080));
        Gdx.input.setInputProcessor(stage);
        backToGame=new TextButton("BACK", AssetManager.getSkin());
        takeQuests =new TextButton("Take Quests", AssetManager.getSkin());
        doQuests =new TextButton("Do Quests", AssetManager.getSkin());
        item=new TextField("", AssetManager.getSkin());
        item.setMessageText("enter the item for taking the quest");
        count=new TextField("", AssetManager.getSkin());
        submit=new TextButton("SUBMIT", AssetManager.getSkin());
        back=new TextButton("BACK", AssetManager.getSkin());
        NPCQuests quests=npc.getNpcQuests();
        for(int i=0;i<quests.getRequests().size();i++){
            StringBuilder builder=new StringBuilder();
            ItemWithCount item=quests.getRequests().get(i);
            builder.append("delivering ")
                .append(item.getCount())
                .append(" ")
                .append(item.getItem().getDisPlayName());
            TextButton button=new TextButton(builder.toString(), AssetManager.getSkin());
            questButtons.add(button);
            controller.addListeners();
            controller.addListenerForQuestsButtons();
        }
    }
    @Override
    public void show() {
        stage.clear();
        Table table=new Table();
        table.setFillParent(true);
        table.center();
        table.add(backToGame).width(300).height(60).row();
        table.add(takeQuests).width(300).height(60).row();
        table.add(doQuests).width(300).height(60).row();
        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().end();
        stage.act(v);
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
    public TextButton getBackToGame() {
        return backToGame;
    }
    public TextButton getTakeQuests() {
        return takeQuests;
    }
    public void setUpTakeQuestsPage(){
        stage.clear();
        Table table=new Table();
        table.setFillParent(true);
        table.center();
        for (TextButton questButton : questButtons) {
            table.add(questButton).width(300).height(60).row();
        }
        table.add(back).width(300).height(60).row();
        stage.addActor(table);
    }
    public void setUpDoQuestsPage(){
        stage.clear();
        Table table=new Table();
        table.setFillParent(true);
        table.defaults().center().pad(10);
        table.add(item).width(500).height(80).row();
        table.add(count).width(500).height(80).row();
        table.add(submit).width(300).height(60).row();
        stage.addActor(table);
    }
    public TextField getItem() {
        return item;
    }
    public TextField getCount() {
        return count;
    }
    public TextButton getSubmit() {
        return submit;
    }
    public TextButton getBack() {
        return back;
    }
    public ArrayList<TextButton> getQuestButtons() {
        return questButtons;
    }
    public NPC getNpc() {
        return npc;
    }
    public Stage getStage(){
        return stage;
    }
    public TextButton getDoQuests() {
        return doQuests;
    }
}
