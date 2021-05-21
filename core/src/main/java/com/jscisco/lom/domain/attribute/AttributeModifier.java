package com.jscisco.lom.domain.attribute;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class AttributeModifier {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    protected AttributeSet.AttributeDefinition attributeDefinition;

    protected float magnitude;

    @Enumerated(value = EnumType.STRING)
    protected Attribute.Operator operator;

    @ManyToOne
    @JoinColumn(name = "attribute_id", nullable = false)
    private Attribute attribute;

    public AttributeModifier() {

    }

    public AttributeModifier forAttribute(AttributeSet.AttributeDefinition attribute) {
        this.attributeDefinition = attribute;
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

    public AttributeSet.AttributeDefinition getAttributeDefinition() {
        return attributeDefinition;
    }

    public float getMagnitude() {
        return magnitude;
    }

    public Attribute.Operator getOperator() {
        return operator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAttributeDefinition(AttributeSet.AttributeDefinition attributeDefinition) {
        this.attributeDefinition = attributeDefinition;
    }

    public void setMagnitude(float magnitude) {
        this.magnitude = magnitude;
    }

    public void setOperator(Attribute.Operator operator) {
        this.operator = operator;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return "AttributeModifier{" +
                "id=" + id +
                ", attribute=" + attributeDefinition +
                ", magnitude=" + magnitude +
                ", operator=" + operator +
                '}';
    }
}
