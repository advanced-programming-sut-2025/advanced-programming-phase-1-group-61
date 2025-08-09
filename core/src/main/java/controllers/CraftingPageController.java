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
import models.enums.Recipe;
import views.CraftingPageView;

public class CraftingPageController {
    private final CraftingPageView view;
    public CraftingPageController(CraftingPageView view) {
        this.view = view;
    }
    public void addHoverClickListener(ImageButton button, Recipe recipe) {
        Character character= Main.getApp().getCurrentGame().getCurrentCharacter();
        button.addListener(new InputListener(){
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                view.getCraftName().setText("name: "+recipe.name());
                view.getPrice().setText("price: "+recipe.getPrice());
                view.getRequiredItems().setText("required items: "+recipe.getRequiredItems());
                if(character.checkRecipeAvailability(recipe))
                    view.getLocked().setText("not locked");
                else
                    view.getLocked().setText("locked");
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                view.getCraftName().setText("");
                view.getPrice().setText("");
                view.getRequiredItems().setText("");
                view.getLocked().setText("");
            }
        });
        button.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y, int pointer) {
                Inventory inventory=character.getInventory();
                for(ItemType itemType : recipe.getRecipe().keySet()){
                    InventorySlot slot=inventory.getSlotByItem(itemType);
                    if(slot==null){
                        AlertGenerator.showAlert("","You don't have enough required items!",view.getStage());
                        return;
                    }
                    if(slot.getCount()<recipe.getRecipe().get(itemType)){
                        AlertGenerator.showAlert("","You don't have enough required items!",view.getStage());
                        return;
                    }
                }
                ItemType recipeItem=recipe.getItemType();
                try {
                    AlertGenerator.showAlert("", "You successfully crafted " + recipeItem.getDisPlayName() + "!", view.getStage());
                } catch (Exception e) {
                    AlertGenerator.showAlert("", "Something went wrong!",view.getStage());
                    return;
                }
                InventorySlot slot=inventory.getSlotByItem(recipeItem);
                if(slot==null){
                    inventory.addItem(recipeItem,1);
                } else {
                    slot.setCount(slot.getCount()+1);
                }
            }
        });
    }
}
