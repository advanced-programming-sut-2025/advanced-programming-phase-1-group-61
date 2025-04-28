package models;

import java.util.Random;

public class RandomNumber {
    private static Random random = new Random();


    public static int getRandomNumberWithBoundaries(int origin, int bound) {
        return random.nextInt(bound - origin) + origin;
    }

    public static int getRandomNumber() {
        return random.nextInt();
    }
}
