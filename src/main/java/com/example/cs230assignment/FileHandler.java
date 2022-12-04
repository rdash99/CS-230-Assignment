package com.example.cs230assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class defines the FileHandler methods. This is used to read and write
 * files, saving and instantiating the game.
 * 
 * @author Rowan Dash
 * @version 1.0
 */

public class FileHandler {

    /**
     * @param fileName The level file to process
     * @return The instance of board for that level
     */
    private static Board loadBoard(File fileName) {
        Scanner in = null;
        try {
            in = new Scanner(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Could not find " + fileName);
            System.exit(0);
        }
        int x;
        int y;
        int levelTime = 0;
        String sizeLine = in.nextLine();
        String[] size = sizeLine.split(" ");
        Tile[][] tiles = new Tile[Integer.parseInt(size[0])][Integer
                .parseInt(size[1])];
        ArrayList<Entity> entities = new ArrayList<Entity>();
        Player player = null;
        x = Integer.parseInt(size[0]);
        y = Integer.parseInt(size[1]);

        int yNum = y - 1;
        while (in.hasNextLine()) {
            String line = in.nextLine().toLowerCase();
            String[] lineArray = line.split(" ");
            int xNum = x - 1;

            for (int i = 0; i < lineArray.length; i++) {
                // read in tile colours
                if (lineArray[i].length() == 4) {
                    tiles[xNum][yNum] = new Tile(lineArray[i].charAt(0),
                            lineArray[i].charAt(1), lineArray[i].charAt(2),
                            lineArray[i].charAt(3));
                    xNum = xNum - 1;
                }
                // read in entities, players and items
                if (lineArray[i].length() == 3) {
                    int xCoord = 0;
                    int yCoord = 0;
                    try {
                        xCoord = Integer.parseInt(lineArray[i - 2]);
                        yCoord = Integer.parseInt(lineArray[i - 1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    // read in player
                    if (lineArray[i].equals("ply")) {
                        Player player1 = new Player(xCoord, yCoord);
                        player = player1;
                    }
                    // read in a floor following thief
                    if (lineArray[i].equals("fft")) {
                        char colour = lineArray[i + 3].charAt(0);
                        FloorFollowingThief fft = new FloorFollowingThief(
                                colour, xCoord, yCoord);
                        entities.add(fft);
                    }
                    // read in a flying assassin
                    if (lineArray[i].equals("fla")) {
                        int xdir = Integer.parseInt(lineArray[i + 1]);
                        int ydir = Integer.parseInt(lineArray[i + 2]);
                        FlyingAssassin fly = new FlyingAssassin(0.5, xCoord,
                                yCoord, xdir, ydir);
                        entities.add(fly);
                    }
                    // read in a smart thief
                    if (lineArray[i].equals("smt")) {
                        entities.add(new SmartThief(xCoord, yCoord, 0.5));
                    }
                    // read in a gate
                    if (lineArray[i].equals("gte")) {
                        char colour = lineArray[i + 1].charAt(0);
                        entities.add(new Gate(colour, xCoord, yCoord));
                    }
                    // read in a key
                    if (lineArray[i].equals("key")) {
                        char colour = lineArray[i + 1].charAt(0);
                        entities.add(new Key(colour, xCoord, yCoord));
                    }
                    // read in doors
                    if (lineArray[i].equals("dor")) {
                        entities.add(new Door(xCoord, yCoord));
                    } // read in level time
                    else {
                        try {
                            levelTime = Integer.parseInt(lineArray[i]);
                        } catch (NumberFormatException e) {
                        }
                    }
                    ;
                }

            }
            yNum = yNum - 1;
        }
        in.close();
        if (player == null) {
            throw new IllegalArgumentException("No player found in file");
        }
        if (levelTime == 0) {
            throw new IllegalArgumentException("No level time found in file");
        }
        Timer timer = new Timer(levelTime);
        // flip the tiles array along x axis
        Tile[][] tiles2 = new Tile[tiles.length][tiles[0].length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                tiles2[i][j] = tiles[tiles.length - 1 - i][tiles[0].length - 1 - j];
            }
        }
        // flip the tiles array 90 degrees again
        Tile[][] tiles3 = new Tile[tiles2.length][tiles2[0].length];
        for (int i = 0; i < tiles2.length; i++) {
            for (int j = 0; j < tiles2[0].length; j++) {
                tiles3[i][j] = tiles2[tiles2.length - 1 - j][i];
            }
        }
        Tile[][] tiles4 = new Tile[tiles3.length][tiles3[0].length];
        for (int i = 0; i < tiles3.length; i++) {
            for (int j = 0; j < tiles3[0].length; j++) {
                tiles4[i][j] = tiles3[j][i];
            }
        }
        // flip the tiles array 90 degrees yet again

        Tile[][] tiles5 = new Tile[tiles4.length][tiles4[0].length];
        for (int i = 0; i < tiles4.length; i++) {
            for (int j = 0; j < tiles4[0].length; j++) {
                tiles5[i][j] = tiles4[tiles4.length - 1 - j][i];
            }
        }
        return new Board(x, y, tiles5, entities, player, timer);
    }

    private static void saveBoard(Board board, String fileName) {
        // characters = board.getCharacters();
    }

    private static void savePlayerData(String playerID, int score, int level) {
    }

    private static Character loadPlayerData(String charData) {
        return null;
    }

    private static String saveItem(Item item) {
        return null;
    }

    private static Item loadItem(String itemName, int x, int y, int value) {
        return new Item(itemName, x, y, value);
    }

    /**
     * @param fileName
     * @return Board
     */
    public static Board readLevelFile(String fileName) {
        File file = new File(fileName + ".txt");
        return loadBoard(file);
    }

    public static void saveGame(Board board, String fileName) {
        File saveFile = new File(fileName);
        saveBoard(board, fileName);
    }

    /**
     * @return String[]
     */
    public static String[] readLevelNameStrings() {
        File file = new File("levels.txt");
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Could not find levels.txt");
            System.exit(0);
        }
        ArrayList<String> levelNames = new ArrayList<String>();
        while (in.hasNextLine()) {
            levelNames.add(in.nextLine());
        }
        in.close();
        String[] levelNamesArray = new String[levelNames.size()];
        levelNamesArray = levelNames.toArray(levelNamesArray);
        return levelNamesArray;
    }
}
