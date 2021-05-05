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

    public FieldOfView(Entity entity) {
        this.entity = entity;
        this.fov = new FOV();
    }

    public void calculateResistanceMap() {
        logger.debug("Calculating resistance map for entity " + entity.getName().toString());
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

    /**
     * Calculates vision without recomputing the resistance map
     *
     * @return vision
     */
    public double[][] calculateFOV() {
        if (this.resistanceMap == null) {
            return calculateFOV(true);
        }
        return calculateFOV(false);
    }

    /**
     * Calculates the vision, and can recompute the resistance map
     *
     * @param recalculateResistanceMap true if the resistance map needs to be recalculated (e.g. if level structure has changed)
     * @return vision
     */
    public double[][] calculateFOV(boolean recalculateResistanceMap) {
        logger.debug("Calculating FOV for entity " + entity.getName());
        if (recalculateResistanceMap) {
            calculateResistanceMap();
        }
        //TODO: Save attribute set
//        this.vision = fov.calculateFOV(this.resistanceMap, entity.position.getX(), entity.position.getY(), entity.getAttributes().getLightRadius().getValue());
        this.vision = fov.calculateFOV(this.resistanceMap, entity.position.getX(), entity.position.getY(), 10.0f);
        return this.vision;
    }

    public boolean isInSight(Position position) {
        return this.vision[position.getX()][position.getY()] > 0;
    }

    public FOV getFov() {
        return fov;
    }
}
