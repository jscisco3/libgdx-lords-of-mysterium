package com.jscisco.lom.domain.action;

import com.jscisco.lom.domain.attribute.Attribute;
import com.jscisco.lom.domain.attribute.AttributeModifier;
import com.jscisco.lom.domain.attribute.Effect;
import com.jscisco.lom.domain.attribute.InstantEffect;
import com.jscisco.lom.domain.entity.Entity;
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
        Effect damage = new InstantEffect()
                .addModifier(new AttributeModifier()
                        .forAttribute(target.getAttributes().getHealth())
                        .withOperator(Attribute.Operator.ADD)
                        .withMagnitude(-d));
        target.applyEffect(damage);

        logger.info(MessageFormat.format("{0} was dealt {1} damage by {2} and has {3} health remaining.", target.getName().getName(), d, source.getName().getName(), target.getAttributes().getHealth().getValue()));
        return ActionResult.succeeded();
    }
}
