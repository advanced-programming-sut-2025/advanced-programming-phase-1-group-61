package views;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import models.character.Inventory;
import models.tool.Tool;

import java.util.ArrayList;
import java.util.List;

public class InventoryUI extends Table implements InputProcessor {

    private final Inventory inventory;
    private final Skin skin;
    private final DragAndDrop dragAndDrop;
    private final Texture errorTexture;
    private final Texture emptySlot;
    private final Texture pickedSlot;
    private boolean inventoryVisible = false;

    public void setInventoryVisible(boolean inventoryVisible) {
        this.inventoryVisible = inventoryVisible;
    }

    private final List<Table> slotTables = new ArrayList<>();
    private final List<Table> toolbarSlots = new ArrayList<>();
    private int selectedToolbarIndex = -1;
    private Table selectedInventorySlot = null;

    public InventoryUI(Skin skin, Inventory inventory, Stage stage) {
        this.inventory = inventory;
        this.skin = skin;
        this.dragAndDrop = new DragAndDrop();
        this.setFillParent(true);

        errorTexture = new Texture("error.png");
        emptySlot = new Texture("emptySlot.png");
        pickedSlot = new Texture("pickedSlot.png");

        createInventoryTable();
        createToolbar(stage);

        stage.addActor(this);
    }

    private void createInventoryTable() {
        Table slotsTable = new Table();
        int columns = 5;

        for (Tool tool : inventory.getTools()) {
            Table slot = createSlot();
            Image toolImage = createImage(tool.getType().getTextureForLevel(tool.getLevel()));
            addDragSource(toolImage, tool.getType().name());
            slot.add(toolImage).size(64).center();
            slotsTable.add(slot).size(64).pad(5);
            if (slotsTable.getCells().size % columns == 0)
                slotsTable.row();
        }

        for (var slotData : inventory.getSlots()) {
            Table slot = createSlot();
            if (!slotData.isEmpty()) {
                Image itemImage = createImage(slotData.getItemType().getTexture());
                addDragSource(itemImage, slotData.getItemType().name());
                slot.add(itemImage).size(64).center();
            }
            slotsTable.add(slot).size(64).pad(5);
            if (slotsTable.getCells().size % columns == 0)
                slotsTable.row();
        }

        ScrollPane scrollPane = new ScrollPane(slotsTable, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);

        this.add(scrollPane).expand().fill().pad(10);
    }

    private Table createSlot() {
        Table slot = new Table();
        Image background = createImage(emptySlot);
        background.setFillParent(true);
        slot.addActor(background);
        slot.setUserObject(background);
        slotTables.add(slot);

        slot.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setPickedSlot(slot);
                return true;
            }
        });

        return slot;
    }

    private void setPickedSlot(Table selectedSlot) {
        selectedInventorySlot = selectedSlot;
        for (Table slot : slotTables) {
            Image background = (Image) slot.getUserObject();
            TextureRegion region = new TextureRegion(slot == selectedSlot ? pickedSlot : emptySlot);
            background.setDrawable(new TextureRegionDrawable(region));
        }
    }

    private Image createImage(Texture texture) {
        if (texture == null) texture = errorTexture;
        return new Image(new TextureRegionDrawable(new TextureRegion(texture)));
    }
    private Image getItemImageFromSlot(Table slot) {
        if (slot.getChildren().size <= 1) return null;
        for (Actor actor : slot.getChildren()) {
            if (actor instanceof Image && actor != slot.getUserObject()) {
                return (Image) actor;
            }
        }
        return null;
    }

    private void setSlotImage(Table slot, Image image) {
        slot.clearChildren();

        Image background = createImage(emptySlot);
        background.setFillParent(true);
        slot.addActor(background);
        slot.setUserObject(background);

        if (image != null) {
            image.setSize(64, 64);
            image.setScaling(Scaling.fit);
            image.setAlign(Align.center);
            slot.add(image).size(64).center();

            addDragSource(image, "");
        }
    }

    private void addDragSource(Image image, String payloadName) {
        dragAndDrop.addSource(new DragAndDrop.Source(image) {
            public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                DragAndDrop.Payload payload = new DragAndDrop.Payload();
                payload.setObject(payloadName);

                Image dragImage = new Image(image.getDrawable());
                dragImage.setSize(image.getWidth(), image.getHeight());
                payload.setDragActor(dragImage);

                return payload;
            }
        });
    }

    private void createToolbar(Stage stage) {
        Table toolbar = new Table();
        toolbar.bottom().padBottom(50);
        toolbar.setFillParent(true);

        int toolbarSlotCount = 9;
        for (int i = 0; i < toolbarSlotCount; i++) {
            Table slot = createToolbarSlot(i);
            toolbar.add(slot).size(64).pad(5);
        }

        stage.addActor(toolbar);
    }

    private Table createToolbarSlot(int index) {
        Table slot = new Table();
        Image background = createImage(emptySlot);
        background.setFillParent(true);
        slot.addActor(background);
        slot.setUserObject(background);
        toolbarSlots.add(slot);

        dragAndDrop.addTarget(new DragAndDrop.Target(slot) {
            @Override
            public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                return true;
            }

            @Override
            public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                slot.clearChildren();

                Image bg = createImage(emptySlot);
                bg.setFillParent(true);
                slot.addActor(bg);
                slot.setUserObject(bg);

                Image droppedImage = new Image(((Image) source.getActor()).getDrawable());
                droppedImage.setSize(64, 64);
                droppedImage.setScaling(Scaling.fit);
                droppedImage.setAlign(Align.center);
                slot.add(droppedImage).size(64).center();

                addDragSource(droppedImage, (String) payload.getObject());

                System.out.println("Dropped on toolbar slot " + index + ": " + payload.getObject());
            }
        });

        slot.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                selectToolbarSlot(index);
                return true;
            }
        });

        return slot;
    }

    private void selectToolbarSlot(int index) {
        for (int i = 0; i < toolbarSlots.size(); i++) {
            Table slot = toolbarSlots.get(i);
            Image bg = (Image) slot.getUserObject();
            TextureRegion region = new TextureRegion(i == index ? pickedSlot : emptySlot);
            bg.setDrawable(new TextureRegionDrawable(region));
        }
        selectedToolbarIndex = index;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode >= com.badlogic.gdx.Input.Keys.NUM_1 && keycode <= com.badlogic.gdx.Input.Keys.NUM_9) {
            int index = keycode - com.badlogic.gdx.Input.Keys.NUM_1;
            if (index < toolbarSlots.size()) {

                if (inventoryVisible && selectedInventorySlot != null) {

                    Table toolbarSlot = toolbarSlots.get(index);

                    Image invImage = getItemImageFromSlot(selectedInventorySlot);
                    Image toolImage = getItemImageFromSlot(toolbarSlot);

                    setSlotImage(selectedInventorySlot, toolImage);
                    setSlotImage(toolbarSlot, invImage);

                    selectToolbarSlot(index);

                    return true;
                } else {
                    selectToolbarSlot(index);
                    return true;
                }
            }
        }
        return false;
    }

    @Override public boolean keyUp(int i) { return false; }
    @Override public boolean keyTyped(char c) { return false; }
    @Override public boolean touchDown(int i, int i1, int i2, int i3) { return false; }
    @Override public boolean touchUp(int i, int i1, int i2, int i3) { return false; }
    @Override public boolean touchCancelled(int i, int i1, int i2, int i3) { return false; }
    @Override public boolean touchDragged(int i, int i1, int i2) { return false; }
    @Override public boolean mouseMoved(int i, int i1) { return false; }
    @Override public boolean scrolled(float v, float v1) { return false; }
}
