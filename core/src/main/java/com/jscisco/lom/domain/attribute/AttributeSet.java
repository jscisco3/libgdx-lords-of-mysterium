package com.jscisco.lom.domain.attribute;

import com.jscisco.lom.domain.Description;
import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.entity.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.Map;

import static com.jscisco.lom.domain.MathUtils.clamp;

/**
 * Responsible for maintaining relevant attributes
 */
@javax.persistence.Entity
public class AttributeSet {

    public enum AttributeDefinition {
        HEALTH,
        MAX_HEALTH,
        LIGHT_RADIUS
    }

    @Id
    @Column(name = "entity_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "entity_id")
    private Entity entity;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    @JoinTable(name = "attribute_set_attribute_mapping",
//            joinColumns = {@JoinColumn(name = "attribute_set_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "attribute_id", referencedColumnName = "id")})
    @MapKeyEnumerated(value = EnumType.STRING)
    private Map<AttributeDefinition, Attribute> attributes = new HashMap<>();

    public AttributeSet() {
        attributes.put(AttributeDefinition.HEALTH, new Attribute(Name.of("Health"), Description.of("Current Health")));
        attributes.put(AttributeDefinition.MAX_HEALTH, new Attribute(Name.of("Max Health"), Description.of("Maximum Health")));
        attributes.put(AttributeDefinition.LIGHT_RADIUS, new Attribute(Name.of("Light Radius"), Description.of("Light Radius")));
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

    public void applyBaseValueModifier(AttributeModifier modifier) {
        float newValue = 0f;

        switch (modifier.operator) {
            case MULTIPLY:
                newValue = modifier.getAttribute().getBaseValue() * modifier.getMagnitude();
                break;
            case ADD:
                newValue = modifier.getAttribute().getBaseValue() + modifier.getMagnitude();
                break;
            case OVERRIDE:
                newValue = modifier.getMagnitude();
                break;
        }
        // TODO: FIX
        // Health can never permanently be greater than the max health.
//        if (modifier.getAttribute().equals(this.health)) {
//            newValue = (clamp(0f, this.maxHealth.getValue(), newValue));
//        }
        modifier.getAttribute().setBaseValue(newValue);
    }

    public float getAttributeValue(AttributeDefinition attributeDefinition) {
        Attribute attribute = this.attributes.get(attributeDefinition);
        float attributeValue = attribute.getValue();
        if (attributeDefinition.equals(AttributeDefinition.HEALTH)) {
            attributeValue = clamp(attribute.getValue(), this.attributes.get(AttributeDefinition.MAX_HEALTH).getValue(), attributeValue);
        }
        return attributeValue;
    }

    /**
     * @param modifier
     */
    public void applyTemporaryModifier(AttributeModifier modifier) {
        modifier.getAttribute().addModifier(modifier);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
