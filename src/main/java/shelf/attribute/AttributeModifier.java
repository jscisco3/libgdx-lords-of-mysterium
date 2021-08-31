package shelf.attribute;

public class AttributeModifier {

    private Long id;

    protected Attribute.Operator operator;

    protected float magnitude;

    private AttributeSet.AttributeDefinition attributeDefinition;

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
