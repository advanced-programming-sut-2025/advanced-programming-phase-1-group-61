package models.character;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.camera.Main;
import models.App;
import models.AssetManager;
import models.CollisionRect;
import models.animal.Animal;
import models.building.Building;
import models.enums.*;

import models.enums.graphic.BackgroundMusic;
import models.interactions.Iteractions;

import models.map.Map;
import models.resource.BuildingReference;
import models.resource.ShippingBin;
import models.tool.Tool;

import java.util.*;

public class Character {
    private  int userId;
    private  Inventory inventory ;
    private int energy;
    private boolean unlimitedEnergy=false;
    private Iteractions iteractions;
    private  Integer partner;
    private int x;
    private int y;
    private int speed;
    private  Skill skill;
    private Tool currentTool;
    private java.util.Map<String,Animal> animals = new HashMap<>();
    private ArrayList<Recipe> recipes=new ArrayList<>();
    private ArrayList<CookingRecipes> cookingRecipes = new ArrayList<>();
    private ArrayList<Cell> lastPath;
    private boolean isFainted ;
    private transient Sprite playerSprite;
    private int spriteX;
    private int spriteY;
    private CollisionRect collisionRect;
    private BackgroundMusic backgroundMusic=BackgroundMusic.WITHOUT_LOVE;
    private boolean hasGreenHouse;
    private Direction direction;
    private float animationTimer = 0f;
    private transient Animation<TextureRegion> currentAnimation;
    private boolean isMoving = false;
    private ItemType currentItem ;
    private int numOfQuest;
    private List<FriendShip> friendShipList;
    private ShippingBin shippingBin;

    public List<FriendShip> getFriendShipList() {
        if(friendShipList == null){
            friendShipList = new ArrayList<>();
            for (Character character : Main.getApp().getCurrentGame().getAllCharacters()) {
                if(character.getUserId() != userId){
                    friendShipList.add(new FriendShip(character.getUserId()));
                }
            }
        }
        return friendShipList;
    }

    private Buff buff=null;
    private int money;
    public Character(int userId){
        playerSprite = new Sprite(AssetManager.getPlayerTexture());
        playerSprite.setSize(96,128);
        spriteX = x*AssetManager.getTileSize();
        spriteY = y*AssetManager.getTileSize();
        playerSprite.setX(spriteX);
        playerSprite.setY(spriteY);
        this.userId=userId;
        this.currentTool=null;
        this.energy = 200;
        this.skill = new Skill();
        this.inventory = new Inventory();
        this.iteractions = new Iteractions(userId);
        cookingRecipes.add(CookingRecipes.FriedEgg);
        cookingRecipes.add(CookingRecipes.BakedFish);
        cookingRecipes.add(CookingRecipes.Salad);
        recipes.add(Recipe.Furnace);
        recipes.add(Recipe.Sprinkler);
        recipes.add(Recipe.CharcoalKlin);
        this.isFainted = false;
        this.money = 10000;
        this.speed = 10;
        this.hasGreenHouse = false;
        this.direction = Direction.RIGHT;
        this.currentItem = null;
        this.numOfQuest = 0;
        this.shippingBin = new ShippingBin();
    }

    public Character() {
    }

    public ItemType getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(ItemType currentItem) {
        this.currentItem = currentItem;
    }

    public boolean isFainted() {
        return isFainted;
    }

    public void setFainted(boolean fainted) {
        isFainted = fainted;
    }

    public Iteractions getIteractions() {
        return iteractions;
    }

    public void setCurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public Tool getCurrentTool(){
        return currentTool;
    }
    public Inventory getInventory() {
        return inventory;
    }

