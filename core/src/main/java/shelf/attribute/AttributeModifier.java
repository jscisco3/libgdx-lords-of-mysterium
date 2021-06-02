package shelf.attribute;

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

    @Enumerated(EnumType.STRING)
    protected Attribute.Operator operator;

    protected float magnitude;

    @Enumerated(EnumType.STRING)
    private AttributeSet.AttributeDefinition attributeDefinition;

    @ManyToOne
    @JoinColumn(name = "effect_id", nullable = true)
    private Effect effect;

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


    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
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
