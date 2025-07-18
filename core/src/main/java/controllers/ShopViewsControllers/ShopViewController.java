package controllers.ShopViewsControllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import models.shops.*;
import views.ShopViews.ShopView;

public class ShopViewController {
    private final ShopView view;
    public ShopViewController(ShopView view) {
        this.view = view;
    }
    public void addHoverListenerForItems(ImageButton button, ShopItem item) {
        button.addListener(new InputListener(){
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                view.getName().setText("Name: "+item.getItem().getDisPlayName());
                view.getPrice().setText("Price: "+item.getPrice());
                if(item.getStock()>(int)1e9){
                    view.getStock().setText("Stock: "+"UNLIMITED");
                } else {
                    view.getStock().setText("Stock: "+item.getStock());
                }
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                view.getName().setText("");
                view.getPrice().setText("");
                view.getStock().setText("");
            }
        });
    }
    public void addHoverListenerForShopTools(ImageButton button, ShopToolUpgrades tool){
        button.addListener(new InputListener(){
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                view.getName().setText("Name: "+tool.getUpgradeName()+" Tool");
                view.getPrice().setText("Price: "+tool.getPrice());
                if(tool.getStock()>(int)1e9){
                    view.getStock().setText("Stock: "+"UNLIMITED");
                } else{
                    view.getStock().setText("Stock: "+tool.getStock());
                }
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                view.getName().setText("");
                view.getPrice().setText("");
                view.getStock().setText("");
            }
        });
    }
    public void addHoverListenerForShopTrashcans(ImageButton button, ShopTrashcanUpgrades trashcan) {
        button.addListener(new InputListener(){
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                view.getName().setText("Name: "+trashcan.getTrashcanType().getDisplayName()+" Trashcan");
                view.getPrice().setText("Price: "+trashcan.getPrice());
                if(trashcan.getStock()>(int)1e9){
                    view.getStock().setText("Stock: "+"UNLIMITED");
                } else{
                    view.getStock().setText("Stock: "+trashcan.getStock());
                }
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                view.getName().setText("");
                view.getPrice().setText("");
                view.getStock().setText("");
            }
        });
    }
    public void addHoverListenerForShopCages(ImageButton button, ShopCages cage){
        button.addListener(new InputListener(){
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                view.getName().setText("Name: "+cage.getCageType().getDisplayName()+" Cage");
                view.getPrice().setText("Price: "+cage.getPrice());
                if(cage.getStock()>(int)1e9){
                    view.getStock().setText("Stock: "+"UNLIMITED");
                } else {
                    view.getStock().setText("Stock: "+cage.getStock());
                }
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                view.getName().setText("");
                view.getPrice().setText("");
                view.getStock().setText("");
            }
        });
    }
    public void addHoverListenerForShopFishingPoleUpgrades(ImageButton button, ShopFishingPoleUpgrades pole){
        button.addListener(new InputListener(){
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                view.getName().setText("Name: "+pole.getUpgradeName()+" Fishing Pole");
                view.getPrice().setText("Price: "+pole.getPrice());
                if(pole.getStock()>(int)1e9){
                    view.getStock().setText("Stock: "+"UNLIMITED");
                } else{
                    view.getStock().setText("Stock: "+pole.getStock());
                }
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                view.getName().setText("");
                view.getPrice().setText("");
                view.getStock().setText("");
            }
        });
    }
    public void addHoverListenerForShopRecipes(ImageButton button, ShopRecipes recipe){
        button.addListener(new InputListener(){
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                view.getName().setText("Name: "+recipe.getRecipe().name());
                view.getPrice().setText("Price: "+recipe.getPrice());
                if(recipe.getStock()>(int)1e9){
                    view.getStock().setText("Stock: "+"UNLIMITED");
                } else{
                    view.getStock().setText("Stock: "+recipe.getStock());
                }
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                view.getName().setText("");
                view.getPrice().setText("");
                view.getStock().setText("");
            }
        });
    }
}
