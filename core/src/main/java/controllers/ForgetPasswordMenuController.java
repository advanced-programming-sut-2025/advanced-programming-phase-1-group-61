package controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.camera.Main;
import models.AlertGenerator;
import models.AssetManager;
import models.User;
import views.ForgetPasswordMenu;
import views.LoginMenu;

public class ForgetPasswordMenuController {
    private ForgetPasswordMenu view;
    public ForgetPasswordMenuController(ForgetPasswordMenu view) {
        this.view = view;
    }
    public void addListeners(){
        view.submitEmail().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                String username=view.getUsername().getText();
                String email=view.getEmail().getText();
                User user=User.getUserByUsername(username);
                if(user==null){
                    AlertGenerator.showAlert("error!","User not found!",view.getStage());
                    return;
                }
                if(!user.getEmail().equals(email)){
                    AlertGenerator.showAlert("error!","Email address does not match!",view.getStage());
                    return;
                }
                AlertGenerator.showAlert("","your password: "+user.getPassword(),view.getStage());
            }
        });
        view.submitSecurityQuestion().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                String username=view.getUsername().getText();
                User user=User.getUserByUsername(username);
                if(user==null){
                    AlertGenerator.showAlert("error!","User not found!",view.getStage());
                    return;
                }
                AlertGenerator.showAlert("","your password: "+user.getPassword(),view.getStage());
            }
        });
        view.getBack().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new LoginMenu(new LoginMenuController()));
            }
        });
        view.getGetPasswordByEmail().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                view.setUpEmailUI();
            }
        });
        view.getGetPasswordBySecurityQuestion().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                view.setUpSecurityQuestionUI();
            }
        });
        view.getCancel().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                view.show();
            }
        });
    }
}
