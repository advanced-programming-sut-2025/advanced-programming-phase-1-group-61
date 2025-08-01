package views.ShopViews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import controllers.ShopViewsControllers.ShopViewController;
import models.AssetManager;
import models.building.Shop;

public abstract class ShopView implements Screen {
    protected final ShopViewController controller=new ShopViewController(this);
    protected final Stage stage;
    protected final Table centerTable;
    protected final Table productTypeButtonTable;
    protected final SelectBox<String> showProductsApproach;
    protected SelectBox<String> productTypeSelectBox;
    protected Table selectBoxesTable;
    protected final Skin skin= AssetManager.getSkin();
    protected final Label name;
    protected final Label price;
    protected final Label stock;
    protected final Table topLeftTable;
    protected boolean allProducts=true;
    protected final TextField desiredAmount;
    protected final TextButton backToGame;
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
        selectBoxesTable=new Table();
        productTypeSelectBox=new SelectBox<>(skin);
        desiredAmount=new TextField("",skin);
        desiredAmount.setMessageText("Please enter the desired amount you want to buy.");
        backToGame=new TextButton("BACK",skin);
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        topLeftTable.setFillParent(true);
        topLeftTable.top().left().padTop(20).padLeft(20);
        topLeftTable.add(name).width(200).row();
        topLeftTable.add(price).width(200).row();
        topLeftTable.add(stock).width(200).row();
        centerTable.setFillParent(true);
        centerTable.top().left().padTop(300).padLeft(100);
        selectBoxesTable.add(desiredAmount).width(500).height(100).colspan(2).row();
        selectBoxesTable.add(productTypeSelectBox).width(500).height(80);
        selectBoxesTable.add(showProductsApproach).width(500).height(80);
        selectBoxesTable.row();
        selectBoxesTable.add(backToGame).width(500).height(80).colspan(2).row();
        selectBoxesTable.setFillParent(true);
        selectBoxesTable.defaults().pad(20).top().right();
        controller.addListenerForBackToGame();
    }
    public Stage getStage() {
        return stage;
    }
    public Label getName() {
        return name;
    }
    public Label getPrice() {
        return price;
    }
    public Label getStock() {
        return stock;
    }
    public SelectBox<String> getShowProductsApproach() {
        return showProductsApproach;
    }
    public SelectBox<String> getProductTypeSelectBox() {
        return productTypeSelectBox;
    }
    public TextField getDesiredAmount() {
        return desiredAmount;
    }
    public TextButton getBackToGame() {
        return backToGame;
    }
    public void setUpUI(){
        stage.clear();
        centerTable.clear();
        stage.addActor(topLeftTable);
        stage.addActor(selectBoxesTable);
    }
    public abstract Shop getShop();
}
