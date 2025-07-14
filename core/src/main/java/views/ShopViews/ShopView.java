package views.ShopViews;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import controllers.ShopViewsControllers.ShopViewController;

public abstract class ShopView implements Screen {
    protected final ShopViewController controller=new ShopViewController(this);
    protected Stage stage;
    protected Table mainTable;
    public ShopView() {
        stage = new Stage(new FitViewport(1920, 1080));
        mainTable = new Table();
    }
}
