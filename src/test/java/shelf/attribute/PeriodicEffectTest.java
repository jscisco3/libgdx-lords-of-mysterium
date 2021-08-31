package shelf.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PeriodicEffectTest {

    private AttributeSet attributeSet;

    @BeforeEach
    public void setup() {
        this.attributeSet = AttributeSetFactory.getAttributeSet();
    }

    @Test
    @Disabled
    public void whenPeriodicEffectTicks_thenItModifiesTheBaseValue() {
        Effect periodicEffect = new PeriodicEffect()
                .withDuration(Duration.turns(100))
                .addModifier(new AttributeModifier()
                        .forAttribute(AttributeSet.AttributeDefinition.HEALTH)
                        .withMagnitude(-10f)
                        .withOperator(Attribute.Operator.ADD));

        periodicEffect.apply(attributeSet);
        periodicEffect.tick();
        assertThat(attributeSet.getHealth().getBaseValue()).isEqualTo(90f);
    }
}
