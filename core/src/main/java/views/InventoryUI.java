package views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

public class InventoryUI extends Table {

    private final Inventory inventory;
    private final Skin skin;
    private final DragAndDrop dragAndDrop;
    private final Texture errorTexture;
    private final Texture emptySlot;
    private final Texture pickedSlot;

    private final List<Table> slotTables = new ArrayList<>();

    public InventoryUI(Skin skin, Inventory inventory, Stage stage) {
        this.inventory = inventory;
        this.skin = skin;
        this.dragAndDrop = new DragAndDrop();
        this.setFillParent(true);

        errorTexture = new Texture("error.png");
        emptySlot = new Texture("emptySlot.png");
        pickedSlot = new Texture("pickedSlot.png");

        createInventoryTable();

        stage.addActor(this);
    }

    private void createInventoryTable() {
        Table slotsTable = new Table();
        int columns = 5;

        for (Tool tool : inventory.getTools()) {
            Table slot = createSlot();
            Image toolImage = createImage(tool.getType().getTextureForLevel(tool.getLevel()));
            addDragAndDrop(toolImage, tool.getType().name());
            slot.add(toolImage).size(64).center();
            slotsTable.add(slot).size(64).pad(5);
            if (slotsTable.getCells().size % columns == 0)
                slotsTable.row();
        }

        for (var slotData : inventory.getSlots()) {
            Table slot = createSlot();
            if (!slotData.isEmpty()) {
                Image itemImage = createImage(slotData.getItemType().getTexture());
                addDragAndDrop(itemImage, slotData.getItemType().name());
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
        background.setScaling(Scaling.fit);
        background.setFillParent(true);
        slot.addActor(background);

        // ذخیره‌ی جدول و پس‌زمینه‌اش
        slot.setUserObject(background);
        slotTables.add(slot);

        // Listener برای کلیک
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
        for (Table slot : slotTables) {
            Image background = (Image) slot.getUserObject();
            TextureRegion region = new TextureRegion(slot == selectedSlot ? pickedSlot : emptySlot);
            background.setDrawable(new TextureRegionDrawable(region));
        }
    }

    private Image createImage(Texture texture) {
        if (texture == null) {
            texture = errorTexture;
        }
        TextureRegion region = new TextureRegion(texture);
        Image image = new Image(new TextureRegionDrawable(region));
        image.setScaling(Scaling.fit);
        image.setAlign(Align.center);
        return image;
    }

    private void addDragAndDrop(Image image, String payloadName) {
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

        dragAndDrop.addTarget(new DragAndDrop.Target(image) {
            public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                return true;
            }

            public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                System.out.println("Dropped: " + payload.getObject());
            }
        });
    }
}
