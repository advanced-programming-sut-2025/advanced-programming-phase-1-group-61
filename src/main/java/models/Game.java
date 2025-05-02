package models;

import models.character.Character;
import models.date.Date;
import models.map.Map;

import java.util.List;

public class Game implements Runnable {


    private int id;
    private Map map;
    private List<Character> allCharacters;
    private int currentCharacter;
    private transient Thread gameThread;
    private final int FPS = 60;
    private Date date;
    private volatile boolean running = false;

    public Game(Map map, List<Character> characters) {
        id = App.getAllGames().size()+1;
        this.map = map;
        this.allCharacters = characters;
        this.date = new Date();
    }

    public void startGameThread() {
        if (gameThread == null || !running) {
            running = true;
            gameThread = new Thread(this, "GameThread-" + id);
            gameThread.start();
        }
    }

    public void stopGameThread() {
        running = false;
        try {
            if (gameThread != null) {
                gameThread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (running) {
            update();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {

    }

    public int getId() {
        return id;
    }

    public Character getCurrentCharacter() {
        return allCharacters.get(currentCharacter);
    }

    public String changeTurn() {
        int turn = currentCharacter+1;
        this.currentCharacter = turn % allCharacters.size();
        StringBuilder message =  new StringBuilder(User.getUSerById(allCharacters.get(currentCharacter).getUserId()).getNickName());

        if(turn == allCharacters.size()){
            message.append("\n");
            message.append("time increased");
            date.increaseTime(1);
        }
        return message.toString();
    }

    public Date getDate() {
        return date;
    }
}
