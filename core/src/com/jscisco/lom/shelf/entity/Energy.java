package com.jscisco.lom.shelf.entity;

public class Energy {

    private int energy;
    private int rechargeRate;

    public Energy() {
        this.energy = 0;
        this.rechargeRate = 1000;
    }

    public int getEnergy() {
        return energy;
    }

    public void spendEnergy(int cost) {
        this.energy -= cost;
    }
}
