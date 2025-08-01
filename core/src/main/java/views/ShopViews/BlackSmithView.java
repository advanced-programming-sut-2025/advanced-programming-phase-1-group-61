package views.ShopViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import controllers.ShopViewsControllers.BlackSmithViewController;
import models.App;
import models.AssetManager;
import models.shops.BlackSmith;
import models.shops.ShopItem;
import models.shops.ShopToolUpgrades;
import models.shops.ShopTrashcanUpgrades;

import java.util.ArrayList;

public class BlackSmithView extends ShopView{
    private final BlackSmithViewController secondController=new BlackSmithViewController(this);
    private ImageButton[] items;
    private ImageButton[] tools;
    private ImageButton[] trashcans;
    private final BlackSmith shop;
    private final ArrayList<ShopItem> shopItems;
    private final ArrayList<ShopToolUpgrades> shopToolUpgrades;
    private final ArrayList<ShopTrashcanUpgrades> shopTrashcanUpgrades;
    private boolean selectItems=true;
    private boolean selectTools=false;
    private boolean selectTrashcan=false;
    public BlackSmithView(BlackSmith blackSmith) {
        super();
        productTypeSelectBox.setItems("Items","Tool Upgrades","Trashcan Upgrades");
        this.shop = blackSmith;
        shopItems = blackSmith.getItems();
        shopToolUpgrades = blackSmith.getToolUpgrades();
        shopTrashcanUpgrades = blackSmith.getTrashcanUpgrades();
        items=new ImageButton[shopItems.size()];
        tools=new ImageButton[shopToolUpgrades.size()];
        trashcans=new ImageButton[shopTrashcanUpgrades.size()];
    }
    @Override
    public void show() {
        super.show();
        secondController.handleTypeSelectBox();
        for(int i=0;i<items.length;i++){
            App.Extract(i, items);
            controller.addHoverListenerForItems(items[i],shopItems.get(i));
        }
        for(int i=0;i<tools.length;i++){
            App.Extract(i, tools);
            controller.addHoverListenerForShopTools(tools[i],shopToolUpgrades.get(i));
        }
        for(int i=0;i<trashcans.length;i++){
            App.Extract(i, trashcans);
            controller.addHoverListenerForShopTrashcans(trashcans[i],shopTrashcanUpgrades.get(i));
        }
        setUpUI();
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        if(selectItems){
            for(int i=0;i<items.length;i++){
                float width = 64;
                float height = 64;
                ImageButton button=items[i];
                if(!allProducts && shopItems.get(i).getStock()<=0) continue;
                stage.getBatch().draw(shopItems.get(i).getItem().getTexture(),
                    button.getX()+(button.getWidth()-width)/2,
                    button.getY()+(button.getHeight()-height)/2,width,height);
            }
        }
        if(selectTools){
            for(int i=0;i<tools.length;i++) {
                float width = 64;
                float height = 64;
                ImageButton button = tools[i];
                if(!allProducts && shopToolUpgrades.get(i).getStock()<=0) continue;
                stage.getBatch().draw(shopToolUpgrades.get(i).getIngredientItem().getTexture(),
                    button.getX() + (button.getWidth() - width) / 2,
                    button.getY() + (button.getHeight() - height) / 2, width, height);
            }
        }
        if(selectTrashcan){
            for(int i=0;i<trashcans.length;i++) {
                float width = 64;
                float height = 64;
                ImageButton button = trashcans[i];
                if(!allProducts && shopTrashcanUpgrades.get(i).getStock()<=0) continue;
                stage.getBatch().draw(shopTrashcanUpgrades.get(i).getTrashcanType().getTexture(),
                    button.getX() + (button.getWidth() - width) / 2,
                    button.getY() + (button.getHeight() - height) / 2, width, height);
            }
        }
        stage.getBatch().end();
        stage.act(delta);
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

    public void setUpUI() {
        super.setUpUI();
        if (selectItems) {
            for (int i = 0; i < shopItems.size(); i++) {
                if (allProducts || shopItems.get(i).getStock() > 0)
                    centerTable.add(items[i]).width(120).height(120).pad(10);
            }
        }

        if (selectTools) {
            for (int i = 0; i < shopToolUpgrades.size(); i++) {
                if (allProducts || shopToolUpgrades.get(i).getStock() > 0)
                    centerTable.add(tools[i]).width(120).height(120).pad(10);
            }
        }
        if(selectTrashcan){
            for (int i = 0; i < shopTrashcanUpgrades.size(); i++) {
                if(allProducts || shopTrashcanUpgrades.get(i).getStock() > 0)
                    centerTable.add(trashcans[i]).width(120).height(120).pad(10);
            }
        }
        stage.addActor(centerTable);
    }

    public void setSelectItems(boolean selectItems) {
        this.selectItems = selectItems;
    }
    public void setSelectTools(boolean selectTools) {
        this.selectTools = selectTools;
    }
    public void setSelectTrashcan(boolean selectTrashcan) {
        this.selectTrashcan = selectTrashcan;
    }
    public BlackSmith getShop(){
        return shop;
    }
}
