package controllers.ShopViewsControllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import models.shops.ShopItem;
import models.shops.ShopTool;
import models.shops.ShopToolUpgrades;
import models.shops.ShopTrashcanUpgrades;
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
        });
    }
}
