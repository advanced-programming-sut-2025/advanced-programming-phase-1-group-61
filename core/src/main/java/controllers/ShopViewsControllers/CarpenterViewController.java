package controllers.ShopViewsControllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import views.ShopViews.CarpenterView;

public class CarpenterViewController {
    private final CarpenterView view;
    public CarpenterViewController(CarpenterView view) {
        this.view = view;
    }
    public void handleTypeSelectBox(){
        view.getProductTypeSelectBox().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                String selected = view.getProductTypeSelectBox().getSelected();
                if(selected.equals("Permanent Items")){
                    view.setSelectPermanentItem(true);
                    view.setSelectFarmBuilding(false);
                }
                if(selected.equals("Farm Buildings")){
                    view.setSelectFarmBuilding(true);
                    view.setSelectPermanentItem(false);
                }
                view.setUpUI();
            }
        });
    }
}
