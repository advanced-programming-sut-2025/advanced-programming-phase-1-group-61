package views.ShopViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import controllers.ShopViewsControllers.ShopViewController;
import models.AssetManager;

public abstract class ShopView implements Screen {
    protected final ShopViewController controller=new ShopViewController(this);
    protected final Stage stage;
    protected final Table centerTable;
    protected final Table productTypeButtonTable;
    protected final SelectBox<String> showProductsApproach;
    protected SelectBox<String> productTypeSelectBox;
    protected final Skin skin= AssetManager.getSkin();
    protected final Label name;
    protected final Label price;
    protected final Label stock;
    protected final Table topLeftTable;
    protected boolean allProducts=true;
    public ShopView() {
        stage = new Stage(new FitViewport(1920, 1080));
        centerTable = new Table();
        productTypeButtonTable = new Table();
        showProductsApproach = new SelectBox<>(skin);
        showProductsApproach.setItems("All Products","Available Products");
        name=new Label("",skin);
        price=new Label("",skin);
        stock=new Label("",skin);
        topLeftTable=new Table();
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        topLeftTable.setFillParent(true);
        topLeftTable.top().left().padTop(20).padLeft(20);
        topLeftTable.add(name);
        topLeftTable.add(price);
        topLeftTable.add(stock);
        stage.addActor(topLeftTable);
        centerTable.setFillParent(true);
        centerTable.top().left().padTop(300).padLeft(100);
    }
}
