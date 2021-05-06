package com.jscisco.lom.domain.attribute;

import com.jscisco.lom.domain.Description;
import com.jscisco.lom.domain.Name;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Attribute {

    public enum Operator {
        ADD,
        MULTIPLY,
        OVERRIDE
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private Description description;

//    @MapKeyEnumerated(value = EnumType.STRING)
//    private AttributeSet.AttributeDefinition attributeDefinition;

    private float baseValue;

    @OneToMany(mappedBy = "attribute", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private final List<AttributeModifier> modifiers = new ArrayList<>();

    public Attribute() {
    }

    public Attribute(Name name, Description description) {
        this.name = name;
        this.description = description;
        this.baseValue = 0f;
    }

    public Attribute(Name name, Description description, float baseValue) {
        this.name = name;
        this.description = description;
        this.baseValue = baseValue;
    }

    public float getValue() {
        return applyOverrides(applyAdders(applyMultipliers(baseValue)));
    }

    // Note: If there are multiple overrides, then the most recently added override will be applied
    public float applyOverrides(float value) {
        float newValue = value;
        for (AttributeModifier modifier : modifiers) {
            if (modifier.getOperator().equals(Operator.OVERRIDE)) {
                newValue = modifier.getMagnitude();
            }
        }
        return newValue;
    }

    public float applyMultipliers(float value) {
        float newValue = value;
        for (AttributeModifier modifier : modifiers) {
            if (modifier.getOperator().equals(Operator.MULTIPLY)) {
                newValue *= modifier.getMagnitude();
            }
        }
        return newValue;
    }

    public float applyAdders(float value) {
        float newValue = value;
        for (AttributeModifier modifier : modifiers) {
            if (modifier.getOperator().equals(Operator.ADD)) {
                newValue += modifier.getMagnitude();
            }
        }
        return newValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }

    public float getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(float baseValue) {
        this.baseValue = baseValue;
    }

    public void addModifier(AttributeModifier modifier) {
        this.modifiers.add(modifier);
    }

    public void removeModifier(AttributeModifier modifier) {
        this.modifiers.remove(modifier);
    }

    public List<AttributeModifier> getModifiers() {
        return modifiers;
    }
}
