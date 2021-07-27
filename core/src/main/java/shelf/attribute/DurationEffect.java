package shelf.attribute;

/**
 * This is an effect that modifies an attribute's calculated value. Used for temporary and permanent effects.
 * For example, an effect that adds 10 strength for 4 turns. Or an effect that grants +10% critical chance until
 * canceled.
 */
public class DurationEffect extends Effect {

    public DurationEffect() {
    }

    @Override
    public void apply(AttributeSet attributeSet) {
        for (AttributeModifier modifier : this.getModifiers()) {
            attributeSet.applyTemporaryModifier(modifier);
        }
    }
}
