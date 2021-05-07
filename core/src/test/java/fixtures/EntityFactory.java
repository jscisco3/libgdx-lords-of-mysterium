package fixtures;

import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.attribute.Attribute;
import com.jscisco.lom.domain.attribute.AttributeModifier;
import com.jscisco.lom.domain.attribute.AttributeSet;
import com.jscisco.lom.domain.attribute.InstantEffect;
import com.jscisco.lom.domain.entity.Hero;

public class EntityFactory {

    public static Hero testHero() {
        Hero h = new Hero.Builder()
                .withPosition(Position.of(1, 1))
                .withName(Name.of("Test Hero"))
                .withAttributes(new AttributeSet())
                .build();

        h.applyEffect(
                new InstantEffect()
                        .addModifier(new AttributeModifier()
                                .forAttribute(AttributeSet.AttributeDefinition.MAX_HEALTH)
                                .withMagnitude(100f)
                                .withOperator(Attribute.Operator.OVERRIDE)
                        )
                        .addModifier(new AttributeModifier()
                                .forAttribute(AttributeSet.AttributeDefinition.HEALTH)
                                .withMagnitude(100f)
                                .withOperator(Attribute.Operator.OVERRIDE)
                        )
                        .addModifier(new AttributeModifier()
                                .forAttribute(AttributeSet.AttributeDefinition.LIGHT_RADIUS)
                                .withMagnitude(10f)
                                .withOperator(Attribute.Operator.OVERRIDE))
        );
        return h;
    }

}
