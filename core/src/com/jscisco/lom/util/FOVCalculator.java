package com.jscisco.lom.util;

import com.jscisco.lom.actor.Actor;
import com.jscisco.lom.dungeon.Block;
import com.jscisco.lom.dungeon.Dungeon;
import squidpony.squidgrid.FOV;
import squidpony.squidgrid.Radius;

public class FOVCalculator {

    private static final FOV fov = new FOV();

    private static double[][] calculateResistanceMap(Actor actor, Dungeon dungeon) {
        Block[][][] blocks = dungeon.getBlocks();
        double[][] calculatedMap = new double[dungeon.getWidth()][dungeon.getHeight()];
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                if (blocks[x][y][actor.getPosition().getZ()].getTerrain().isTransparent()) {
                    calculatedMap[x][y] = 0.0;
                } else {
                    calculatedMap[x][y] = 1.0;
                }
            }
        }
        return calculatedMap;
    }

    public static void calculateFOV(Actor actor, Dungeon dungeon) {
        double[][] resistanceMap = calculateResistanceMap(actor, dungeon);
        actor.getFieldOfView().setFov(fov.calculateFOV(resistanceMap, actor.getPosition().getX(), actor.getPosition().getY(), actor.getFieldOfView().getRadius(), Radius.DIAMOND));
    }
}
