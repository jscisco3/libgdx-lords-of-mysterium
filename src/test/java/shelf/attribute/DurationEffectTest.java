package shelf.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DurationEffectTest {

    private AttributeSet attributeSet;

    @BeforeEach
    public void setup() {
        this.attributeSet = AttributeSetFactory.getAttributeSet();
    }

    @Test
    @Disabled
    public void whenDurationEffectApplied_thenCurrentValueOfAttributeIsUpdated() {
//        Effect durationEffect = new DurationEffect()
//                .withDuration(Duration.permanent())
//                .addModifier(new AttributeModifier()
//                        .forAttribute(AttributeSet.AttributeDefinition.MAX_HEALTH)
//                        .withMagnitude(100f)
//                        .withOperator(Attribute.Operator.ADD)
//                );
//        durationEffect.apply(this.attributeSet);
//
//        assertThat(this.attributeSet.getMaxHealth().getBaseValue()).isEqualTo(200f);
//        assertThat(this.attributeSet.getMaxHealth().getValue()).isEqualTo(300f);
    }
}
