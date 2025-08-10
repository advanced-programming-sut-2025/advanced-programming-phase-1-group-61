package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import controllers.PreGameMenuController;
import controllers.ScoreTableController;
import io.github.camera.Main;
import models.AssetManager;
import models.Game;
import models.character.Character;

import java.util.List;

public class ScoreTableView implements Screen {
    private ScoreTableController controller;
    private Stage stage;
    private Skin skin;
    private Table buttonTable;
    private Table usersTable;
    private TextButton leave;

    private SortType currentSort = SortType.MONEY;

    public ScoreTableView(ScoreTableController controller) {
        this.controller = controller;
        stage = new Stage();
        skin = AssetManager.getSkin();
    }

    public ScoreTableController getController() {
        return controller;
    }

    @Override
    public void show() {
        buttonTable = new Table();
        buttonTable.top().pad(10);
        buttonTable.setFillParent(true);
        stage.addActor(buttonTable);

        usersTable = new Table();
        usersTable.top().padTop(50);
        usersTable.setFillParent(true);
        stage.addActor(usersTable);

        // دکمه مرتب‌سازی‌ها
        TextButton sortByQuests = new TextButton("Sort by Quests", skin);
        sortByQuests.addListener(e -> {
            currentSort = SortType.QUESTS;
            updateTable();
            return true;
        });

        TextButton sortByMoney = new TextButton("Sort by Money", skin);
        sortByMoney.addListener(e -> {
            currentSort = SortType.MONEY;
            updateTable();
            return true;
        });

        TextButton sortByLevel = new TextButton("Sort by Level", skin);
        sortByLevel.addListener(e -> {
            currentSort = SortType.LEVEL;
            updateTable();
            return true;
        });

        leave = new TextButton("Leave", skin);
        leave.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameMenu(new PreGameMenuController()));
            }
        });

        buttonTable.add(sortByQuests).pad(5);
        buttonTable.add(sortByMoney).pad(5);
        buttonTable.add(sortByLevel).pad(5);
        buttonTable.row();
        buttonTable.add(leave).pad(5);
        buttonTable.bottom();
        buttonTable.padBottom(50);

        Gdx.input.setInputProcessor(stage);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                controller.refreshRequest();
                updateTable();
            }
        }, 0, 0.5f);
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    private void updateTable() {
        usersTable.clear();

        // هدر جدول
        usersTable.add(new Label("Username", skin)).pad(5);
        usersTable.add(new Label("Quests", skin)).pad(5);
        usersTable.add(new Label("Money", skin)).pad(5);
        usersTable.add(new Label("Level Sum", skin)).pad(5);
        usersTable.row();

        List<UserScore> scores = controller.getAllUsers().stream()
            .map(user -> {
                int quests = 0;
                int money = 0;
                int levelSum = 0;

                for (Game game : controller.getAllGames()) {
                    for (Character c : game.getAllCharacters()) {
                        if (c.getUserId() == user.getId()) {
                            quests += c.getNumberOfQuest();
                            money += c.getMoney();
                            levelSum += c.getSkillLevel();
                        }
                    }
                }

                return new UserScore(user.getUsername(), quests, money, levelSum);
            })
            .filter(us -> us.quests > 0 || us.money > 0 || us.levelSum > 0)
            .sorted((a, b) -> {
                switch (currentSort) {
                    case QUESTS -> {
                        int cmp = Integer.compare(b.quests, a.quests);
                        if (cmp == 0) cmp = Integer.compare(b.money, a.money);
                        if (cmp == 0) cmp = Integer.compare(b.levelSum, a.levelSum);
                        return cmp;
                    }
                    case MONEY -> {
                        int cmp = Integer.compare(b.money, a.money);
                        if (cmp == 0) cmp = Integer.compare(b.quests, a.quests);
                        if (cmp == 0) cmp = Integer.compare(b.levelSum, a.levelSum);
                        return cmp;
                    }
                    case LEVEL -> {
                        int cmp = Integer.compare(b.levelSum, a.levelSum);
                        if (cmp == 0) cmp = Integer.compare(b.quests, a.quests);
                        if (cmp == 0) cmp = Integer.compare(b.money, a.money);
                        return cmp;
                    }
                }
                return 0;
            })
            .toList();

        // ردیف‌های داده
        for (UserScore us : scores) {
            usersTable.add(new Label(us.username, skin)).pad(5);
            usersTable.add(new Label(String.valueOf(us.quests), skin)).pad(5);
            usersTable.add(new Label(String.valueOf(us.money), skin)).pad(5);
            usersTable.add(new Label(String.valueOf(us.levelSum), skin)).pad(5);
            usersTable.row();
        }
    }

    private enum SortType {
        QUESTS,
        MONEY,
        LEVEL
    }

    private static class UserScore {
        String username;
        int quests;
        int money;
        int levelSum;
        UserScore(String username, int quests, int money, int levelSum) {
            this.username = username;
            this.quests = quests;
            this.money = money;
            this.levelSum = levelSum;
        }
    }

    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {}
}
