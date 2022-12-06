package com.example.cs230assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
        Player player = board.getPlayer();
        ArrayList<Entity> entities = board.getEntities();
        Tile[][] tiles = board.getTiles();
        int levelTime = board.getTimer().getLevelTime();
        int x = board.getTiles().length;
        int y = board.getTiles()[0].length;
        String playerData = savePlayer(player);
        String itemData = "";
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Item) {
                itemData = itemData + saveItem((Item) entities.get(i));
            }
        }
        String boardData = saveBoardData(x, y);
        String tileData = "";
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                tileData = tileData + saveTile(tiles[i][j], i, j);
            }
            tileData = tileData + "\n";
        }
        String entityData = "";
        for (int i = 0; i < entities.size(); i++) {
            if (!(entities.get(i) instanceof Item)) {
                entityData = entityData + saveEntity(entities.get(i));
            }
        }
        // write the data to a file
        String data = boardData + tileData + playerData + itemData + entityData
                + "\n" + levelTime;
        File file = new File(fileName + ".txt");
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String saveEntity(Entity entity) {
        int x = entity.getXCoord();
        int y = entity.getYCoord();
        String entityName = entity.getEntityName();
        String extraData = "";
        if (entityName.equals("player")) {
            throw new IllegalArgumentException(
                    "Player data should be saved separately, how did this get here?");
        }
        switch (entityName) {
        case "Floor Following Thief":
            entityName = "fft";
            break;
        case "Flying Assassin":
            entityName = "fla";
            int[] directions = ((FlyingAssassin) entity).getDirection();
            extraData = " " + directions[0] + " " + directions[1];
            break;
        case "Smart Thief":
            entityName = "smt";
            break;
        case "Gate":
            entityName = "gte";
            char colour = ((Gate) entity).getGateColour();
            extraData = " " + colour;
            break;
        case "Door":
            entityName = "dor";
            break;
        case "Key":
            entityName = "key";
            colour = ((Key) entity).getKeyColour();
            extraData = " " + colour;
            break;
        }
        String data = x + " " + y + " " + entityName + extraData + "\n";
        return data;
    }

    private static String saveTile(Tile tile, int x, int y) {
        String tileSquareString = tile.getSquares();
        String data = x + " " + y + " " + tileSquareString + " ";
        return data;
    }

    private static String savePlayerData(String playerID, int score,
            ArrayList<String> level) {
        String data = "player " + playerID + " " + score + " " + level;
        return data;
    }

    private static String savePlayer(Player player) {
        int x = player.getXCoord();
        int y = player.getYCoord();
        int score = player.getScore();
        ArrayList<String> levels = player.getLevels();
        String playerID = player.getEntityName();
        try {
            String data = savePlayerData("ply" + playerID, score, levels) + " "
                    + x + " " + y;
            writeToFile(playerID, data);
        } catch (NullPointerException e) {
            return "";
        }
        String data = x + " " + y + " " + "ply" + "\n";
        return data;
    }

    private static void writeToFile(String filename, String data) {
        File file = new File(filename + ".txt");
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String saveBoardData(int x, int y) {
        String data = x + " " + y + "\n";
        return data;
    }

    public static String loadPlayerData(String playerName) {
        File file = new File(playerName + ".txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String data = sc.nextLine();
        sc.close();
        return data;
    }

    private static String saveItem(Item item) {
        String itemName = item.getEntityName();
        int x = item.getXCoord();
        int y = item.getYCoord();
        int value = item.getItemValue();
        boolean isCollected = item.isCollected();
        if (isCollected) {
            return "";
        } else {
            String data = "item " + itemName + " " + x + " " + y + " " + value
                    + "\n";
            return data;
        }
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
