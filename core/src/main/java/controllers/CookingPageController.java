package controllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.camera.Main;
import models.AlertGenerator;
import models.character.Character;
import models.character.Inventory;
import models.character.InventorySlot;
import models.enums.ItemType;
import models.enums.CookingRecipes;
import views.CookingPageView;

public class CookingPageController {
    private final CookingPageView view;

    public CookingPageController(CookingPageView view) {
        this.view = view;
    }

    public void addHoverClickListener(ImageButton button, CookingRecipes recipe) {
        Character character = Main.getApp().getCurrentGame().getCurrentCharacter();

        button.addListener(new InputListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                view.getRecipeName().setText("name: " + recipe.name());
                view.getPrice().setText("price: " + recipe.getSellPrice());
                view.getRequiredItems().setText("required items: " + recipe.getIngredients());
                if (character.getCookingRecipes().contains(recipe))
                    view.getLocked().setText("unlocked");
                else
                    view.getLocked().setText("locked (Source: "+recipe.getSource()+")");
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                view.getRecipeName().setText("");
                view.getPrice().setText("");
                view.getRequiredItems().setText("");
                view.getLocked().setText("");
            }
        });

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Inventory inventory = character.getInventory();
                for (ItemType itemType : recipe.getIngredients().keySet()) {
                    InventorySlot slot = inventory.getSlotByItem(itemType);
                    if (slot == null || slot.getCount() < recipe.getIngredients().get(itemType)) {
                        AlertGenerator.showAlert("", "You don't have enough required ingredients!", view.getStage());
                        return;
                    }
                }

                ItemType cookedItem = recipe.getFood();
                try {
                    AlertGenerator.showAlert("", "You successfully cooked " + cookedItem.getDisPlayName() + "!", view.getStage());
                } catch (Exception e) {
                    AlertGenerator.showAlert("", "Something went wrong!", view.getStage());
                    return;
                }

                InventorySlot slot = inventory.getSlotByItem(cookedItem);
                if (slot == null) {
                    inventory.addItem(cookedItem, 1);
                } else {
                    slot.setCount(slot.getCount() + 1);
                }
            }
        });
    }
}
