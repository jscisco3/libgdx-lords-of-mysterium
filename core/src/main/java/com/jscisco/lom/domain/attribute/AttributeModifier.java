package com.jscisco.lom.domain.attribute;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AttributeModifier {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "attribute_id", nullable = false)
    protected Attribute attribute;

    protected float magnitude;

    @Enumerated(value = EnumType.STRING)
    protected Attribute.Operator operator;

    public AttributeModifier() {

    }

    public AttributeModifier forAttribute(Attribute attribute) {
        this.attribute = attribute;
        return this;
    }

    public AttributeModifier withMagnitude(float magnitude) {
        this.magnitude = magnitude;
        return this;
    }

    public AttributeModifier withOperator(Attribute.Operator operator) {
        this.operator = operator;
        return this;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public float getMagnitude() {
        return magnitude;
    }

    public Attribute.Operator getOperator() {
        return operator;
    }
}
