package views.ShopViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import controllers.ShopViewsControllers.FishShopViewController;
import models.App;
import models.shops.FishShop;
import models.shops.ShopFishingPoleUpgrades;
import models.shops.ShopRecipes;

import java.util.ArrayList;

public class FishShopView extends ShopView {
    private final FishShopViewController secondController=new FishShopViewController(this);
    private final FishShop shop;
    private final ImageButton[] fishingPoleUpgrades;
    private final ImageButton[] recipes;
    private final ArrayList<ShopFishingPoleUpgrades> shopFishingPoleUpgrades;
    private final ArrayList<ShopRecipes> shopRecipes;
    private boolean selectFishingPole=true;
    private boolean selectRecipe=false;
    public FishShopView(FishShop shop) {
        super();
        this.shop=shop;
        productTypeSelectBox.setItems("FishingPole Upgrades", "Recipes");
        shopFishingPoleUpgrades=shop.getFishingPoles();
        shopRecipes=shop.getShopRecipes();
        fishingPoleUpgrades=new ImageButton[shopFishingPoleUpgrades.size()];
        recipes=new ImageButton[shopRecipes.size()];
    }
    @Override
    public void show() {
        super.show();
        secondController.handleTypeSelectBox();
        for(int i=0; i<shopFishingPoleUpgrades.size(); i++){
            App.Extract(i,fishingPoleUpgrades);
            controller.addHoverListenerForShopFishingPoleUpgrades(fishingPoleUpgrades[i],shopFishingPoleUpgrades.get(i));
        }
        for(int i=0; i<shopRecipes.size(); i++){
            App.Extract(i,recipes);
            controller.addHoverListenerForShopRecipes(recipes[i],shopRecipes.get(i));
        }
        setUpUI();
    }
    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        if(selectFishingPole){
            for(int i=0; i<shopFishingPoleUpgrades.size(); i++){
                float width=64;
                float height=64;
                ImageButton button=fishingPoleUpgrades[i];
                if(!allProducts && shopFishingPoleUpgrades.get(i).getStock()<=0) continue;
                stage.getBatch().draw(shopFishingPoleUpgrades.get(i).getTextureByUpgradeName(),
                    button.getX()+(button.getWidth()-width)/2,
                    button.getY()+(button.getHeight()-height)/2,width,height);
            }
        }
        if(selectRecipe){
            for(int i=0; i<shopRecipes.size(); i++){
                float width=64;
                float height=64;
                ImageButton button=recipes[i];
                if(!allProducts && shopRecipes.get(i).getStock()<=0) continue;
                stage.getBatch().draw(shopRecipes.get(i).getRecipe().getTexture(),
                    button.getX()+(button.getWidth()-width)/2,
                    button.getY()+(button.getHeight()-height)/2,width,height);
            }
        }
        stage.getBatch().end();
        stage.act(v);
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
    public void setUpUI(){
        super.setUpUI();
        if(selectFishingPole){
            for(int i=0; i<shopFishingPoleUpgrades.size(); i++){
                if(allProducts || shopFishingPoleUpgrades.get(i).getStock()>0)
                    centerTable.add(fishingPoleUpgrades[i]).width(120).height(120).pad(10);
            }
        }
        if(selectRecipe){
            for(int i=0; i<shopRecipes.size(); i++){
                if(allProducts || shopRecipes.get(i).getStock()>0)
                    centerTable.add(recipes[i]).width(120).height(120).pad(10);
            }
        }
        stage.addActor(centerTable);
    }
    public void setSelectFishingPole(boolean selectFishingPole) {
        this.selectFishingPole = selectFishingPole;
    }
    public void setSelectRecipe(boolean selectRecipe) {
        this.selectRecipe = selectRecipe;
    }
}
