package com.wec.controllers;

import com.wec.algorithm.JsonUtility;
import com.wec.algorithm.RunAlgorithm;
import com.wec.models.ResultResponse;
import com.wec.models.ShipResponse;
import lombok.val;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.wec.algorithm.RunAlgorithm.getShipSize;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/results")
public class ResultController {



    @RequestMapping(method = RequestMethod.GET)
    public ResultResponse getResults() {
        val array = JsonUtility.readJson(JsonUtility.FILE_NAME);
        val map = getShipSize(array);
        val result = RunAlgorithm.determineBestShot(array, map);

        val response = new ResultResponse();

        final List<ShipResponse> shipList = map.entrySet().stream().map(x -> {
            val ship = new ShipResponse();
            ship.setName(String.valueOf(x.getKey()));
            ship.setSize(x.getValue());
            return ship;
        }).collect(toList());
        shipList.sort((o1, o2) -> o2.getSize() - o1.getSize());
        response.setShipSize(shipList);

        response.setNumberOfShips(response.getShipSize().size());
        response.setDestructionScore(result.getDestructionScore());

        val startingPoint = new int [3];
        startingPoint[0] = result.getStartingCoordinate().getX();
        startingPoint[1] = result.getStartingCoordinate().getY();
        startingPoint[2] = result.getStartingCoordinate().getZ();
        response.setStartingPoint(startingPoint);

        val vector = new int [3];
        vector[0] = result.getVector().getX();
        vector[1] = result.getVector().getY();
        vector[2] = result.getVector().getZ();
        response.setVector(vector);

        response.setShipsDestroyed(result.getShipsDestroyed().stream().map(String::valueOf).collect(toList()));

        return response;
    }

}
