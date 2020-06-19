package com.jscisco.lom.domain.combat;

public class Evasion {
    int evasion;

    private Evasion(int evasion) {
        this.evasion = evasion;
    }

    public static Evasion of(int evasion) {
        return new Evasion(evasion);
    }

}
