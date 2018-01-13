package com.wec.controllers;

import com.wec.algorithm.JsonUtility;
import com.wec.algorithm.RunAlgorithm;
import lombok.val;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.wec.algorithm.RunAlgorithm.getShipSize;

@RestController
public class CSVController {

    @ResponseBody
    @RequestMapping(value = "/api/grid.csv", method = RequestMethod.GET)
    public void getCSVGrid(HttpServletResponse response) throws IOException {
        response.setContentType("text/plain; charset=utf-8");

        val file = JsonUtility.readJson(JsonUtility.FILE_NAME);
        val builder = new StringBuilder();
        builder.append("x,y,z\n");

        for (int x = 0; x < file.length; x++) {
            for (int y = 0; y < file[0].length; y++) {
                for (int z = 0; z < file[0][0].length; z++) {
                    if (file[x][y][z] != '0') {
                        builder.append(x + 1);
                        builder.append(",");
                        builder.append(y + 1);
                        builder.append(",");
                        builder.append(z + 1);
                        builder.append("\n");
                    }
                }
            }
        }

        response.getWriter().print(builder.toString());
    }

    @ResponseBody
    @RequestMapping(value = "/api/solution.csv", method = RequestMethod.GET)
    public void getCSVAnswer(HttpServletResponse response) throws IOException {
        response.setContentType("text/plain; charset=utf-8");

        val array = JsonUtility.readJson(JsonUtility.FILE_NAME);
        val map = getShipSize(array);
        val result = RunAlgorithm.determineBestShot(array, map);

        val builder = new StringBuilder();
        builder.append(result.getStartingCoordinate().getX());
        builder.append(",");
        builder.append(result.getStartingCoordinate().getY());
        builder.append(",");
        builder.append(result.getStartingCoordinate().getZ());
        builder.append("\n");

        builder.append(result.getVector().getX());
        builder.append(",");
        builder.append(result.getVector().getY());
        builder.append(",");
        builder.append(result.getVector().getZ());
        builder.append("\n");

        builder.append(result.getShipsDestroyed().size());
        builder.append("\n");
        builder.append(result.getDestructionScore());
        builder.append("\n");

        response.getWriter().print(builder.toString());
    }

}
