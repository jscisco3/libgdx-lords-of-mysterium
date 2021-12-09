package com.jscisco.lom.domain;

import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class MathUtils {

    public static float clamp(float min, float max, float value) {
        return max(min, min(max, value));
    }

    public static int randomIntegerInRange(Random random, int min, int max) {
        return random.nextInt(max - min) + min;
    }

}
