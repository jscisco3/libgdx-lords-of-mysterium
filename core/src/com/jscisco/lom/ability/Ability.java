package com.jscisco.lom.ability;

import com.jscisco.lom.effect.Effect;

import java.util.ArrayList;
import java.util.List;

public abstract class Ability {
    // Optional Cost
    // Number of turns before we can use this ability again
    private int cooldown = 0;
    private int turnsUntilCanUseAgain = 0;
    // Effect(s) that are wrought by this ability
    private List<Effect> effects = new ArrayList<>();

    public void tick() {
        if (turnsUntilCanUseAgain > 0) {
            turnsUntilCanUseAgain -= 1;
        }
    }

    public boolean canUse() {
        return turnsUntilCanUseAgain > 0;
    }
}
