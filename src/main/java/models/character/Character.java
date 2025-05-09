package models.character;

import models.App;
import models.Item;
import models.User;
import models.animal.Animal;
import models.enums.Recipe;
import models.map.Map;
import models.tool.Backpack;
import models.tool.Tool;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class Character {
    private int userId;
    private Inventory inventory;
    private final Backpack backpack=new Backpack();
    private int energy;
    private int x;
    private int y;
    private Skill skill;
    private Tool currentTool;
    private ArrayList<Animal> animals=new ArrayList<>();
    private ArrayList<Recipe> recipes=new ArrayList<>();
    private ArrayList<Cell> lastPath;
    public Character(int userId){
        this.userId=userId;
        currentTool=null;
        this.energy = 200;
    }
    public void setTool(Tool newTool){
        currentTool=newTool;
    }
    public Tool getCurrentTool(){
        return currentTool;
    }
    public Backpack getBackpack() {
        return backpack;
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

    public int getUserId() {
        return userId;
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
        return lastPath.size()/20;
    }
    private Cell bfs(int targetX, int targetY,Map map){
        System.out.println(map.getHeightSize() + " "+map.getWidthSize());
        boolean[][] visited=new boolean[map.getHeightSize()][map.getWidthSize()];
        visited[getY()][getX()]=true;
        Cell cell=new Cell()
                .setX(targetX)
                .setY(targetY)
                .setPreviousCell(null);
        Queue<Cell> cells=new ArrayDeque<>();
        cells.add(cell);
        while(!cells.isEmpty()){
            Cell lastCell=cells.poll();
            for(int i=0;i<4;i++){
                int[] dx={1,0,-1,0,1,1,-1,-1};
                int[] dy={0,1,0,-1,1,-1,1,-1};
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