    public ArrayList<CookingRecipes> getCookingRecipes() {
        return cookingRecipes;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getEnergy() {
        return energy;
    }
    public void setEnergy(int energy) {
        this.energy = Math.min(200,energy);
        if(this.energy <= 0 ){
            this.energy = 0;

        }
    }
    public boolean isUnlimitedEnergy() {
        return unlimitedEnergy;
    }
    public void setUnlimitedEnergy(boolean unlimitedEnergy) {
        this.unlimitedEnergy = unlimitedEnergy;
    }
    public int getUserId() {
        return userId;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public int getMoney() {
        return money;
    }
    public void spend(int amount){
        money-=amount;
    }
    public void moveCharacter(){
        for(Cell cell:lastPath){
            this.setX(cell.getX());
            this.setY(cell.getY());
        }
    }
    public void setBuff(Buff buff){
        this.buff=buff;
    }
    public Buff getBuff(){
        return buff;
    }
    public Skill getSkill() {
        return skill;
    }

    public java.util.Map<String,Animal>getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal, String name) {
        if(!animals.containsKey(name)){
            animals.put(name,animal);
        }else{
            System.out.println("Name is already taken");
        }
    }


    public void findPath(int targetX, int targetY){
        lastPath=new ArrayList<>();
        System.gc();
        Map map= Main.getApp().getCurrentGame().getMap();
        if(map==null) return;
        if(map.getTiles()[targetY][targetX].getType().isCollisionOn()) return;
        Cell targetCell=bfs(targetX,targetY,map);
        if(targetCell==null) return;
        while(targetCell!=null){
            lastPath.add(targetCell);
            targetCell=targetCell.getPreviousCell();
        }
        Collections.reverse(lastPath);
        lastPath.remove(0);
    }
    public int getNeededEnergy(int targetX, int targetY) {
        findPath(targetX, targetY);
        if (lastPath == null || lastPath.isEmpty()) return -1;

        int numberOfTurns = 0;

        for (int i = 1; i < lastPath.size() - 1; i++) {
            Cell prev = lastPath.get(i - 1);
            Cell current = lastPath.get(i);
            Cell next = lastPath.get(i + 1);

            int dx1 = current.getX() - prev.getX();
            int dy1 = current.getY() - prev.getY();

            int dx2 = next.getX() - current.getX();
            int dy2 = next.getY() - current.getY();

            if (dx1 != dx2 || dy1 != dy2) {
                numberOfTurns++;
            }
        }

        System.out.println(numberOfTurns);
        System.out.println(lastPath.size());
        int energyRequired = (lastPath.size() + 10 * numberOfTurns) / 20;
        return energyRequired;
    }
    private Cell bfs(int targetX, int targetY,Map map){
        boolean[][] visited=new boolean[map.getHeightSize()][map.getWidthSize()];
        visited[getY()][getX()]=true;
        Character character=Main.getApp().getCurrentGame().getCurrentCharacter();
        Cell cell=new Cell()
                .setX(character.getX())
                .setY(character.getY())
                .setPreviousCell(null);
        Queue<Cell> cells=new ArrayDeque<>();
        cells.add(cell);
        int[] dx={1,0,-1,0};
        int[] dy={0,1,0,-1};
        while(!cells.isEmpty()){
            Cell lastCell=cells.poll();
            for(int i=0;i<dx.length;i++){
                int newX=lastCell.getX()+dx[i];
                int newY=lastCell.getY()+dy[i];
                if(newY<0 || newX<0 || newY>=map.getHeightSize() || newX>=map.getWidthSize()) continue;
                if(map.getTiles()[newY][newX].getType().isCollisionOn() ||
                        (map.getTiles()[newY][newX].getResource()!=null &&
                                !(map.getTiles()[newY][newX].getResource() instanceof BuildingReference) )){continue;
                }
                if(visited[newY][newX]) continue;
                visited[newY][newX]=true;
                Cell newCell=new Cell()
                        .setX(newX)
                        .setY(newY)
                        .setPreviousCell(lastCell);
                cells.add(newCell);
                if(newX==targetX && newY==targetY){
                    return newCell;
                }
            }
        }
        return null;
    }



    public int sellAnimal(String name){
        if(animals.containsKey(name)){
            this.money+=animals.get(name).getPrice();
            int Animalprice=animals.get(name).getPrice();
            animals.remove(name);
            return Animalprice;
        }
        return -1;
    }

    public Integer getPartner() {
        return partner;
    }

    public void setPartner(Integer partner) {
        this.partner = partner;
    }


    public int getSpeed() {
        return speed;
    }

    public Sprite getPlayerSprite() {
        if(playerSprite==null){
            playerSprite = new Sprite(AssetManager.getPlayerTexture());
            playerSprite.setSize(96,128);
            playerSprite.setX(x);
            playerSprite.setY(y);
        }
        return playerSprite;
    }
    public TextureRegion getCurrentFrame() {
            currentAnimation = AssetManager.getWalkAnimation(direction);
        if (isMoving) {
            return currentAnimation.getKeyFrame(animationTimer, true);
        } else {
            return currentAnimation.getKeyFrame(0);
        }
    }

    public void updateAnimation(Direction newDir, float deltaTime) {
        if (direction != newDir) {
            direction = newDir;
            currentAnimation = AssetManager.getWalkAnimation(direction);
            animationTimer = 0;
        } else {
            animationTimer += deltaTime;
        }
    }


    public int getSpriteX() {
        return spriteX;
    }

    public int getSpriteY() {
        return spriteY;
    }

    public void setSpriteX(int spriteX) {
        this.spriteX = spriteX;
        this.x = spriteX/AssetManager.getTileSize();
        collisionRect.setX(spriteX+32);
    }

    public void setSpriteY(int spriteY) {
        this.spriteY = spriteY;
        this.y= spriteY/AssetManager.getTileSize();
        collisionRect.setY(spriteY);
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }
    public void spawnCharacter(int x , int y){
        this.spriteX = x*AssetManager.getTileSize();
        this.spriteY = y*AssetManager.getTileSize();
        playerSprite.setX(spriteX);
        playerSprite.setY(spriteY);
        this.collisionRect = new CollisionRect(spriteX , spriteY , 48 , 64);
    }
    public void setBackgroundMusic(BackgroundMusic backgroundMusic) {
        this.backgroundMusic = backgroundMusic;
    }
    public BackgroundMusic getBackgroundMusic() {
        return backgroundMusic;
    }

    public boolean hasGreenHouse() {
        return hasGreenHouse;
    }

    public void setHasGreenHouse(boolean hasGreenHouse) {
        this.hasGreenHouse = hasGreenHouse;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public int getNumberOfQuest() {
        return numOfQuest;
    }
    public int getSkillLevel(){
        int total =0;
        total += skill.getFishingLVL();
        total += skill.getFarmingLVL();
        total+= skill.getMiningLVL();
        total += skill.getForagingLVL();
        total += skill.getForagingLVL();
        return total;
    }
    public boolean checkRecipeAvailability(Recipe recipe){
        return recipes.contains(recipe);
    }

    public ShippingBin getShippingBin() {
        return shippingBin;
    }
}
