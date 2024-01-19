package com.jscisco.lom.domain.action;

import com.badlogic.gdx.tools.hiero.unicodefont.effects.Effect;
import com.jscisco.lom.domain.Pool;
import com.jscisco.lom.domain.Pools;
import com.jscisco.lom.domain.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The basic bump-attack action. TODO: Refactor into an ability when implementing those.
 */
public class AttackAction extends Action {

    private final static Logger logger = LoggerFactory.getLogger(AttackAction.class);
    private final Entity target;

    public AttackAction(Entity source, Entity target) {
        super(source);
        this.target = target;
    }

    @Override
    public ActionResult execute() {
        logger.info("Executing Attack action. {} is attacking {}", source.getName(), target.getName());
        int d = 10;
        logger.info("{} attacked {} and dealt {} damage", source.getName(), target.getName(), d);
        Pools sourcePools = source.getPools();
        Pools targetPools = target.getPools();
        Pool targetHp = targetPools.getHp();
        logger.info("{} had {} hp", target.getName(), targetHp.getCurrent());
        targetHp.subtract(d);
        logger.info("{} now has {} hp", target.getName(), targetHp.getCurrent());
        if (targetHp.getCurrent() <= 0) {
            // TODO: Level up
            sourcePools.addExperience(targetPools.getLevel() * 100);
            // TODO: Make this more abstract some how?
            source.getLevel().removeEntity(target);
        }
        // TODO: Game over
        return ActionResult.succeeded();
    }
}
