package views.ShopViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import models.AssetManager;
import models.shops.BlackSmith;
import models.shops.ShopItem;
import models.shops.ShopToolUpgrades;
import models.shops.ShopTrashcanUpgrades;

import java.util.ArrayList;

public class BlackSmithView extends ShopView{
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
        productTypeSelectBox=new SelectBox<>(skin);
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
        for(int i=0;i<items.length;i++){
            ImageButton.ImageButtonStyle style=new ImageButton.ImageButtonStyle();
            Drawable imageUp=new TextureRegionDrawable(AssetManager.getSelectorBubbleDefault());
            Drawable imageOver=new TextureRegionDrawable(AssetManager.getSelectorBubbleHover());
            style.up=imageUp;
            style.over=imageOver;
            style.down=imageOver;
            items[i]=new ImageButton(style);
            controller.addHoverListenerForItems(items[i],shopItems.get(i));
        }
        for(int i=0;i<tools.length;i++){
            ImageButton.ImageButtonStyle style=new ImageButton.ImageButtonStyle();
            Drawable imageUp=new TextureRegionDrawable(AssetManager.getSelectorBubbleDefault());
            Drawable imageOver=new TextureRegionDrawable(AssetManager.getSelectorBubbleHover());
            style.up=imageUp;
            style.over=imageOver;
            style.down=imageOver;
            tools[i]=new ImageButton(style);
            controller.addHoverListenerForShopTools(tools[i],shopToolUpgrades.get(i));
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
                stage.getBatch().draw(shopItems.get(i).getItem().getTexture(),
                    button.getX()+(button.getWidth()-width)/2,
                    button.getY()+(button.getHeight()-height)/2,width,height);
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

    public void setUpUI(){
        if(selectItems) {
            for (int i = 0; i < shopItems.size(); i++) {
                if (allProducts) centerTable.add(items[i]).width(120).height(120).pad(10);
                else {
                    if (shopItems.get(i).getStock() > 0) centerTable.add(items[i]).width(120).height(120).pad(10);
                }
            }
        }
        if(selectTools) {
            for (int i = 0; i < shopToolUpgrades.size(); i++) {
                if(allProducts) centerTable.add(tools[i]).width(120).height(120).pad(10);
                else {
                    if(shopToolUpgrades.get(i).getStock()>0) centerTable.add(tools[i]).width(120).height(120).pad(10);
                }
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
}
