package com.wec.algorithm;

import lombok.val;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class RunAlgorithm {

    private static String FILE_NAME = "scanner_data.json";

    public static Result runAlgorithm() {
        val array = JsonUtility.readJson(FILE_NAME);
        val map = getShipSize(array);

        return determineBestShot(array, map);
    }

    public static Result determineBestShot(char array [][][], Map<Character, Integer> shipSize) {
        Result bestResult = new Result();


        //z plane
        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array[0].length; y++) {

                val shipsDestroyed = new HashSet<Character>();
                int destructionScore = 0;
                for (int z = 0; z < array[0][0].length; z++) {
                    if (array[x][y][z] != '0' && !shipsDestroyed.contains(array[x][y][z])) {
                        shipsDestroyed.add(array[x][y][z]);
                        destructionScore += shipSize.get(array[x][y][z]);
                    }
                }

                if (destructionScore > bestResult.getDestructionScore()) {
                    bestResult.setDestructionScore(destructionScore);
                    bestResult.setShipsDestroyed(shipsDestroyed);

                    val startingCoordinate = new Coordinate();
                    startingCoordinate.setX(x + 1);
                    startingCoordinate.setY(y + 1);
                    startingCoordinate.setZ(0);

                    bestResult.setStartingCoordinate(startingCoordinate);

                    val vector = new Coordinate();
                    vector.setX(0);
                    vector.setY(0);
                    vector.setZ(1);

                    bestResult.setVector(vector);
                }
            }
        }


        //x plane
        for (int y = 0; y < array[0].length; y++) {
            for (int z = 0; z < array[0][0].length; z++) {

                val shipsDestroyed = new HashSet<Character>();
                int destructionScore = 0;
                for (int x = 0; x < array.length; x++) {
                    if (array[x][y][z] != '0' && !shipsDestroyed.contains(array[x][y][z])) {
                        shipsDestroyed.add(array[x][y][z]);
                        destructionScore += shipSize.get(array[x][y][z]);
                    }
                }

                if (destructionScore > bestResult.getDestructionScore()) {
                    bestResult.setDestructionScore(destructionScore);
                    bestResult.setShipsDestroyed(shipsDestroyed);

                    val startingCoordinate = new Coordinate();
                    startingCoordinate.setX(0);
                    startingCoordinate.setY(y + 1);
                    startingCoordinate.setZ(z + 1);

                    bestResult.setStartingCoordinate(startingCoordinate);

                    val vector = new Coordinate();
                    vector.setX(1);
                    vector.setY(0);
                    vector.setZ(0);

                    bestResult.setVector(vector);
                }

            }
        }


        //y plane
        for (int x = 0; x < array.length; x++) {
            for (int z = 0; z < array[0][0].length; z++) {

                val shipsDestroyed = new HashSet<Character>();
                int destructionScore = 0;
                for (int y = 0; y < array[0].length; y++) {
                    if (array[x][y][z] != '0' && !shipsDestroyed.contains(array[x][y][z])) {
                        shipsDestroyed.add(array[x][y][z]);
                        destructionScore += shipSize.get(array[x][y][z]);
                    }
                }

                if (destructionScore > bestResult.getDestructionScore()) {
                    bestResult.setDestructionScore(destructionScore);
                    bestResult.setShipsDestroyed(shipsDestroyed);

                    val startingCoordinate = new Coordinate();
                    startingCoordinate.setX(x + 1);
                    startingCoordinate.setY(0);
                    startingCoordinate.setZ(z + 1);

                    bestResult.setStartingCoordinate(startingCoordinate);

                    val vector = new Coordinate();
                    vector.setX(0);
                    vector.setY(1);
                    vector.setZ(0);

                    bestResult.setVector(vector);
                }

            }
        }

        return bestResult;
    }

    public static Map<Character, Integer> getShipSize(char array [][][]) {
        char currentLetter = 'A';
        val sizeMap = new HashMap<Character, Integer>();
        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array[x].length; y++) {
                for (int z = 0; z < array[x][y].length; z++) {
                    if (array[x][y][z] == '1') {
                        val size = doDFSForShipSize(currentLetter, array, x, y, z);
                        sizeMap.put(currentLetter, size);
                        currentLetter++;
                    }
                }
            }
        }

        return sizeMap;
    }

    public static int doDFSForShipSize(char currentLetter, char[][][] array, int x, int y, int z) {
        if (x < 0 || y < 0 || z < 0 || x >= array.length || y >= array[0].length || z >= array[0][0].length || array[x][y][z] != '1') {
            return 0;
        }

        array[x][y][z] = currentLetter;

        return doDFSForShipSize(currentLetter, array, x + 1, y, z) + doDFSForShipSize(currentLetter, array, x, y + 1, z) + doDFSForShipSize(currentLetter, array, x, y, z + 1)
                + doDFSForShipSize(currentLetter, array, x - 1, y, z) + doDFSForShipSize(currentLetter, array, x, y - 1, z) + doDFSForShipSize(currentLetter, array, x, y, z - 1) + 1;
    }


}
