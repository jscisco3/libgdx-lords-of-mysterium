package com.jscisco.lom.math;

import com.jscisco.lom.domain.Position;

/**
 * This provides methods for calculating distances between two points in a variety
 */
public class DistanceAlgorithm {
    /**
     * Calculate the distance from start to end via the euclidean distance metric, without calculating the square root
     * of the result Useful for comparing general distances without worrying about the correct result.
     *
     * @param start
     *            The starting position
     * @param end
     *            The ending position
     *
     * @return The distance
     */
    public static float euclideanSquared(Position start, Position end) {
        return (float) (Math.pow(start.getX() - end.getX(), 2) + Math.pow(start.getY() - end.getY(), 2));
    }

    /**
     * Calculate the distance from start to end using the Manhattan distance metric.
     *
     * @param start
     *            The starting position
     * @param end
     *            The ending position
     *
     * @return The distance
     */
    public static float manhattan(Position start, Position end) {
        return Math.abs(start.getX() - end.getX()) + Math.abs(start.getY() - start.getX());
    }

}
