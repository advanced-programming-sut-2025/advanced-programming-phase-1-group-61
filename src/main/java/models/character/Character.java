package models.character;

import models.App;
import models.Item;
import models.animal.Animal;
import models.character.Skill.*;
import models.enums.Recipe;
import models.map.Map;
import models.tool.Tool;

import java.util.*;

public class Character {
    private int userId;
    private final Inventory inventory ;
    private int energy;
    private boolean unlimitedEnergy=false;
    private int x;
    private int y;
    private Skill skill;
    private Tool currentTool;
    private ArrayList<Animal> animals=new ArrayList<>();
    private ArrayList<Recipe> recipes=new ArrayList<>();
    private ArrayList<Cell> lastPath;
    private int money;
    public Character(int userId){
        this.userId=userId;
        this.currentTool=null;
        this.energy = 200;
        this.skill = new Skill();
        this.inventory = new Inventory();
    }

    public void setTool(Tool newTool){
        currentTool=newTool;
    }
    public Tool getCurrentTool(){
        return currentTool;
    }
    public Inventory getInventory() {
        return inventory;
    }
    public void upgradeTool(Tool desiredTool){

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveX(int x){
        this.x+=x;
    }

    public void moveY(int y){
        this.y+=y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void faint(){
        //TODO

    }
    public Item fish(){
        return null;
    }
    public void trade(Character character,Item item,int quantity){
        //TODO
    }
    public void artisanUse(){}
    public void craft(){}
    public void cook(){}
    public int getEnergy() {
        return energy;
    }
    public void setEnergy(int energy) {
        this.energy = Math.min(200,energy);
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
    public void moveCharacter(){
        for(Cell cell:lastPath){
            this.setX(cell.getX());
            this.setY(cell.getY());
        }
    }

    public void findPath(int targetX, int targetY){
        lastPath=new ArrayList<>();
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
        lastPath.remove(0); //remove the cell where user stands at the moment
    }
    public int getNeededEnergy(int targetX, int targetY){
        findPath(targetX,targetY);
        int numberOfTurns=0;
        for(int i=0;i<lastPath.size();i++){
            if(i==lastPath.size()-1) break;
            Cell cell1=lastPath.get(i);
            Cell cell2=lastPath.get(i+1);
            if((cell2.getX()>cell1.getX() && cell2.getY()>cell1.getY()) ||
                    (cell2.getX()>cell1.getX() && cell2.getY()<cell1.getY()) ||
                    (cell1.getX()>cell2.getX() && cell1.getY()>cell2.getY()) ||
                    (cell1.getX()>cell2.getX() && cell1.getY()<cell2.getY())) numberOfTurns++;
        }
        return (lastPath.size()+numberOfTurns*10)/20;
    }
    private Cell bfs(int targetX, int targetY,Map map){
        boolean[][] visited=new boolean[map.getHeightSize()][map.getWidthSize()];
        visited[getY()][getX()]=true;
        Cell cell=new Cell()
                .setX(targetX)
                .setY(targetY)
                .setPreviousCell(null);
        Queue<Cell> cells=new ArrayDeque<>();
        cells.add(cell);
        int[] dx={1,0,-1,0,1,1,-1,-1};
        int[] dy={0,1,0,-1,1,-1,1,-1};
        while(!cells.isEmpty()){
            Cell lastCell=cells.poll();
            for(int i=0;i<dx.length;i++){
                int newX=lastCell.getX()+dx[i];
                int newY=lastCell.getY()+dy[i];
                if(newY<0 || newX<0 || newY>=map.getHeightSize() || newX>=map.getWidthSize()) continue;
                if(map.getTiles()[newY][newX].getType().isCollisionOn() ||
                        map.getTiles()[newY][newX].getResource()!=null) continue;
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
}