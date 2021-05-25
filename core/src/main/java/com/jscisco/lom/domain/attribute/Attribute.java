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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(
        name = "attribute_sequence",
        sequenceName = "attribute_sequence",
        initialValue = 1,
        allocationSize = 1
)
public class Attribute {

    public enum Operator {
        ADD,
        MULTIPLY,
        OVERRIDE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attribute_sequence")
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private Description description;

    private float baseValue;

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

    // TODO: Put this in the attribute set.

//    public float getValue() {
//        return applyOverrides(applyAdders(applyMultipliers(baseValue)));
//    }

    // Note: If there are multiple overrides, then the most recently added override will be applied
//    public float applyOverrides(float value) {
//        float newValue = value;
//        for (AttributeModifier modifier : modifiers) {
//            if (modifier.getOperator().equals(Operator.OVERRIDE)) {
//                newValue = modifier.getMagnitude();
//            }
//        }
//        return newValue;
//    }
//
//    public float applyMultipliers(float value) {
//        float newValue = value;
//        for (AttributeModifier modifier : modifiers) {
//            if (modifier.getOperator().equals(Operator.MULTIPLY)) {
//                newValue *= modifier.getMagnitude();
//            }
//        }
//        return newValue;
//    }
//
//    public float applyAdders(float value) {
//        float newValue = value;
//        for (AttributeModifier modifier : modifiers) {
//            if (modifier.getOperator().equals(Operator.ADD)) {
//                newValue += modifier.getMagnitude();
//            }
//        }
//        return newValue;
//    }

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

    @Override
    public String toString() {
        return "Attribute{" +
                "id=" + id +
                ", name=" + name +
                ", description=" + description +
                ", baseValue=" + baseValue +
                '}';
    }
}
