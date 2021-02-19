package com.jscisco.lom.domain.attribute;

public class Duration {

    private final boolean permanent;
    private final int turns;

    private Duration(boolean permanent) {
        this.permanent = true;
        this.turns = 0;
    }

    private Duration(int turns) {
        this.permanent = false;
        this.turns = turns;
    }

    public static Duration permanent() {
        return new Duration(true);
    }

    public static Duration turns(int turns) {
        return new Duration(turns);
    }

    public boolean isPermanent() {
        return permanent;
    }
}
