package views.ShopViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import controllers.ShopViewsControllers.CarpenterViewController;
import models.App;
import models.shops.Carpenter;
import models.shops.ShopCages;
import models.shops.ShopItem;

import java.util.ArrayList;

public class CarpenterView extends ShopView{
    private final Carpenter shop;
    private final CarpenterViewController secondController=new CarpenterViewController(this);
    private final ImageButton[] permanentItems;
    private final ImageButton[] farmBuildings;
    private final ArrayList<ShopItem> shopPermanentItems;
    private final ArrayList<ShopCages> shopFarmBuildings;
    private boolean selectPermanentItem = true;
    private boolean selectFarmBuilding = false;
    public CarpenterView(Carpenter carpenter) {
        super();
        this.shop = carpenter;
        productTypeSelectBox.setItems("Permanent Items", "Farm Buildings");
        shopPermanentItems = shop.getPermanentItems();
        shopFarmBuildings = shop.getFarmBuildings();
        permanentItems = new ImageButton[shopPermanentItems.size()];
        farmBuildings = new ImageButton[shopFarmBuildings.size()];
    }
    @Override
    public void show() {
        super.show();
        secondController.handleTypeSelectBox();
        for(int i=0;i<shopPermanentItems.size();i++){
            App.Extract(i,permanentItems);
            controller.addHoverListenerForItems(permanentItems[i],shopPermanentItems.get(i));
        }
        for(int i=0;i<shopFarmBuildings.size();i++){
            App.Extract(i,farmBuildings);
            controller.addHoverListenerForShopCages(farmBuildings[i],shopFarmBuildings.get(i));
        }
        setUpUI();
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        if(selectPermanentItem){
            for(int i=0;i<shopPermanentItems.size();i++){
                float width=64;
                float height=64;
                ImageButton button=permanentItems[i];
                if(!allProducts && shopPermanentItems.get(i).getStock()<=0) continue;
                stage.getBatch().draw(shopPermanentItems.get(i).getItem().getTexture(),
                    button.getX()+(button.getWidth()-width)/2,
                    button.getY()+(button.getHeight()-height)/2,width,height);
            }
        }
        if(selectFarmBuilding){
            for(int i=0;i<shopFarmBuildings.size();i++){
                float width=64;
                float height=64;
                ImageButton button=farmBuildings[i];
                if(!allProducts && shopFarmBuildings.get(i).getStock()<=0) continue;
                stage.getBatch().draw(shopFarmBuildings.get(i).getCageType().getTexture(),
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
        if(selectPermanentItem){
            for(int i=0;i<shopPermanentItems.size();i++){
                if(allProducts || shopPermanentItems.get(i).getStock()>0)
                    centerTable.add(permanentItems[i]).width(120).height(120).pad(10);
            }
        }
        if(selectFarmBuilding){
            for(int i=0;i<shopFarmBuildings.size();i++){
                if(allProducts || shopFarmBuildings.get(i).getStock()>0)
                    centerTable.add(farmBuildings[i]).width(120).height(120).pad(10);
            }
        }
        stage.addActor(centerTable);
    }
    public void setSelectPermanentItem(boolean select) {
        selectPermanentItem = select;
    }
    public void setSelectFarmBuilding(boolean select) {
        selectFarmBuilding = select;
    }
}
