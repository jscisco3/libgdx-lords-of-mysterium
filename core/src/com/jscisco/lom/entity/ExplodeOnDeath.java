package com.jscisco.lom.entity;

public class ExplodeOnDeath implements DeathStrategy {

    @Override
    public void die(Entity entity) {
        throw new RuntimeException("faklsdf");
    }
}
