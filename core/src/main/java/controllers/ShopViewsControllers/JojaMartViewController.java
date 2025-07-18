package controllers.ShopViewsControllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import views.ShopViews.JojaMartView;

public class JojaMartViewController {
    private final JojaMartView view;
    public JojaMartViewController(JojaMartView view) {
        this.view = view;
    }
    public void handleTypeSelectBox(){
        view.getProductTypeSelectBox().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                String selected = view.getProductTypeSelectBox().getSelected();
                if(selected.equals("Permanent Items")){
                    view.setSelectPermanentItems(true);
                    view.setSelectSpringItems(false);
                    view.setSelectSummerItems(false);
                    view.setSelectFallItems(false);
                    view.setSelectWinterItems(false);
                }
                if(selected.equals("Spring Items")){
                    view.setSelectPermanentItems(false);
                    view.setSelectSpringItems(true);
                    view.setSelectSummerItems(false);
                    view.setSelectFallItems(false);
                    view.setSelectWinterItems(false);
                }
                if(selected.equals("Summer Items")){
                    view.setSelectPermanentItems(false);
                    view.setSelectSpringItems(false);
                    view.setSelectSummerItems(true);
                    view.setSelectFallItems(false);
                    view.setSelectWinterItems(false);
                }
                if(selected.equals("Fall Items")){
                    view.setSelectPermanentItems(false);
                    view.setSelectSpringItems(false);
                    view.setSelectSummerItems(false);
                    view.setSelectFallItems(true);
                    view.setSelectWinterItems(false);
                }
                if(selected.equals("Winter Items")){
                    view.setSelectPermanentItems(false);
                    view.setSelectSpringItems(false);
                    view.setSelectSummerItems(false);
                    view.setSelectFallItems(false);
                    view.setSelectWinterItems(true);
                }
                view.setUpUI();
            }
        });
    }
}
