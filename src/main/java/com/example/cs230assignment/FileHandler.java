package com.example.cs230assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class defines the FileHandler methods. These are used to read and write
 * files, saving and loading the game and relevant data.
 * 
 * @author Rowan Dash
 * @version 1.0
 */

public class FileHandler {

    /**
     * This method loads the level from a file.
     * 
     * @param fileName The level file to process
     * @return The instance of board for that level
     */
    private static Board loadBoard(File fileName, String playerName) {
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
                if (lineArray[i].equals("timer")) {
                    levelTime = Integer.parseInt(lineArray[i + 1]);
                }
                if (lineArray[i].equals("items")) {
                    int xCoord = Integer.parseInt(lineArray[i - 2]);
                    int yCoord = Integer.parseInt(lineArray[i - 1]);
                    String name = lineArray[i + 1];
                    int value = Integer.parseInt(lineArray[i + 2]);
                    entities.add(loadItem(name, xCoord, yCoord, value));
                }
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
                        Player player1 = new Player(playerName, xCoord, yCoord);
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
                    if (lineArray[i].equals("itm")) {
                        String name = lineArray[i + 1];
                        int value = Integer.parseInt(lineArray[i + 2]);
                        entities.add(loadItem(name, x, y, value));
                    }
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
        // flip the tiles array 90 degrees
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
        Tile[][] tiles6 = linkTilesEntity(tiles5, entities, player);
        Board board = new Board(x, y, tiles6, entities, player, timer);
        addBoardLinks(board);
        return board;
    }

    /**
     * Links the tiles and entities together.
     * 
     * @param tiles
     * @param entities
     * @return Tile[][]
     */
    private static Tile[][] linkTilesEntity(Tile[][] tiles,
            ArrayList<Entity> entities, Player player) {
        for (int i = 0; i < entities.size(); i++) {
            int x = entities.get(i).getXCoord();
            int y = entities.get(i).getYCoord();
            tiles[x][y].setEntity(entities.get(i));
        }
        int x = player.getXCoord();
        int y = player.getYCoord();
        tiles[x][y].setEntity(player);
        return tiles;
    }

    private static void addBoardLinks(Board board) {
        ArrayList<Entity> entities = board.getEntities();
        Player player = board.getPlayer();
        for (Entity e : entities) {
            if (e instanceof FloorFollowingThief || e instanceof SmartThief
                    || e instanceof FlyingAssassin) {
                ((Character) e).setBoard(board);
            }
        }
        player.setBoard(board);
    }

    /**
     * Attempts to load a player's data from a file.
     * 
     * @param playerName The name of the player
     * @return String
     */
    public static String loadPlayerData(String playerName) {
        File file = new File(
                "src/main/resources/profiles/" + playerName + ".txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            return null;
        }
        String data = sc.nextLine();
        sc.close();
        return data;
    }

    /**
     * Instansiate a new item.
     * 
     * @param itemName The name of the item
     * @param x        The x coordinate of the item
     * @param y        The y coordinate of the item
     * @param value    The value of the item
     * @return Item
     */
    private static Item loadItem(String itemName, int x, int y, int value) {
        return new Item(itemName, x, y, value);
    }

    /**
     * Saves the board to a file.
     * 
     * @param board    the board to save
     * @param fileName the name of the file to save to
     */
    private static void saveBoard(Board board, String fileName) {
        Player player = board.getPlayer();
        fileName = player.getEntityName() + fileName + "save";
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
                tileData = tileData + saveTile(tiles[i][j]);
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
                + "timer " + levelTime;
        File file = new File("src/main/resources/saves/" + fileName + ".txt");
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts an entity to a string representation.
     * 
     * @param entity The entity to be saved
     * @return String The data to be saved
     */
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

    /**
     * Converts a tile to a string representation.
     * 
     * @param tile The tile to be saved
     * @param x    The x coordinate of the tile
     * @param y    The y coordinate of the tile
     * @return String
     */
    private static String saveTile(Tile tile) {
        String tileSquareString = tile.getSquares();
        String data = tileSquareString + " ";
        return data;
    }

    /**
     * Converts a player to a string representation to save their progress.
     * 
     * @param playerID The name of the player
     * @param score    The player's score
     * @param level    The player's completed levels
     * @return String
     */
    private static String savePlayerData(String playerID, int score,
            ArrayList<String> level) {
        String data = score + " " + level;
        return data;
    }

    /**
     * Converts a player to a string representation in order to save it.
     * 
     * @param player The player to be saved
     * @return String
     */
    private static String savePlayer(Player player) {
        int x = player.getXCoord();
        int y = player.getYCoord();
        int score = player.getScore();
        ArrayList<String> levels = player.getLevels();
        String playerID = player.getEntityName();
        try {
            String data = savePlayerData(playerID, score, levels);
            writeToFile("src/main/resources/profiles/" + playerID, data);
        } catch (NullPointerException e) {
        }
        String data = x + " " + y + " " + "ply" + "\n";
        return data;
    }

    /**
     * Returns the x and y size of the board as a string for saving purposes.
     * 
     * @param x The x size of the board
     * @param y The y size of the board
     * @return String
     */
    private static String saveBoardData(int x, int y) {
        String data = x + " " + y + "\n";
        return data;
    }

    /**
     * Converts an item to a string representation.
     * 
     * @param item The item to be converted
     * @return String
     */
    private static String saveItem(Item item) {
        String itemName = item.getEntityName();
        int x = item.getXCoord();
        int y = item.getYCoord();
        int value = item.getItemValue();
        boolean isCollected = item.isCollected();
        if (isCollected) {
            return "";
        } else {
            String data = x + " " + y + " items " + itemName + " " + value
                    + "\n";
            return data;
        }
    }

    /**
     * Saves a board to a file.
     * 
     * @param board
     * @param fileName
     */
    public static void saveGame(Board board, String fileName) {
        File saveFile = new File(fileName);
        saveBoard(board, fileName);
    }

    /**
     * Writes a string to a file.
     * 
     * @param filename The name of the file
     * @param data     The data to be written
     */
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

    /**
     * Instansiates a new instance of board.
     * 
     * @param fileName
     * @return Board
     */
    public static Board readLevelFile(String fileName, String playerName) {
        File file = new File("src/main/resources/levels/" + fileName + ".txt");
        return loadBoard(file, playerName);
    }

    /**
     * Reads the level names from a file.
     * 
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
