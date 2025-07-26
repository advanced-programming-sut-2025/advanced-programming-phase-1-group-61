package controllers.ShopViewsControllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import views.ShopViews.FishShopView;

public class FishShopViewController {
    private final FishShopView view;
    public FishShopViewController(FishShopView view) {
        this.view = view;
    }
    public void handleTypeSelectBox(){
        view.getProductTypeSelectBox().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                String selected = view.getProductTypeSelectBox().getSelected();
                if(selected.equals("FishingPole Upgrades")){
                    view.setSelectFishingPole(true);
                    view.setSelectRecipe(false);
                }
                if(selected.equals("Recipes")){
                    view.setSelectRecipe(true);
                    view.setSelectFishingPole(false);
                }
                view.setUpUI();
            }
        });
    }
}
