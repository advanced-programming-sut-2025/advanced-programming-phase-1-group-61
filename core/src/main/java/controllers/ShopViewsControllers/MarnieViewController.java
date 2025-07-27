package controllers.ShopViewsControllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import views.ShopViews.MarnieView;

public class MarnieViewController {
    private final MarnieView view;
    public MarnieViewController(MarnieView view) {
        this.view = view;
    }
    public void handleTypeSelectBox(){
        view.getProductTypeSelectBox().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                String selected = view.getProductTypeSelectBox().getSelected();
                if(selected.equals("Permanent Items")){
                    view.setSelectItems(true);
                    view.setSelectAnimals(false);
                    view.setSelectTools(false);
                }
                if(selected.equals("Tools")){
                    view.setSelectItems(false);
                    view.setSelectAnimals(false);
                    view.setSelectTools(true);
                }
                if(selected.equals("Animals")){
                    view.setSelectItems(false);
                    view.setSelectAnimals(true);
                    view.setSelectTools(false);
                }
                view.setUpUI();
            }
        });
    }
}
