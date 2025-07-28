package views.ShopViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import controllers.ShopViewsControllers.MarnieViewController;
import models.App;
import models.building.Shop;
import models.shops.Marnie;
import models.shops.ShopAnimals;
import models.shops.ShopItem;
import models.shops.ShopTool;

import java.util.ArrayList;

public class MarnieView extends ShopView {
    private final Marnie shop;
    private final MarnieViewController secondController=new MarnieViewController(this);
    private final ImageButton[] permanentItems;
    private final ImageButton[] permanentTools;
    private final ImageButton[] permanentAnimals;
    private final ArrayList<ShopItem> shopPermanentItems;
    private final ArrayList<ShopTool> shopPermanentTools;
    private final ArrayList<ShopAnimals> shopPermanentAnimals;
    private boolean selectTools=false;
    private boolean selectAnimals=false;
    private boolean selectItems=true;
    public MarnieView(Marnie shop) {
        super();
        this.shop=shop;
        productTypeSelectBox.setItems("Permanent Items","Tools","Animals");
        shopPermanentTools=shop.getShopTools();
        shopPermanentAnimals=shop.getShopAnimals();
        shopPermanentItems=shop.getShopItems();
        permanentItems=new ImageButton[shopPermanentTools.size()];
        permanentTools=new ImageButton[shopPermanentAnimals.size()];
        permanentAnimals=new ImageButton[shopPermanentAnimals.size()];
    }
    @Override
    public void show() {
        super.show();
        secondController.handleTypeSelectBox();
        for(int i=0;i<shopPermanentItems.size();i++){
            App.Extract(i,permanentItems);
            controller.addHoverListenerForItems(permanentItems[i],shopPermanentItems.get(i));
        }
        for(int i=0;i<shopPermanentTools.size();i++){
            App.Extract(i,permanentTools);
        }
        for (int i=0;i<shopPermanentAnimals.size();i++){
            App.Extract(i,permanentAnimals);
        }
        setUpUI();
    }
    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        if(selectItems){
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
        if(selectTools){
            for(int i=0;i<shopPermanentTools.size();i++){
                float width=64;
                float height=64;
                ImageButton button=permanentTools[i];
                if(!allProducts && shopPermanentTools.get(i).getStock()<=0) continue;
                stage.getBatch().draw(shopPermanentTools.get(i).getTool().getDefaultTexture(),
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
        if(selectItems){
            for(int i=0;i<shopPermanentItems.size();i++){
                if(allProducts || shopPermanentItems.get(i).getStock()>0)
                    centerTable.add(permanentItems[i]).width(120).height(120).pad(10);
            }
        }
        if(selectTools){
            for(int i=0;i<shopPermanentTools.size();i++){
                if(allProducts || shopPermanentTools.get(i).getStock()>0)
                    centerTable.add(permanentTools[i]).width(120).height(120).pad(10);
            }
        }
        if(selectAnimals){
            for(int i=0;i<shopPermanentAnimals.size();i++){
                if(allProducts || shopPermanentAnimals.get(i).getStock()>0)
                    centerTable.add(permanentAnimals[i]).width(120).height(120).pad(10);
            }
        }
        stage.addActor(centerTable);
    }
    public void setSelectTools(boolean selectTools) {
        this.selectTools = selectTools;
    }
    public void setSelectAnimals(boolean selectAnimals) {
        this.selectAnimals = selectAnimals;
    }
    public void setSelectItems(boolean selectItems) {
        this.selectItems = selectItems;
    }
    public Shop getShop() {
        return shop;
    }
}
