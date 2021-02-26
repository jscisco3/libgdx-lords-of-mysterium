package com.jscisco.lom.domain.attribute;

public class Duration {
    private boolean permanent;
    private int turns;

    private Duration() {

    }

    private Duration(boolean permanent) {
        this.permanent = permanent;
        this.turns = 0;
    }

    private Duration(int turns) {
        this.permanent = false;
        this.turns = turns;
    }

    public static Duration turns(int turns) {
        return new Duration(turns);
    }

    public static Duration permanent() {
        return new Duration(true);
    }
}
