package com.wec.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ResultResponse {

    private List<ShipResponse> shipSize;

    private int numberOfShips;

    private int destructionScore;

    private int [] startingPoint;

    private int [] vector;

    private List<String> shipsDestroyed;

}
