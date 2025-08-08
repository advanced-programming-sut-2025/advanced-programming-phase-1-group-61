package views.ShopViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import controllers.ShopViewsControllers.JojaMartViewController;
import io.github.camera.Main;
import models.App;
import models.building.Shop;
import models.shops.JojaMart;
import models.shops.ShopItem;

import java.util.ArrayList;

public class JojaMartView extends ShopView {
    private final JojaMart shop;
    private final JojaMartViewController secondController=new JojaMartViewController(this);
    private final ImageButton[] permanentShopItems;
    private final ImageButton[] springShopItems;
    private final ImageButton[] summerShopItems;
    private final ImageButton[] fallShopItems;
    private final ImageButton[] winterShopItems;
    private final ArrayList<ShopItem> shopPermanentItems;
    private final ArrayList<ShopItem> shopSpringItems;
    private final ArrayList<ShopItem> shopSummerItems;
    private final ArrayList<ShopItem> shopFallItems;
    private final ArrayList<ShopItem> shopWinterItems;
    private boolean selectPermanentItems=true;
    private boolean selectSpringItems=false;
    private boolean selectSummerItems=false;
    private boolean selectFallItems=false;
    private boolean selectWinterItems=false;
    public JojaMartView(JojaMart shop) {
        super();
        this.shop = shop;
        productTypeSelectBox.setItems("Permanent Items", "Spring Items", "Summer Items", "Fall Items", "Winter Items");
        shopPermanentItems = shop.getPermanentShopItems();
        shopSpringItems = shop.getSpringShopItems();
        shopSummerItems = shop.getSummerShopItems();
        shopFallItems = shop.getFallShopItems();
        shopWinterItems = shop.getWinterShopItems();
        permanentShopItems = new ImageButton[shop.getPermanentShopItems().size()];
        springShopItems = new ImageButton[shop.getSpringShopItems().size()];
        summerShopItems = new ImageButton[shop.getSummerShopItems().size()];
        fallShopItems = new ImageButton[shop.getFallShopItems().size()];
        winterShopItems = new ImageButton[shop.getWinterShopItems().size()];
    }
    @Override
    public void show() {
        super.show();
        secondController.handleTypeSelectBox();

        for (int i = 0; i < permanentShopItems.length; i++) {
            Main.getApp().Extract(i, permanentShopItems);
            controller.addHoverListenerForItems(permanentShopItems[i], shopPermanentItems.get(i));
        }
        for (int i = 0; i < springShopItems.length; i++) {
            Main.getApp().Extract(i, springShopItems);
            controller.addHoverListenerForItems(springShopItems[i], shopSpringItems.get(i));
        }
        for (int i = 0; i < summerShopItems.length; i++) {
            Main.getApp().Extract(i, summerShopItems);
            controller.addHoverListenerForItems(summerShopItems[i], shopSummerItems.get(i));
        }
        for (int i = 0; i < fallShopItems.length; i++) {
            Main.getApp().Extract(i, fallShopItems);
            controller.addHoverListenerForItems(fallShopItems[i], shopFallItems.get(i));
        }
        for (int i = 0; i < winterShopItems.length; i++) {
            Main.getApp().Extract(i, winterShopItems);
            controller.addHoverListenerForItems(winterShopItems[i], shopWinterItems.get(i));
        }
        setUpUI();
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        if(selectPermanentItems) extract(shopPermanentItems,permanentShopItems);
        if(selectSpringItems) extract(shopSpringItems,springShopItems);
        if(selectSummerItems) extract(shopSummerItems,summerShopItems);
        if(selectFallItems) extract(shopFallItems,fallShopItems);
        if(selectWinterItems) extract(shopWinterItems,winterShopItems);
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

    public void setUpUI() {
        super.setUpUI();
        if(selectPermanentItems) extractForTable(shopPermanentItems,permanentShopItems);
        if(selectSpringItems) extractForTable(shopSpringItems,springShopItems);
        if(selectSummerItems) extractForTable(shopSummerItems,summerShopItems);
        if(selectFallItems) extractForTable(shopFallItems,fallShopItems);
        if(selectWinterItems) extractForTable(shopWinterItems,winterShopItems);
        stage.addActor(centerTable);
    }

    public void setSelectPermanentItems(boolean select) {
        selectPermanentItems = select;
    }
    public void setSelectSpringItems(boolean select) {
        selectSpringItems = select;
    }
    public void setSelectSummerItems(boolean select) {
        selectSummerItems = select;
    }
    public void setSelectFallItems(boolean select) {
        selectFallItems = select;
    }
    public void setSelectWinterItems(boolean select) {
        selectWinterItems = select;
    }
    private void extract(ArrayList<ShopItem> shopItems,ImageButton[] itemsShop) {
        for(int i=0;i<shopItems.size();i++){
            float width=64;
            float height=64;
            ImageButton button=itemsShop[i];
            if(!allProducts && shopItems.get(i).getStock()<=0) continue;
            stage.getBatch().draw(shopItems.get(i).getItem().getTexture(),
                button.getX()+(button.getWidth()-width)/2,
                button.getY()+(button.getHeight()-height)/2,width,height);
        }
    }
    private void extractForTable(ArrayList<ShopItem> shopItems,ImageButton[] itemsShop) {
        for(int i=0;i<shopItems.size();i++){
            if(allProducts || shopItems.get(i).getStock()>0)
                centerTable.add(itemsShop[i]).width(120).height(120).pad(10);
        }
    }
    public Shop getShop() {
        return shop;
    }
}
