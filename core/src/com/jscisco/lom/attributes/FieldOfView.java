package com.jscisco.lom.attributes;

import com.jscisco.lom.actor.Actor;
import com.jscisco.lom.dungeon.Block;
import com.jscisco.lom.dungeon.Dungeon;
import squidpony.squidgrid.FOV;
import squidpony.squidgrid.Radius;

public class FieldOfView {

    private double[][] fov;
    private double radius;
    // The resistance map is really just needed for the particular level a character is on
    private double[][] resistanceMap;

    public FieldOfView(double radius) {
        this.radius = radius;
    }

    public void initialize(Dungeon dungeon, Actor actor) {
        this.fov = new double[dungeon.getWidth()][dungeon.getHeight()];
        this.resistanceMap = calculateResistanceMap(dungeon, actor);
    }

    private double[][] calculateResistanceMap(Dungeon dungeon, Actor actor) {
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

    public void calculateFov(Actor actor, FOV fovCalculator) {
        this.fov = fovCalculator.calculateFOV(resistanceMap, actor.getPosition().getX(), actor.getPosition().getY(), radius, Radius.DIAMOND);
    }

    public double[][] getFov() {
        return fov;
    }

    public double getRadius() {
        return radius;
    }

    public double[][] getResistanceMap() {
        return resistanceMap;
    }
}
