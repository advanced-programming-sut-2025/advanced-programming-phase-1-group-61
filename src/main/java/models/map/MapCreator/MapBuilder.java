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
        int i =0, j =0;
        for (String[] strings : fullMap) {
            for (String string : strings) {
                tiles[j][i] = new Tile(i, j, TileType.getTypeByNumber(Integer.parseInt(string.trim())), null);
                i++;
            }
            i=0;
            j++;
        }


        return new Map(tiles , new Weather(WeatherState.Sunny));
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
//        for (int x = 0; x < width; x++) {
//            for (int y = 0; y < height/2-3; y++) {
//               if(fullMap[y][x]==null){
//                   fullMap[y][x]="1";
//               }
//            }
//        }
//        for (int x = 0; x < width; x++) {
//            for (int y = height/2-3; y < height/2+4 ; y++) {
//                if(fullMap[y][x]==null){
//                    fullMap[y][x]="9";
//                }
//            }
//        }

//        for (int x = 0; x < width; x++) {
//            for (int y = height/2+4; y < height; y++) {
//                if(fullMap[y][x]==null){
//                    fullMap[y][x] = "3";
//                }
//            }
//        }



        return fullMap;
    }
}
