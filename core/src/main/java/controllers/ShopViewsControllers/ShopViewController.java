package controllers.ShopViewsControllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.camera.Main;
import models.AlertGenerator;
import models.App;
import models.building.Shop;
import models.shops.*;
import views.ShopViews.ShopView;

public class ShopViewController {
    private final ShopView view;
    private Runnable onBuyCallback = null;

    public ShopViewController(ShopView view) {
        this.view = view;
    }

    private void updateLabels(String name, int price, int stock) {
        view.getName().setText("Name: " + name);
        view.getPrice().setText("Price: " + price);
        if (stock > (int) 1e9) {
            view.getStock().setText("Stock: UNLIMITED");
        } else {
            view.getStock().setText("Stock: " + stock);
        }
    }

    private void clearLabels() {
        view.getName().setText("");
        view.getPrice().setText("");
        view.getStock().setText("");
    }

    public void addHoverListenerForItems(ImageButton button, ShopItem item) {
        button.addListener(new InputListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                updateLabels(item.getItem().getDisPlayName(), item.getPrice(), item.getStock());
                onBuyCallback = () -> updateLabels(item.getItem().getDisPlayName(), item.getPrice(), item.getStock());
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                clearLabels();
                onBuyCallback = null;
            }
        });

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buy(item.getItem().getDisPlayName());
            }
        });
    }

    public void addHoverListenerForShopTools(ImageButton button, ShopToolUpgrades tool) {
        button.addListener(new InputListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                updateLabels(tool.getUpgradeName() + " Tool", tool.getPrice(), tool.getStock());
                onBuyCallback = () -> updateLabels(tool.getUpgradeName() + " Tool", tool.getPrice(), tool.getStock());
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                clearLabels();
                onBuyCallback = null;
            }
        });

        button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                buy(tool.getUpgradeName());
            }
        });
    }

    public void addHoverListenerForShopTrashcans(ImageButton button, ShopTrashcanUpgrades trashcan) {
        button.addListener(new InputListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                updateLabels(trashcan.getTrashcanType().getDisplayName() + " Trashcan", trashcan.getPrice(), trashcan.getStock());
                onBuyCallback = () -> updateLabels(trashcan.getTrashcanType().getDisplayName() + " Trashcan", trashcan.getPrice(), trashcan.getStock());
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                clearLabels();
                onBuyCallback = null;
            }
        });

        button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                buy(trashcan.getTrashcanType().getDisplayName());
            }
        });
    }

    public void addHoverListenerForShopCages(ImageButton button, ShopCages cage) {
        button.addListener(new InputListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                updateLabels(cage.getCageType().getDisplayName() + " Cage", cage.getPrice(), cage.getStock());
                onBuyCallback = () -> updateLabels(cage.getCageType().getDisplayName() + " Cage", cage.getPrice(), cage.getStock());
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                clearLabels();
                onBuyCallback = null;
            }
        });

        button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                buy(cage.getCageType().getDisplayName());
            }
        });
    }

    public void addHoverListenerForShopFishingPoleUpgrades(ImageButton button, ShopFishingPoleUpgrades pole) {
        button.addListener(new InputListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                updateLabels(pole.getUpgradeName() + " Fishing Pole", pole.getPrice(), pole.getStock());
                onBuyCallback = () -> updateLabels(pole.getUpgradeName() + " Fishing Pole", pole.getPrice(), pole.getStock());
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                clearLabels();
                onBuyCallback = null;
            }
        });

        button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                buy(pole.getUpgradeName());
            }
        });
    }

    public void addHoverListenerForShopRecipes(ImageButton button, ShopRecipes recipe) {
        button.addListener(new InputListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                updateLabels(recipe.getRecipe().name(), recipe.getPrice(), recipe.getStock());
                onBuyCallback = () -> updateLabels(recipe.getRecipe().name(), recipe.getPrice(), recipe.getStock());
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                clearLabels();
                onBuyCallback = null;
            }
        });

        button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                buy(recipe.getRecipe().name());
            }
        });
    }
    private void buy(String productName) {
        Shop shop = view.getShop();
        String amountStr = view.getDesiredAmount().getText();
        int amount;

        if (amountStr.isEmpty()) {
            AlertGenerator.showAlert("", "Please enter the amount you want to buy!", view.getStage());
            return;
        }
        try {
            amount = Integer.parseInt(amountStr);
        } catch (NumberFormatException e) {
            AlertGenerator.showAlert("", "Please enter a valid amount!", view.getStage());
            return;
        }
        String result = shop.purchaseProduct(productName, amount);
        AlertGenerator.showAlert("", result, view.getStage());
        if (onBuyCallback != null) {
            onBuyCallback.run();
        }
    }
    public void addListenerForBackToGame(){
        view.getBackToGame().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(App.getLastScreenBeforeShop());
            }
        });
    }
}
