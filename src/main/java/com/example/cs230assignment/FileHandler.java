package com.example.cs230assignment;

import java.io.File;
import java.io.FileNotFoundException;
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
