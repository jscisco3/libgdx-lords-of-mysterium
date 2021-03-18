package com.jscisco.lom.domain;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class MathUtils {

    public static float clamp(float min, float max, float value) {
        return max(min, min(max, value));
    }
}
