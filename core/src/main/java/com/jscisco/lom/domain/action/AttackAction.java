package com.jscisco.lom.domain.action;

import com.jscisco.lom.application.configuration.GameConfiguration;
import shelf.attribute.*;
import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.event.LogEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * The basic bump-attack action.
 * TODO: Refactor into an ability when implementing those.
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
        float d = 10f;
//        Attribute health = target.getAttributes().getHealth();
//        Effect damage = new InstantEffect()
//                .addModifier(new AttributeModifier()
//                        .forAttribute(AttributeSet.AttributeDefinition.HEALTH)
//                        .withOperator(Attribute.Operator.ADD)
//                        .withMagnitude(-d));
//        target.applyEffect(damage);
//        GameConfiguration.eventBus.post(new LogEvent(MessageFormat.format("{0} was dealt {1} damage by {2} and has {3} health remaining.", target.getName().getName(), d, source.getName().getName(), health.getValue())));
//        logger.debug(MessageFormat.format("{0} was dealt {1} damage by {2} and has {3} health remaining.", target.getName().getName(), d, source.getName().getName(), health.getValue()));
//        if (health.getValue() <= 0f) {
//            target.applyEffect(new DurationEffect()
//                    .withDuration(Duration.permanent())
//                    .grantTag(Tag.DEAD));
//            target.onDied();
//        }
        return ActionResult.succeeded();
    }
}
