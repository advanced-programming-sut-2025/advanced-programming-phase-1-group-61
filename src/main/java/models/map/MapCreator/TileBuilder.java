package models.map.MapCreator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TileBuilder {
    public static int WIDTH = 36;
    public static int HEIGHT = 20;

//    public static String[][] buildDefaultFarm()  {
//        String[][] farm = new String[HEIGHT][WIDTH];
//        for (int y = 0; y < HEIGHT; y++) {
//            for (int x = 0; x < WIDTH; x++) {
//                farm[y][x] = "0";
//            }
//        }
//        for (int y = 0; y < 6; y++) {
//            for (int x = 0; x < 5; x++) {
//                farm[y][x] = "4";
//            }
//        }
//        for (int y = 16; y < 20; y++) {
//            for (int x = 7; x < 20; x++) {
//                farm[y][x] = "5";
//            }
//        }
//        return farm;
//    }
    public static String[][] buildMap(int mapNumber)  {
            File map;
            Scanner mapScanner;
            String[][] farm = new String[HEIGHT][WIDTH];
            if(mapNumber==1) {
                try {
                    map = new File("models/map/MapCreator/map1.txt");
                    mapScanner = new Scanner(map);
                    int row = 0;
                    while (mapScanner.hasNextLine() && row < HEIGHT) {
                        String line = mapScanner.nextLine().trim();
                        String[] cells = line.split("\\s+");
                        for (int col = 0; col < Math.min(cells.length, WIDTH); col++) {
                            farm[row][col] = cells[col];
                        }
                        row++;
                    }
                    mapScanner.close();
                } catch (FileNotFoundException e) {
                    System.out.println("map1.txt was not found");
                    return null;
                }
            } else if (mapNumber==2) {
                try {
                    map = new File("models/map/MapCreator/map2.txt");
                    mapScanner = new Scanner(map);
                    int row = 0;
                    while (mapScanner.hasNextLine() && row < HEIGHT) {
                        String line = mapScanner.nextLine().trim();
                        String[] cells = line.split("\\s+");
                        for (int col = 0; col < Math.min(cells.length, WIDTH); col++) {
                            farm[row][col] = cells[col];
                        }
                        row++;
                    }
                    mapScanner.close();
                } catch (FileNotFoundException e) {
                    System.out.println("map2.txt was not found");
                    return null;
                }
            }
        return farm;
    }
}
