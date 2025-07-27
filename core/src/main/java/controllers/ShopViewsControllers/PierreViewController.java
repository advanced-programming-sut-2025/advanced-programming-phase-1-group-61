package controllers.ShopViewsControllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import views.ShopViews.PierreView;

public class PierreViewController {
    private final PierreView view;
    public PierreViewController(PierreView view) {
        this.view = view;
    }
    public void handleTypeSelectBox(){
        view.getProductTypeSelectBox().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                String selected=view.getProductTypeSelectBox().getSelected();
                if(selected.equals("Year Round Items")){
                    view.setSelectYearRoundItems(true);
                    view.setSelectSpringItems(false);
                    view.setSelectSummerItems(false);
                    view.setSelectFallItems(false);
                    view.setSelectBackpacks(false);
                    view.setSelectRecipes(false);
                }
                if(selected.equals("Spring Items")){
                    view.setSelectYearRoundItems(false);
                    view.setSelectSpringItems(true);
                    view.setSelectSummerItems(false);
                    view.setSelectFallItems(false);
                    view.setSelectBackpacks(false);
                    view.setSelectRecipes(false);
                }
                if(selected.equals("Summer Items")){
                    view.setSelectYearRoundItems(false);
                    view.setSelectSpringItems(false);
                    view.setSelectSummerItems(true);
                    view.setSelectFallItems(false);
                    view.setSelectBackpacks(false);
                    view.setSelectRecipes(false);
                }
                if(selected.equals("Fall Items")){
                    view.setSelectYearRoundItems(false);
                    view.setSelectSpringItems(false);
                    view.setSelectSummerItems(false);
                    view.setSelectFallItems(true);
                    view.setSelectBackpacks(false);
                    view.setSelectRecipes(false);
                }
                if(selected.equals("Backpacks")){
                    view.setSelectYearRoundItems(false);
                    view.setSelectSpringItems(false);
                    view.setSelectSummerItems(false);
                    view.setSelectFallItems(false);
                    view.setSelectBackpacks(true);
                    view.setSelectRecipes(false);
                }
                if(selected.equals("Recipes")){
                    view.setSelectYearRoundItems(false);
                    view.setSelectSpringItems(false);
                    view.setSelectSummerItems(false);
                    view.setSelectFallItems(false);
                    view.setSelectBackpacks(false);
                    view.setSelectRecipes(true);
                }
                view.setUpUI();
            }
        });
    }
}
