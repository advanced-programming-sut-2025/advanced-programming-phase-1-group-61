package views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import models.character.Character;
import models.character.InventorySlot;
import models.enums.ItemType;
import models.tool.Tool;

import java.util.ArrayList;
import java.util.List;

public class ToolbarUI extends Table {
    private final Skin skin;
    private final Character character;
    private final Texture emptySlot;
    private final Texture errorTexture;
    private int selectedSlotIndex = -1;


    private void handleSlotAction(int index) {
        if (index >= toolbarSlots.size()) return;

        InventorySlot slotData = character.getInventory().getSlots().get(index);
        Object item = slotData.getObjectInSlot();

        if (item instanceof Tool tool) {
            character.setCurrentTool(tool);
            character.setCurrentItem(null);
            selectedSlotIndex = index;
            updateSlotHighlighting();
        } else if (item instanceof ItemType itemType) {
            character.setCurrentTool(null);
            character.setCurrentItem(itemType);
            selectedSlotIndex = index;
            updateSlotHighlighting();
        }
    }


    @Override
    public void act(float delta) {
        super.act(delta);

        for (int i = 0; i < toolbarSlots.size(); i++) {
            if (com.badlogic.gdx.Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.NUM_1 + i)) {
                handleSlotAction(i);
            }
        }
    }


    private final List<Table> toolbarSlots = new ArrayList<>();

    public ToolbarUI(Skin skin, Character character) {
        this.skin = skin;
        this.character = character;


        this.setFillParent(true);
        this.bottom();

        emptySlot = new Texture("emptySlot.png");
        errorTexture = new Texture("error.png");

        createToolbarUI();
    }

    private void createToolbarUI() {
        this.clear();
        Table toolbarTable = new Table();
        toolbarTable.defaults().size(64).pad(5).padBottom(50);

        List<InventorySlot> slots = character.getInventory().getSlots();

        int toolbarCount = 5;
        for (int i = 0; i < toolbarCount && i < slots.size(); i++) {
            InventorySlot slotData = slots.get(i);
            Table slot = createSlot(slotData);
            toolbarSlots.add(slot);
            toolbarTable.add(slot);
        }

        this.add(toolbarTable).padBottom(10);
    }

    private Table createSlot(InventorySlot slotData) {
        Table slot = new Table();
        Image background = createImage(emptySlot);
        background.setFillParent(true);
        slot.addActor(background);
        slot.setUserObject(background);
        slot.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int index = toolbarSlots.indexOf(slot);
                handleSlotAction(index);
            }
        });

        if (slotData.getObjectInSlot() != null) {
            Texture texture = getTextureForObject(slotData.getObjectInSlot());
            Image image = createImage(texture);

            Label countLabel = new Label(String.valueOf(slotData.getCount()), skin);
            Stack stack = new Stack();
            stack.add(image);
            stack.add(countLabel);
            slot.add(stack).size(64).center();
        }

        return slot;
    }

    private void updateSlotHighlighting() {
        for (int i = 0; i < toolbarSlots.size(); i++) {
            Table slot = toolbarSlots.get(i);
            Image bg = (Image) slot.getUserObject();
            if (i == selectedSlotIndex) {
                bg.setColor(1f, 1f, 0f, 1f);
            } else {
                bg.setColor(1f, 1f, 1f, 1f);
            }
        }
    }



    private Texture getTextureForObject(Object obj) {
        if (obj instanceof Tool tool) {
            return tool.getType().getTextureForLevel(tool.getLevel());
        }
        return errorTexture;
    }

    private Image createImage(Texture texture) {
        return new Image(new TextureRegionDrawable(new TextureRegion(texture)));
    }
    public void refreshUI() {
        toolbarSlots.clear();
        this.clear();
        createToolbarUI();
        updateSlotHighlighting();
    }
    public void updateSlotsUI() {
        List<InventorySlot> slots = character.getInventory().getSlots();

        for (int i = 0; i < toolbarSlots.size(); i++) {
            Table slot = toolbarSlots.get(i);
            slot.clearChildren();

            InventorySlot slotData = slots.get(i);

            Image bg = (Image) slot.getUserObject();
            slot.addActor(bg);

            if (slotData.getObjectInSlot() != null) {
                Texture texture = getTextureForObject(slotData.getObjectInSlot());
                Image image = createImage(texture);

                Label countLabel = new Label(String.valueOf(slotData.getCount()), skin);
                Stack stack = new Stack();
                stack.add(image);
                stack.add(countLabel);

                slot.add(stack).size(64).center();

            }
        }
        updateSlotHighlighting();
    }


}
