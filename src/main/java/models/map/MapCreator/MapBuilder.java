package models.map.MapCreator;

import models.Game;
import models.enums.TileType;
import models.enums.WeatherState;
import models.map.Map;
import models.map.Tile;
import models.map.Weather;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapBuilder {
    private static final int FARM_WIDTH = TileBuilder.WIDTH;
    private static final int FARM_HEIGHT = TileBuilder.HEIGHT;
    private static int cityWidth = 7;
    private static int cityHeight = 7;

    public static Map buildFullMap(int player1Farm , int player2Farm , int player3Farm , int player4Farm) {

        int fullWidth = FARM_WIDTH * 2 + cityWidth;
        int fullHeight = FARM_HEIGHT * 2 + cityHeight;

        String[][] fullMap = new String[fullHeight][fullWidth];


        placeFarm(fullMap, TileBuilder.buildMap(player1Farm), 0, 0);
        placeFarm(fullMap, TileBuilder.buildMap(player2Farm), 0, FARM_WIDTH + cityWidth);
        placeFarm(fullMap, TileBuilder.buildMap(player3Farm), FARM_HEIGHT + cityHeight, 0);
        placeFarm(fullMap, TileBuilder.buildMap(player4Farm), FARM_HEIGHT+cityHeight , FARM_WIDTH + cityWidth);


        buildCity(fullMap , fullWidth,fullHeight);
        Tile[][] tiles = new Tile[fullHeight][fullWidth];
        for (int y = 0; y < fullHeight; y++) {
            for (int x = 0; x < fullWidth; x++) {
                TileType tileType = TileType.getTypeByNumber(fullMap[y][x].trim());
                int ownerId = getOwnerIdForPosition(x, y, FARM_WIDTH, FARM_HEIGHT, cityWidth, cityHeight);
                tiles[y][x] = new Tile(x, y, tileType, null, ownerId);
            }
        }




        return new Map(tiles , new Weather(WeatherState.Sunny));
    }

    private static int getOwnerIdForPosition(int x, int y, int farmWidth, int farmHeight, int cityWidth, int cityHeight) {
        if (x < farmWidth && y < farmHeight) {
            return 0; // Player 1
        } else if (x >= farmWidth + cityWidth && y < farmHeight) {
            return 1; // Player 2
        } else if (x < farmWidth && y >= farmHeight + cityHeight) {
            return 2; // Player 3
        } else if (x >= farmWidth + cityWidth && y >= farmHeight + cityHeight) {
            return 3; // Player 4
        }
        return -1; // City
    }


    private static void placeFarm(String[][] fullMap, String[][] farm, int offsetY, int offsetX) {
        for (int y = 0; y < farm.length; y++) {
            for (int x = 0; x < farm[0].length; x++) {
                fullMap[y + offsetY][x + offsetX] = farm[y][x];
            }
        }
    }

    private static String[][] buildCity(String[][] fullMap,int width , int height) {

        File middleCity ;
        Scanner middleCityScanner;
        try {
         middleCity = new File("src/main/java/models/map/MapCreator/middleCity.txt");
         middleCityScanner = new Scanner(middleCity);

            int row = (height/2)-3;

            while (middleCityScanner.hasNextLine() && row < (height/2)+4) {
                String line = middleCityScanner.nextLine().trim();
                String[] cells = line.split("\\s+");

                for (int col = 0; col < line.length()/2+1; col++) {
                    fullMap[row][col] = cells[col];
                }

                row++;
            }

            middleCityScanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        File upperCity ;
        Scanner upperCityScanner ;
        try {
            upperCity = new File("src/main/java/models/map/MapCreator/upperCity.txt");
            upperCityScanner = new Scanner(upperCity);
            int row = 0;
            while (upperCityScanner.hasNextLine() && row<(height/2)-3){
                String line = upperCityScanner.nextLine().trim();
                String[] cells = line.split("\\s+");

                for (int col = 0; col < 7; col++) {
                    fullMap[row][col+(width/2)-3] = cells[col];
                }

                row++;
            }

        }catch (FileNotFoundException e) {
            System.out.println("upperCit.txt not found");
            throw new RuntimeException(e);
        }

        File lowerCity ;
        Scanner lowerCityScanner;
        try {
         lowerCity = new File("src/main/java/models/map/MapCreator/lowerCity.txt");
         lowerCityScanner = new Scanner(lowerCity);
            int row = (height/2) + 4;
            while (lowerCityScanner.hasNextLine() && row<height){
                String line = lowerCityScanner.nextLine().trim();
                String[] cells = line.split("\\s+");

                for (int col = 0; col < 7; col++) {
                    fullMap[row][col+(width/2)-3] = cells[col];
                }

                row++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("lowerCity.txt not found");
            throw new RuntimeException(e);
        }
        return fullMap;
    }
}
