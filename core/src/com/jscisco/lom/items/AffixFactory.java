package com.jscisco.lom.items;

public class AffixFactory {

    public Affix getFlameAttackAffix(int ilvl) {
        if (ilvl < 10) {
            return new Affix("Burning");
        }
        if (ilvl < 20) {
            return new Affix("Flaming");
        } else {
            return new Affix("Inferno");
        }
    }

}
