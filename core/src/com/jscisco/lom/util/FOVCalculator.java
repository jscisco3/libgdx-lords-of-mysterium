package com.jscisco.lom.util;

import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.zone.Stage;
import com.jscisco.lom.zone.Tile;
import squidpony.squidgrid.FOV;
import squidpony.squidgrid.Radius;

public class FOVCalculator {

    private static final FOV fov = new FOV();

    private static double[][] calculateResistanceMap(Entity entity, Stage stage) {
        Tile[][] tiles = stage.getTiles();
        double[][] calculatedMap = new double[stage.getWidth()][stage.getHeight()];
        for (int x = 0; x < stage.getWidth(); x++) {
            for (int y = 0; y < stage.getHeight(); y++) {
                if (tiles[x][y].getTerrain().isTransparent()) {
                    calculatedMap[x][y] = 0.0;
                } else {
                    calculatedMap[x][y] = 1.0;
                }
            }
        }
        return calculatedMap;
    }

    public static void calculateFOV(Entity entity, Stage stage) {
        double[][] resistanceMap = calculateResistanceMap(entity, stage);
        entity.getFieldOfView().setFov(fov.calculateFOV(resistanceMap, entity.getPosition().getX(), entity.getPosition().getY(), entity.getFieldOfView().getRadius(), Radius.CUBE));
    }
}
