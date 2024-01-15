package com.jscisco.lom.domain;

public class Pool {
    private int max;
    private int current;

    public Pool(int max) {
        this.max = max;
        this.current = this.max;
    }

    public int getMax() {
        return max;
    }

    public int getCurrent() {
        return current;
    }

    public void subtract(int amount) {
        this.current -= amount;
    }

    public void add(int amount) {
        this.current = Math.clamp(this.current + amount, 0, this.max);
    }
}
