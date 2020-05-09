package com.jscisco.lom.shelf.combat;

public class Defense {
    private ArmorClass ac;
    private Evasion ev;

    public Defense(ArmorClass ac, Evasion ev) {
        this.ac = ac;
        this.ev = ev;
    }

    public ArmorClass getAc() {
        return ac;
    }

    public Evasion getEv() {
        return ev;
    }
}
