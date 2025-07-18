package controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.camera.Main;
import models.*;
import models.shops.BlackSmith;
import models.shops.Carpenter;
import models.shops.FishShop;
import views.LoginMenu;
import views.MainMenu;
import views.RegisterMenu;
import views.SettingsMenu;
import views.ShopViews.BlackSmithView;
import views.ShopViews.CarpenterView;
import views.ShopViews.FishShopView;


public class MainMenuController {
    private MainMenu view;

    public void setView(MainMenu view) {
        this.view = view;
    }

    public Result register(){
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new RegisterMenu(new RegisterMenuController()));
        return new Result(true , "successfully logged out");
    }
    public Result logIn(){
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new LoginMenu(new LoginMenuController()));
        return new Result(true , "invalid menu");
    }
    public void addListeners(){
        view.getLoginButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                logIn();
            }
        });

        view.getRegisterButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                register();
            }
        });
        view.getSettings().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                Game game= App.getCurrentGame();
                if(game==null) {
                    AlertGenerator.showAlert("error","Please login first",view.getStage());
                    return;
                }
                if(game.getCurrentCharacter()==null){
                    AlertGenerator.showAlert("error","please login first",view.getStage());
                    return;
                }
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new SettingsMenu());
            }
        });
        view.getExitButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                Main.getMain().getScreen().dispose();
                Gdx.app.exit();
            }
        });
        view.getTestShop().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new FishShopView(new FishShop("...",0,0)));
            }
        });
    }
}
