package shelf.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InstantEffectTest {

    private Effect effect;
    private AttributeSet attributeSet;

    @BeforeEach
    public void setup() {
        this.attributeSet = AttributeSetFactory.getAttributeSet();
    }

    @Test
    @Disabled
    public void whenInstantEffectIsApplied_thenTheAttributesBaseValueIsUpdated() {

        effect = new InstantEffect()
                .addModifier(new AttributeModifier()
                        .forAttribute(AttributeSet.AttributeDefinition.MAX_HEALTH)
                        .withMagnitude(10f)
                        .withOperator(Attribute.Operator.ADD));

        float initialValue = attributeSet.getAttributeValue(AttributeSet.AttributeDefinition.MAX_HEALTH);
        float expectedValue = initialValue + 10f;
        effect.apply(attributeSet);

        assertThat(attributeSet.getAttributeValue(AttributeSet.AttributeDefinition.MAX_HEALTH)).isEqualTo(expectedValue);
    }
}
