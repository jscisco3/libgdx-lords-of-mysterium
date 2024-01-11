package com.jscisco.lom.random;

import org.springframework.stereotype.Component;
import squidpony.squidmath.RNG;

@Component
public class RNGProvider {
    RNG rng;

    public RNGProvider() {
        // TODO: Seed?
        this.rng = new RNG();
    }

    public RNG getRng() {
        return rng;
    }
}
