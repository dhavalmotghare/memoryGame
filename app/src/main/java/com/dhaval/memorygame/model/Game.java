package com.dhaval.memorygame.model;

import android.util.Log;
import android.util.Pair;

import java.util.Random;

public class Game {
    private int level;
    private int maxTime;
    private int actualTime;
    private int row, column;
    private int[] boardValues;

    public Game(int level, int row, int column, int maxTime) {
        this.row = row;
        this.column = column;
        this.level = level;
        this.maxTime = maxTime;
        initializeBoard();
    }

    private void initializeBoard() {
        boardValues = new int[row * column];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                boardValues[r + c] = -1;
            }
        }

        while (!boardInitialized()) {
            int randomValue = getRandomValue(50 * level);
            Pair<Integer, Integer> firstPair = getRandomUnfilledPosition();
            Pair<Integer, Integer> secondPair = getRandomUnfilledPosition();
            if (firstPair != null && secondPair != null) {
                boardValues[firstPair.first + firstPair.second] = randomValue;
                boardValues[secondPair.first + secondPair.second] = randomValue;
            }
        }

        Log.i("Game ", " printing board values");
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                Log.i("Game ", " value [" + r + "] [" + c + "] = " + boardValues[r + c]);
            }
        }
    }

    private Pair<Integer, Integer> getRandomUnfilledPosition() {
        if (boardInitialized()) {
            return null;
        }
        int randomRow = getRandomValue(row);
        int randomColumn = getRandomValue(column);
        while (boardValues[randomRow + randomColumn] != -1) {
            randomRow = getRandomValue(row);
            randomColumn = getRandomValue(column);
        }
        return new Pair(randomRow, randomColumn);
    }

    private int getRandomValue(int seed) {
        Random random = new Random(seed);
        return random.nextInt();
    }

    private boolean boardInitialized() {
        int unfilledColumns = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                if (boardValues[r + c] == -1) {
                    unfilledColumns++;
                }
            }
        }
        return (unfilledColumns <= 1);
    }


    public static Game getGame(int level) {
        Game game = null;
        switch (level) {
            case 1:
                game = new Game(level, 3, 2, 60);
                break;
            case 2:
                game = new Game(level, 3, 3, 40);
                break;
            default:
                game = new Game(level, 3, 2, 60);
        }
        return game;
    }

    public int getLevel() {
        return level;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public int getActualTime() {
        return actualTime;
    }

    public int[] getBoardValues() {
        return boardValues;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
