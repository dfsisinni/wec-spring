package com.wec.algorithm;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Result {

    private Coordinate startingCoordinate;
    private Coordinate vector;

    private Set<Character> shipsDestroyed;
    private int destructionScore;

    public Result() {
        shipsDestroyed = new HashSet<>();
        destructionScore = 0;
        startingCoordinate = null;
        vector = null;
    }
}
