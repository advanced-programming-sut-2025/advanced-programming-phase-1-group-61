package models;

import java.util.Random;

public class RandomNumber {
    private static final Random random = new Random();

    public static int getRandomNumberWithBoundaries(int origin , int bound) {
        return random.nextInt(origin, bound);
    }

    public static int getRandomNumber() {
        return random.nextInt();
    }
}
