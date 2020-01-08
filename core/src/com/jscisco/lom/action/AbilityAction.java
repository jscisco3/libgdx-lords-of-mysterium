package com.jscisco.lom.action;

import com.jscisco.lom.ability.Ability;
import com.jscisco.lom.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidmath.Coord;

public class AbilityAction extends AbstractAction {

    private Ability ability;
    private static final Logger logger = LoggerFactory.getLogger(AbilityAction.class);

    public AbilityAction(Entity source, Ability ability) {
        super(source);
        this.ability = ability;
    }

    @Override
    public ActionResult invoke() {
        if (this.ability.isTargetRequired() && !this.ability.getTarget().isPresent()) {
            logger.info("Tried to activate an ability which requires a target, but it has no target!");
            return ActionResult.failure();
        }
        logger.info("Applying action centered at position {}", this.ability.getTarget().get());
        this.ability.getAoe().setOrigin(source.getPosition().asCoord());
        this.ability.applyEffects(source.getStage());
        return ActionResult.success();
    }

    public Ability getAbility() {
        return ability;
    }
}
