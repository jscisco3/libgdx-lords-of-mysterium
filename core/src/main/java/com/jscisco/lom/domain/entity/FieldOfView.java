package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.zone.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidgrid.FOV;

public class FieldOfView {

    private static final Logger logger = LoggerFactory.getLogger(FieldOfView.class);

    private final Entity entity;
    private double[][] resistanceMap;
    private double[][] vision;
    private final FOV fov;
    private Level level;
    private boolean recalculate = true;

    public FieldOfView(Entity entity) {
        this.entity = entity;
        this.fov = new FOV();
    }

    public void calculateResistanceMap() {
        logger.info("Calculating resistance map...");
        if (this.level == null) {
            this.level = this.entity.getLevel();
            this.resistanceMap = new double[level.getWidth()][level.getHeight()];
        }
        for (int x = 0; x < level.getWidth(); x++) {
            for (int y = 0; y < level.getHeight(); y++) {
                resistanceMap[x][y] = level.getTileAt(Position.of(x, y)).blocksSight(entity) ? 1.0 : 0.0;
            }
        }
    }

    public double[][] calculateFOV() {
        logger.debug("Calculating FOV");
        if (recalculate) {
            calculateResistanceMap();
            recalculate = false;
        }
        this.vision = fov.calculateFOV(resistanceMap, entity.position.getX(), entity.position.getY(), 20.0);
        return this.vision;
    }

    public boolean isInSight(Position position) {
        return this.vision[position.getX()][position.getY()] > 0;
    }

    public FOV getFov() {
        return fov;
    }
}
