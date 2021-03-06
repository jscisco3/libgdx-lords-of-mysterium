package shelf.attribute;

import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@javax.persistence.Entity
public abstract class Effect {

    @Id
    UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "entity_id", nullable = false)
    protected AttributeSet attributeSet;

    @Embedded
    protected Duration duration;

    @OneToMany(mappedBy = "effect")
    protected List<AttributeModifier> modifiers = new ArrayList<>();

    @Transient
    protected List<Tag> grantedTags = new ArrayList<>();


    public Effect withDuration(Duration duration) {
        this.duration = duration;
        return this;
    }

    public Effect addModifier(AttributeModifier modifier) {
        this.modifiers.add(modifier);
        modifier.setEffect(this);
        return this;
    }

    public Effect grantTag(Tag tag) {
        this.grantedTags.add(tag);
        return this;
    }

    public List<AttributeModifier> getModifiers() {
        return modifiers;
    }

    public abstract void apply(AttributeSet attributeSet);

    public void tick() {
        if (!this.duration.isPermanent()) {
            this.duration.decrementDuration();
        }
    }

    public boolean isExpired() {
        return this.duration.isExpired();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public AttributeSet getAttributeSet() {
        return attributeSet;
    }

    public void setAttributeSet(AttributeSet attributeSet) {
        this.attributeSet = attributeSet;
    }

    public void setModifiers(List<AttributeModifier> modifiers) {
        this.modifiers = modifiers;
    }

    public List<Tag> getGrantedTags() {
        return grantedTags;
    }

    public void setGrantedTags(List<Tag> grantedTags) {
        this.grantedTags = grantedTags;
    }
}
