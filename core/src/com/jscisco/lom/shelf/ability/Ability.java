package com.jscisco.lom.shelf.ability;

import com.jscisco.lom.shelf.effect.Effect;
import com.jscisco.lom.shelf.entity.Entity;
import com.jscisco.lom.shelf.domain.Position;
import com.jscisco.lom.shelf.zone.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidai.AOE;
import squidpony.squidai.BlastAOE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Ability {
    // Optional Cost
    // Number of turns before we can use this ability again
    private int cooldown = 0;
    private int turnsUntilCanUseAgain = 0;
    // Effect(s) that are wrought by this ability
    private List<Effect> effects = new ArrayList<>();
    private AOE aoe;
    private AbilityName name;
    private AbilityDescription description;
    private boolean requireTarget;
    private Optional<Position> target;

    private static final Logger logger = LoggerFactory.getLogger(Ability.class);

    private Ability() {
    }

    public static class Builder {
        private int cooldown = 0;
        private List<Effect> effects = new ArrayList<>();
        private AOE aoe;
        private AbilityName name;
        private AbilityDescription description;
        private boolean requireTarget;

        public Builder() {
        }

        public Builder withCooldown(int cooldown) {
            this.cooldown = cooldown;
            return this;
        }

        public Builder withEffect(Effect effect) {
            this.effects.add(effect);
            return this;
        }

        public Builder withAOE(AOE aoe) {
            this.aoe = aoe;
            return this;
        }

        public Builder withName(AbilityName name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(AbilityDescription description) {
            this.description = description;
            return this;
        }

        public Builder withTargetRequired() {
            this.requireTarget = true;
            return this;
        }

        public Ability build() {
            assert this.aoe != null;
            Ability ability = new Ability();
            ability.name = this.name;
            ability.description = this.description;
            ability.effects = this.effects;
            ability.aoe = this.aoe;
            ability.cooldown = this.cooldown;
            ability.requireTarget = this.requireTarget;
            return ability;
        }


    }

    public void tick() {
        if (turnsUntilCanUseAgain > 0) {
            turnsUntilCanUseAgain -= 1;
        }
    }

    public boolean canUse() {
        return turnsUntilCanUseAgain > 0;
    }

    public void applyEffects(Stage stage) {
        // Get affected entities
        this.aoe.setMap(stage.toSquidlibMap());
        logger.info("Apply effects!");
        if (this.aoe instanceof BlastAOE) {
            logger.info("Center: {}", ((BlastAOE) this.aoe).getCenter());
        }
        this.aoe.findArea().keySet().forEach(coord -> {
            Entity e = stage.getEntityAtPosition(Position.get(coord.x, coord.y));
            logger.info("Affecting: {}", coord);
            if (e != null) {
                logger.info("Ability: {} has effected {}", this.name.getName(), e.getName().name());
                this.effects.forEach(e::applyEffect);
            }
        });
    }

    public AbilityName getName() {
        return name;
    }

    public AbilityDescription getDescription() {
        return description;
    }

    public void setTarget(Position position) {
        this.target = Optional.of(position);
        if (this.aoe instanceof BlastAOE) {
            logger.info("Setting center to {}", position);
            this.aoe = new BlastAOE(position.asCoord(), ((BlastAOE) this.aoe).getRadius(), ((BlastAOE) this.aoe).getRadiusType());
        }
    }

    public boolean isTargetRequired() {
        return requireTarget;
    }

    public Optional<Position> getTarget() {
        return target;
    }

    public AOE getAoe() {
        return aoe;
    }
}
