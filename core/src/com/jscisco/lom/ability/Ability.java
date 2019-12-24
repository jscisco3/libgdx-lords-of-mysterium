package com.jscisco.lom.ability;

import com.jscisco.lom.effect.Effect;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Stage;
import squidpony.squidai.AOE;
import squidpony.squidmath.Coord;
import squidpony.squidmath.OrderedMap;

import java.util.ArrayList;
import java.util.List;

public class Ability {
    // Optional Cost
    // Number of turns before we can use this ability again
    private int cooldown = 0;
    private int turnsUntilCanUseAgain = 0;
    // Effect(s) that are wrought by this ability
    private List<Effect> effects = new ArrayList<>();
    private Stage stage;
    private AOE aoe;

    public Ability(int cooldown, List<Effect> effects, Stage stage, AOE aoe) {
        this.cooldown = cooldown;
        this.effects = effects;
        this.stage = stage;
        this.aoe = aoe;

        this.aoe.setMap(this.stage.toSquidlibMap());
    }

//    public Ability(Ability other) {
//        this.cooldown = other.cooldown;
//        this.effects = other.effects;
//        this.stage = other.stage;
//        this.aoe = other.aoe;
//        this.aoe.setMap(this.stage.toSquidlibMap());
//    }

    public void tick() {
        if (turnsUntilCanUseAgain > 0) {
            turnsUntilCanUseAgain -= 1;
        }
    }

    public boolean canUse() {
        return turnsUntilCanUseAgain > 0;
    }

    public void applyEffects() {
        // Get affected entities
        this.area().keySet().forEach(coord -> {
            Entity e = this.stage.getEntityAtPosition(Position.get(coord.x, coord.y));
            if (e != null) {
                this.effects.forEach(e::applyEffect);
            }
        });
    }

    public OrderedMap<Coord, Double> area() {
        return this.aoe.findArea();
    }

}
