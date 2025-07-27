package views.ShopViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import controllers.ShopViewsControllers.PierreViewController;
import models.App;
import models.shops.Pierre;
import models.shops.ShopBackpacks;
import models.shops.ShopItem;
import models.shops.ShopRecipes;

import java.util.ArrayList;

public class PierreView extends ShopView {
    private final Pierre shop;
    private final PierreViewController secondController=new PierreViewController(this);
    private final ImageButton[] yearRoundItems;
    private final ImageButton[] springItems;
    private final ImageButton[] summerItems;
    private final ImageButton[] fallItems;
    private final ImageButton[] backpacks;
    private final ImageButton[] recipes;
    private final ArrayList<ShopItem> shopYearRoundItems;
    private final ArrayList<ShopItem> shopSpringItems;
    private final ArrayList<ShopItem> shopSummerItems;
    private final ArrayList<ShopItem> shopFallItems;
    private final ArrayList<ShopBackpacks> shopBackpacks;
    private final ArrayList<ShopRecipes> shopRecipes;
    private boolean selectYearRoundItems=true;
    private boolean selectSpringItems=false;
    private boolean selectSummerItems=false;
    private boolean selectFallItems=false;
    private boolean selectBackpacks=false;
    private boolean selectRecipes=false;
    public PierreView(Pierre shop) {
        super();
        this.shop=shop;
        productTypeSelectBox.setItems("Year Round Items","Spring Items","Summer Items","Fall Items","Backpacks","Recipes");
        shopYearRoundItems=shop.getYearRoundItems();
        shopSpringItems=shop.getSpringItems();
        shopSummerItems=shop.getSummerItems();
        shopFallItems=shop.getFallItems();
        shopBackpacks=shop.getShopBackpacks();
        shopRecipes=shop.getShopRecipes();
        yearRoundItems=new ImageButton[shopYearRoundItems.size()];
        springItems=new ImageButton[shopSpringItems.size()];
        summerItems=new ImageButton[shopSummerItems.size()];
        fallItems=new ImageButton[shopFallItems.size()];
        backpacks=new ImageButton[shopBackpacks.size()];
        recipes=new ImageButton[shopRecipes.size()];
    }
    @Override
    public void show() {
        super.show();
        secondController.handleTypeSelectBox();
        for(int i=0; i<shopYearRoundItems.size(); i++){
            App.Extract(i,yearRoundItems);
            controller.addHoverListenerForItems(yearRoundItems[i],shopYearRoundItems.get(i));
        }
        for(int i=0; i<shopSpringItems.size(); i++){
            App.Extract(i,springItems);
            controller.addHoverListenerForItems(springItems[i],shopSpringItems.get(i));
        }
        for(int i=0; i<shopSummerItems.size(); i++){
            App.Extract(i,summerItems);
            controller.addHoverListenerForItems(summerItems[i],shopSummerItems.get(i));
        }
        for(int i=0; i<shopFallItems.size(); i++){
            App.Extract(i,fallItems);
            controller.addHoverListenerForItems(fallItems[i],shopFallItems.get(i));
        }
        for(int i=0; i<shopBackpacks.size(); i++){
            App.Extract(i,backpacks);
        }
        for(int i=0; i<shopRecipes.size(); i++){
            App.Extract(i,recipes);
        }
        setUpUI();
    }
    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        if(selectYearRoundItems){
            extract(shopYearRoundItems,yearRoundItems);
        }
        if(selectSpringItems){
            extract(shopSpringItems,springItems);
        }
        if(selectSummerItems){
            extract(shopSummerItems,summerItems);
        }
        if(selectFallItems){
            extract(shopFallItems,fallItems);
        }
        if(selectBackpacks){
            for(int i=0;i<shopBackpacks.size();i++){
                float width=64;
                float height=64;
                ImageButton button=backpacks[i];
                if(!allProducts && shopBackpacks.get(i).getStock()<=0) continue;
                stage.getBatch().draw(new Texture(Gdx.files.internal("error.png")),
                    button.getX()+(button.getWidth()-width)/2,
                    button.getY()+(button.getHeight()-height)/2,width,height);
            }
        }
        if(selectRecipes){
            for(int i=0;i<shopRecipes.size();i++){
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
        if(selectYearRoundItems){
            for(int i=0; i<shopYearRoundItems.size(); i++){
                if(allProducts || shopYearRoundItems.get(i).getStock()>0)
                    centerTable.add(yearRoundItems[i]).width(120).height(120).pad(10);
            }
        }
        if(selectSpringItems){
            for(int i=0; i<shopSpringItems.size(); i++){
                if(allProducts || shopSpringItems.get(i).getStock()>0)
                    centerTable.add(springItems[i]).width(120).height(120).pad(10);
            }
        }
        if(selectSummerItems){
            for(int i=0; i<shopSummerItems.size(); i++){
                if(allProducts || shopSummerItems.get(i).getStock()>0)
                    centerTable.add(summerItems[i]).width(120).height(120).pad(10);
            }
        }
        if(selectFallItems){
            for(int i=0; i<shopFallItems.size(); i++){
                if(allProducts || shopFallItems.get(i).getStock()>0)
                    centerTable.add(fallItems[i]).width(120).height(120).pad(10);
            }
        }
        if(selectBackpacks){
            for(int i=0; i<shopBackpacks.size(); i++){
                if(allProducts || shopBackpacks.get(i).getStock()>0)
                    centerTable.add(backpacks[i]).width(120).height(120).pad(10);
            }
        }
        if(selectRecipes){
            for(int i=0; i<shopRecipes.size(); i++){
                if(allProducts || shopRecipes.get(i).getStock()>0)
                    centerTable.add(recipes[i]).width(120).height(120).pad(10);
            }
        }
        stage.addActor(centerTable);
    }
    public void setSelectYearRoundItems(boolean b) {
        selectYearRoundItems = b;
    }
    public void setSelectSpringItems(boolean b) {
        selectSpringItems = b;
    }
    public void setSelectSummerItems(boolean b) {
        selectSummerItems = b;
    }
    public void setSelectFallItems(boolean b) {
        selectFallItems = b;
    }
    public void setSelectBackpacks(boolean b) {
        selectBackpacks = b;
    }
    public void setSelectRecipes(boolean b) {
        selectRecipes = b;
    }
    private void extract(ArrayList<ShopItem> shopItems,ImageButton[] items) {
        for(int i=0;i<shopItems.size();i++){
            float width=64;
            float height=64;
            ImageButton button=items[i];
            if(!allProducts && shopItems.get(i).getStock()<=0) continue;
            stage.getBatch().draw(shopItems.get(i).getItem().getTexture(),
                button.getX()+(button.getWidth()-width)/2,
                button.getY()+(button.getHeight()-height)/2,width,height);
        }
    }
}
