package controllers.ShopViewsControllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import models.AlertGenerator;
import models.shops.BlackSmith;
import views.ShopViews.BlackSmithView;

public class BlackSmithViewController {
    private final BlackSmithView view;
    public BlackSmithViewController(BlackSmithView view) {
        this.view = view;
    }
    public void handleTypeSelectBox(){
        view.getProductTypeSelectBox().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                String selectedValue = view.getProductTypeSelectBox().getSelected();
                if(selectedValue.equals("Items")){
                    view.setSelectItems(true);
                    view.setSelectTools(false);
                    view.setSelectTrashcan(false);
                }
                if(selectedValue.equals("Trashcan Upgrades")){
                    view.setSelectItems(false);
                    view.setSelectTools(false);
                    view.setSelectTrashcan(true);
                }
                if(selectedValue.equals("Tool Upgrades")){
                    view.setSelectItems(false);
                    view.setSelectTools(true);
                    view.setSelectTrashcan(false);
                }
                view.setUpUI();
            }
        });
    }
}
