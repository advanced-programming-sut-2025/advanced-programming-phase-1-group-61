package models.character;

import com.badlogic.gdx.graphics.g2d.Sprite;
import models.App;
import models.AssetManager;
import models.CollisionRect;
import models.Item;
import models.NPC.NPC;
import models.NPC.NPCFriendships;
import models.animal.Animal;
import models.building.Building;
import models.building.Shop;
import models.enums.CookingRecipes;
import models.enums.Recipe;
import models.enums.ToolType;

import models.interactions.Iteractions;

import models.map.Map;
import models.map.Tile;
import models.resource.BuildingReference;
import models.tool.Tool;
import models.workBench.WorkBench;

import java.util.*;

public class Character {
    private final int userId;
    private final Inventory inventory ;
    private int energy;
    private boolean unlimitedEnergy=false;
    private Iteractions iteractions;
    private  Integer partner;
    private int x;
    private int y;
    private int speed;
    private final Skill skill;
    private Tool currentTool;
    private java.util.Map<String,Animal> animals = new HashMap<>();
    private ArrayList<Building> buildings =new ArrayList<>();
    private ArrayList<WorkBench> workBenches =new ArrayList<>();
    private ArrayList<Recipe> recipes=new ArrayList<>();
    private ArrayList<CookingRecipes> cookingRecipes = new ArrayList<>();
    private ArrayList<Cell> lastPath;
    private int xRefrigerator , yRefrigerator;
    private boolean isFainted ;
    private transient Sprite playerSprite;
    private int spriteX;
    private int spriteY;
    private CollisionRect collisionRect;

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


    public void setTool(ToolType newTool){
        for (Tool tool : this.inventory.getTools()) {
            if(tool.getType().equals(newTool)){
                this.currentTool = tool;
            }
        }
    }
    public Shop getCurrentShop(){
       Map map = App.getCurrentGame().getMap();
        Tile tile = map.getTileByCordinate(x,y);
        if(tile.getResource() instanceof BuildingReference buildingReference){
          String name = buildingReference.getName();
          return App.getCurrentGame().getShopByName(name);
        }
        return null;
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

    public int getxRefrigerator() {
        return xRefrigerator;
    }

    public void setxRefrigerator(int xRefrigerator) {
        this.xRefrigerator = xRefrigerator;
    }

    public int getyRefrigerator() {
        return yRefrigerator;
    }

    public void setyRefrigerator(int yRefrigerator) {
        this.yRefrigerator = yRefrigerator;
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
    public ArrayList<Building> getBuildings() {
        return buildings;
    }
    public void addAnimal(Animal animal, String name) {
        if(!animals.containsKey(name)){
            animals.put(name,animal);
        }else{
            System.out.println("Name is already taken");
        }
    }
    public void pet(String name){
        if (!animals.containsKey(name)) {
            System.out.println("You do not have "+name+" pet youre own animals");
            return;
        }
            animals.get(name).pet(this.x,this.y);
    }

    public void findPath(int targetX, int targetY){
        lastPath=new ArrayList<>();
        System.gc();
        Map map= App.getCurrentGame().getMap();
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
        Character character=App.getCurrentGame().getCurrentCharacter();
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

    public Building getBuilding(String name){
        for (Building building : buildings) {
            if(building.getName().equals(name)) return building;
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
    public List<WorkBench> getWorkBenches(){
        return workBenches;
    }

    public Integer getPartner() {
        return partner;
    }

    public void setPartner(Integer partner) {
        this.partner = partner;
    }
    public NPC getNPC(){
        ArrayList<NPC> allNPCs=NPC.getAllNPCs();
        for(NPC npc:allNPCs){
            int delta_x=npc.getX()-this.getX();
            int delta_y=npc.getY()-this.getY();
            int[] dx={1,0,-1,0,1,1,-1,-1};
            int[] dy={0,1,0,-1,1,-1,1,-1};
            for(int i=0;i<dx.length;i++){
                if(dx[i]==delta_x && dy[i]==delta_y) return npc;
            }
        }
        return null;
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
}
