package views;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import io.github.camera.Main;
import models.App;
import models.character.Character;
import models.character.InventorySlot;
import models.enums.ItemType;
import models.enums.TileType;
import models.map.Tile;
import models.tool.Axe;
import models.tool.Tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryUI extends Table implements InputProcessor {

    private final Character character;
    private final Skin skin;

    private final Texture errorTexture;
    private final Texture emptySlot;
    private final Texture pickedSlot;
    private ToolbarUI toolbarUI;

    private final List<Table> slotTables = new ArrayList<>();
    private final Map<Table, Image> slotBackgrounds = new HashMap<>();
    private Table selectedInventorySlot = null;
    private boolean inventoryVisible = false;

    public void setInventoryVisible(boolean inventoryVisible) {
        this.inventoryVisible = inventoryVisible;
    }

    public InventoryUI(Skin skin, Character character, Stage stage) {
        this.character = character;
        this.skin = skin;

        this.setFillParent(true);

        errorTexture = new Texture("error.png");
        emptySlot = new Texture("emptySlot.png");
        pickedSlot = new Texture("pickedSlot.png");

        createInventoryUI();

        stage.addActor(this);
    }

    public void setToolbarUI(ToolbarUI toolbarUI) {
        this.toolbarUI = toolbarUI;
    }

    private void createInventoryUI() {
        this.clear();
        this.top();

        slotTables.clear();
        slotBackgrounds.clear();

        Table buttonsTable = new Table();
        buttonsTable.defaults().pad(5);

        TextButton repairButton = new TextButton("Repair Greenhouse", skin);
        TextButton journalButton = new TextButton("Journal", skin);
        TextButton skillsButton = new TextButton("Skills", skin);

        if (character.hasGreenHouse()) {
            repairButton.setVisible(false);
        }

        repairButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                character.setHasGreenHouse(true);
                repairButton.setVisible(false);
                for (Tile[] tiles : Main.getApp().getCurrentGame().getMap().getTiles()) {
                    for (Tile tile : tiles) {
                        if (tile.getOwnerId() == character.getUserId()) {
                            if (tile.getType().equals(TileType.BrokenGreenHouse)) {
                                tile.setType(TileType.GreenHouse);
                            } else if (tile.getType().equals(TileType.BrokenGreenHouseWall)) {
                                tile.setType(TileType.CabinWall);
                            }
                        }
                    }
                }
            }
        });

        journalButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new JournalView());
            }
        });

        skillsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SkillView skillView = new SkillView(skin);
                skillView.show(getStage());
            }
        });

        buttonsTable.add(journalButton);
        buttonsTable.add(skillsButton);
        buttonsTable.add(repairButton);

        this.add(buttonsTable).padTop(10).row();

        Table slotsTable = new Table();
        int columns = 5;

        for (InventorySlot slotData : character.getInventory().getSlots()) {
            Table slot = createSlot(slotData);
            slotTables.add(slot);
            slotsTable.add(slot).size(64).pad(5);
            if (slotsTable.getCells().size % columns == 0)
                slotsTable.row();
        }

        ScrollPane scrollPane = new ScrollPane(slotsTable, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);

        this.add(scrollPane).expand().fill().pad(10).row();
    }

    private Table createSlot(InventorySlot slotData) {
        Table slot = new Table();
        Image background = createImage(emptySlot);
        background.setFillParent(true);
        slot.addActor(background);
        slotBackgrounds.put(slot, background);

        if (slotData.getObjectInSlot() != null) {
            Object obj = slotData.getObjectInSlot();
            Texture texture = getTextureForObject(obj);
            Image image = createImage(texture);
            image.setTouchable(Touchable.enabled);

            Label countLabel = new Label(String.valueOf(slotData.getCount()), skin);
            Stack stack = new Stack();
            stack.add(image);
            stack.add(countLabel);
            stack.setTouchable(Touchable.enabled);

            slot.add(stack).size(64).center();

        }



        slot.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (button == 0) {
                    setPickedSlot(slot);

                    if (slotData.getObjectInSlot() instanceof Tool) {
                        Tool selectedTool = (Tool) slotData.getObjectInSlot();
                        character.setCurrentTool(selectedTool);
                        System.out.println("Current tool set to: " + selectedTool.getType());
                    } else {
                        character.setCurrentTool(null);
                    }

                } else if (button == 1) {
                    if (selectedInventorySlot != null && selectedInventorySlot != slot) {
                        InventorySlot sourceSlotData = getSlotDataFromTable(selectedInventorySlot);
                        InventorySlot targetSlotData = slotData;

                        List<InventorySlot> inventory = character.getInventory().getSlots();

                        int sourceIndex = inventory.indexOf(sourceSlotData);
                        int targetIndex = inventory.indexOf(targetSlotData);

                        if (sourceIndex != -1 && targetIndex != -1) {
                            inventory.set(sourceIndex, targetSlotData);
                            inventory.set(targetIndex, sourceSlotData);
                        }

                        refreshUI();

                        selectedInventorySlot = null;
                    }
                }
                return true;
            }
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.N && selectedInventorySlot != null) {
                    System.out.println("salam");
                    InventorySlot slotData = getSlotDataFromTable(selectedInventorySlot);

                    if (slotData != null && slotData.getObjectInSlot() instanceof ItemType) {
                        ItemType itemType = (ItemType) slotData.getObjectInSlot();
                        int count = slotData.getCount();

                        Main.getApp()
                            .getCurrentGame()
                            .getCurrentCharacter()
                            .getInventory()
                            .getTrashcan()
                            .removeItem(itemType, count);

                        refreshUI();
                        return true;
                    }
                }
                return false;
            }
        });

        return slot;
    }

    private InventorySlot getSlotDataFromTable(Table slotTable) {

        int index = slotTables.indexOf(slotTable);
        if (index != -1 && index < character.getInventory().getSlots().size()) {
            return character.getInventory().getSlots().get(index);
        }
        return null;
    }



    private void setPickedSlot(Table selectedSlot) {
        selectedInventorySlot = selectedSlot;
        for (Table slot : slotTables) {
            Image bg = slotBackgrounds.get(slot);
            if (bg != null) {
                TextureRegion region = new TextureRegion(slot == selectedSlot ? pickedSlot : emptySlot);
                bg.setDrawable(new TextureRegionDrawable(region));
            }
        }
    }

    private Texture getTextureForObject(Object obj) {
        if (obj instanceof ItemType) {
            return ((ItemType) obj).getTexture();
        } else if (obj instanceof Tool) {
            return ((Tool) obj).getType().getTextureForLevel(((Tool) obj).getLevel());
        } else {
            System.out.println(obj.toString());
            return errorTexture;
        }
    }

    private Image createImage(Texture texture) {
        if (texture == null) texture = errorTexture;
        return new Image(new TextureRegionDrawable(new TextureRegion(texture)));
    }

    public void refreshUI() {
        createInventoryUI();
        toolbarUI.updateSlotsUI();
        toolbarUI.refreshUI();
    }

    @Override
    public boolean keyDown(int keycode) { return false; }
    @Override
    public boolean keyUp(int i) { return false; }
    @Override
    public boolean keyTyped(char c) { return false; }
    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) { return false; }
    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) { return false; }
    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) { return false; }
    @Override
    public boolean touchDragged(int i, int i1, int i2) { return false; }
    @Override
    public boolean mouseMoved(int i, int i1) { return false; }
    @Override
    public boolean scrolled(float v, float v1) { return false; }



}
