package models;

import models.character.Character;
import models.date.Date;
import models.map.Map;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static int idCounter=1;
    private int id;
    private Map map;
    private List<Character> allCharacters;
    private transient Thread gameThread ;
    private final int FPS = 60;
    private Date date;
    public Game(Map map, List<models.character.Character> characters){
        id=idCounter++;
        this.map = map;
        this.allCharacters = characters;
        this.date = new Date();
    }


    public void startGameThread(){
        gameThread = new Thread(String.valueOf(id));
        gameThread.start();
    }

    public void run(){
        double drawInterval = 1000000000 /FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null){
            // game loop here


            update();


            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if(remainingTime < 0 ){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void update(){

    }

    public int getId() {
        return id;
    }
}
