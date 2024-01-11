package com.jscisco.lom.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squidpony.squidmath.RNG;

public class BSPBuilderTest {

    private RNG rng;
    private BuildData buildData;
    private BSPBuilder builder;

    @BeforeEach
    public void setUp() {
        rng = new RNG(0xDEADBEEFL);
        builder = new BSPBuilder();
    }

}
