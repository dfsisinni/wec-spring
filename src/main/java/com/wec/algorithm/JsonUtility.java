package com.wec.algorithm;

import lombok.val;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonUtility {

    public static final String FILE_NAME = "scanner_data.json";

    public static char [][][] readJson(String fileName) {
        val parser = new JSONParser();

        try {
            val rawData = (JSONArray) parser.parse(new FileReader(fileName));

            val zSize = rawData.size();
            val firstInner = (JSONArray) rawData.get(0);
            val ySize = firstInner.size();
            val xSize = ((String) firstInner.get(0)).length();

            val array = new char [xSize][ySize][zSize];

            for (int z = 0; z < zSize; z++) {
                val zRow = (JSONArray) rawData.get(z);
                for (int y = 0; y < ySize; y++) {
                    val xVals = (String) zRow.get(y);
                    for (int x = 0; x < xVals.length(); x++) {
                        array[x][y][z] = xVals.charAt(x);
                    }
                }
            }

            return array;
        } catch (Exception e) {
            throw new IllegalStateException("Unable to read file.", e);
        }
    }

}
