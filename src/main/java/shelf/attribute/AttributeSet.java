package shelf.attribute;

import com.jscisco.lom.domain.Description;
import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.entity.Entity;

import java.util.*;

import static com.jscisco.lom.domain.MathUtils.clamp;

/**
 * Responsible for maintaining relevant attributes
 */
public class AttributeSet {

    public enum AttributeDefinition {
        HEALTH,
        MAX_HEALTH,
        LIGHT_RADIUS
    }

    private UUID id;

    private Entity entity;

    private Map<AttributeDefinition, Attribute> attributes = new HashMap<>();

    private List<Effect> effects = new ArrayList<>();

    public AttributeSet() {
    }

    public void initialize() {
        attributes.put(AttributeDefinition.HEALTH, new Attribute(Name.of("Health"), Description.of("Current Health")));
        attributes.put(AttributeDefinition.MAX_HEALTH, new Attribute(Name.of("Max Health"), Description.of("Maximum Health")));
        attributes.put(AttributeDefinition.LIGHT_RADIUS, new Attribute(Name.of("Light Radius"), Description.of("Light Radius")));
    }

    public void addAttribute(AttributeDefinition definition, Attribute attribute) {
        this.attributes.put(definition, attribute);
    }

    public Attribute getHealth() {
        return attributes.get(AttributeDefinition.HEALTH);
    }

    public Attribute getMaxHealth() {
        return attributes.get(AttributeDefinition.MAX_HEALTH);
    }

    public Attribute getLightRadius() {
        return attributes.get(AttributeDefinition.LIGHT_RADIUS);
    }

    public void addEffect(Effect effect) {
        this.effects.add(effect);
        effect.setAttributeSet(this);
    }

    public void applyBaseValueModifier(AttributeModifier modifier) {
        float newValue = 0f;

        switch (modifier.operator) {
            case MULTIPLY:
                newValue = getBaseValue(modifier.getAttributeDefinition()) * modifier.getMagnitude();
                break;
            case ADD:
                newValue = getBaseValue(modifier.getAttributeDefinition()) + modifier.getMagnitude();
                break;
            case OVERRIDE:
                newValue = modifier.getMagnitude();
                break;
        }
        // TODO: FIX
        // Health can never permanently be greater than the max health.
        if (modifier.getAttributeDefinition().equals(AttributeDefinition.HEALTH)) {
            newValue = (clamp(0f, getAttributeValue(AttributeDefinition.MAX_HEALTH), newValue));
        }
        getAttribute(modifier.getAttributeDefinition()).setBaseValue(newValue);
    }

    public float getAttributeValue(AttributeDefinition attributeDefinition) {
        Attribute attribute = this.attributes.get(attributeDefinition);
        float attributeValue = getValue(attributeDefinition, attribute.getBaseValue());
        if (attributeDefinition.equals(AttributeDefinition.HEALTH)) {
            attributeValue = clamp(getValue(attributeDefinition, attribute.getBaseValue()), getValue(AttributeDefinition.MAX_HEALTH, getAttribute(AttributeDefinition.MAX_HEALTH).getBaseValue()), attributeValue);
        }
        return attributeValue;
    }

    //
    private float getValue(AttributeDefinition attributeDefinition, float baseValue) {
//        return applyOverrides(applyAdders(applyMultipliers(attributeDefinition, baseValue)));
        return 0.0f;
    }

    public float applyOverrides(AttributeDefinition attributeDefinition, float value) {
        float newValue = value;
        // Get modifiers from the effects we have
        for (Effect effect : effects) {
            for (AttributeModifier modifier : effect.getModifiers()) {
                if (modifier.getAttributeDefinition().equals(attributeDefinition)) {
                    if (modifier.getOperator().equals(Attribute.Operator.OVERRIDE)) {
                        newValue = modifier.getMagnitude();
                    }
                }
            }
        }
        return newValue;
    }

    public float applyMultipliers(AttributeDefinition attributeDefinition, float value) {
        float newValue = value;
        // Get modifiers from the effects we have
        for (Effect effect : effects) {
            for (AttributeModifier modifier : effect.getModifiers()) {
                if (modifier.getAttributeDefinition().equals(attributeDefinition)) {
                    if (modifier.getOperator().equals(Attribute.Operator.MULTIPLY)) {
                        newValue *= modifier.getMagnitude();
                    }
                }
            }
        }
        return newValue;
    }

    public float applyAdders(AttributeDefinition attributeDefinition, float value) {
        float newValue = value;
        // Get modifiers from the effects we have
        for (Effect effect : effects) {
            for (AttributeModifier modifier : effect.getModifiers()) {
                if (modifier.getAttributeDefinition().equals(attributeDefinition)) {
                    if (modifier.getOperator().equals(Attribute.Operator.ADD)) {
                        newValue += modifier.getMagnitude();
                    }
                }
            }
        }
        return newValue;
    }

    /**
     * @param modifier
     */
    public void applyTemporaryModifier(AttributeModifier modifier) {
//        getAttribute(modifier.getAttributeDefinition()).addModifier(modifier);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Map<AttributeDefinition, Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<AttributeDefinition, Attribute> attributes) {
        this.attributes = attributes;
    }

    public Attribute getAttribute(AttributeDefinition attributeDefinition) {
        return attributes.get(attributeDefinition);
    }

    public float getBaseValue(AttributeDefinition attributeDefinition) {
        return attributes.get(attributeDefinition).getBaseValue();
    }
}
