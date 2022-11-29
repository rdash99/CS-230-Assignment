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
            String line = in.nextLine();
            String[] lineArray = line.split(" ");
            int xNum = x * (-1);
            yNum = yNum + 1;
            for (int i = 0; i < lineArray.length; i++) {
                if (lineArray[i].length() == 4) {
                    tiles[xNum][yNum] = new Tile(lineArray[i].charAt(0),
                            lineArray[i].charAt(1), lineArray[i].charAt(2),
                            lineArray[i].charAt(3));
                }
                ;
                if (lineArray[i].length() == 3) {
                    String[] coords = lineArray[i - 1].split(",");
                    int xCoord = Integer.parseInt(coords[0]);
                    int yCoord = Integer.parseInt(coords[1]);
                    if (lineArray[i].equals("PLR")) {
                        Player player1 = new Player(xCoord, yCoord);
                        player = player1;
                        break;
                    }
                }
                ;
                xNum = xNum + 1;
            }
        }
        in.close();
        return null;
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

    private static Item loadItem(String item) {
        String[] itemData = item.split(",");
        String itemName = "";
        int[] itemCoord;
        return new Item(itemName, new int[] { 0, 0 }, 0);
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
