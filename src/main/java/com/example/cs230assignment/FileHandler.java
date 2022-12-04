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
            System.out.println("Could not find" + fileName);
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

        int yNum = y * (-1);
        while (in.hasNextLine()) {
            String line = in.nextLine().toLowerCase();
            String[] lineArray = line.split(" ");
            int xNum = x * (-1);
            yNum = yNum + 1;
            for (int i = 0; i < lineArray.length; i++) {
                // read in tile colours
                if (lineArray[i].length() == 4) {
                    tiles[xNum][yNum] = new Tile(lineArray[i].charAt(0),
                            lineArray[i].charAt(1), lineArray[i].charAt(2),
                            lineArray[i].charAt(3));
                }
                // read in entities, players and items
                if (lineArray[i].length() == 3) {
                    int xCoord = Integer.parseInt(lineArray[i - 2]);
                    int yCoord = Integer.parseInt(lineArray[i - 1]);

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

                    if (lineArray[i].equals("smt")) {
                        entities.add(new SmartThief(xCoord, yCoord, 0.5));
                    }

                    if (lineArray[i].equals("gte")) {
                        char colour = lineArray[i + 1].charAt(0);
                        entities.add(new Gate(colour, xCoord, yCoord));
                    }
                    if (lineArray[i].equals("key")) {
                        char colour = lineArray[i + 1].charAt(0);
                        entities.add(new Key(colour, xCoord, yCoord));
                    }
                    if (lineArray[i].equals("dor")) {
                        entities.add(new Door(xCoord, yCoord));
                    }
                }
                if (lineArray[i].length() == 1) {
                    levelTime = Integer.parseInt(lineArray[i]);
                }
                xNum = xNum + 1;
            }
        }
        in.close();
        if (player == null) {
            throw new IllegalArgumentException("No player found in file");
        }
        if (levelTime == 0) {
            throw new IllegalArgumentException("No level time found in file");
        }
        return new Board(x, y, tiles, entities, player, levelTime);
    }

    private static void saveBoard(Board board, String fileName) {
        // characters = board.getCharacters();
    }

    private static String saveCharacter(Character character) {
        return null;
    }

    private static Character loadCharacter(String charData) {
        return null;
    }

    private static String saveItem(Item item) {
        return null;
    }

    private static Item loadItem(String itemName, int x, int y, int value) {
        return new Item(itemName, x, y, value);
    }

    public static Board readLevelFile(String fileName) {
        File file = new File(fileName);
        return loadBoard(file);
    }

    public static void saveGame(Board board, String fileName) {
        File saveFile = new File(fileName);
        saveBoard(board, fileName);
    }
}
