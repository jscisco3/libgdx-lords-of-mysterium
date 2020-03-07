package com.jscisco.lom.combat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Attack {
    private Set<Damage> damages = new HashSet<>();
    private int accuracy;

    public Attack(int accuracy, Damage... damages) {
        this.accuracy = accuracy;
        this.damages.addAll(Arrays.asList(damages));
    }

    public Set<Damage> getDamages() {
        return damages;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public Attack addDamage(Damage damage) {
        this.damages.add(damage);
        return this;
    }
}
