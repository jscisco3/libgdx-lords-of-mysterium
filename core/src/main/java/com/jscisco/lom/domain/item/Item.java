package com.jscisco.lom.domain.item;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.Glyph;
import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.Inventory;
import com.jscisco.lom.domain.zone.Level;

import javax.annotation.Nullable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
@SequenceGenerator(
        name = "item_sequence",
        sequenceName = "item_sequence",
        initialValue = 1,
        allocationSize = 1
)
public class Item {

    // TODO: Random UUID
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_sequence")
    private Long id;

    @Embedded
    private Name name;

    @Transient
    private Glyph glyph;

    @Embedded
    @Nullable
    private Position position;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    @ManyToOne(optional = true)
    @Nullable
    private Level level;

    @ManyToOne(optional = true)
    @Nullable
    private Inventory inventory;

    public Item() {

    }

    public static class Builder {
        Name name;
        ItemType itemType;
        Glyph glyph;


        public Builder withName(Name name) {
            this.name = name;
            return this;
        }

        public Builder ofType(ItemType itemType) {
            this.itemType = itemType;
            return this;
        }

        public Builder withGlyph(Glyph glyph) {
            this.glyph = glyph;
            return this;
        }

        public Item build() {
            Item item = new Item();
            item.name = this.name;
            item.glyph = this.glyph;
            item.itemType = this.itemType;
            return item;
        }
    }

    public void draw(SpriteBatch batch, Assets assets) {
        TextureRegion t = assets.getTextureRegion(this.glyph);
        Sprite s = new Sprite(t);
        s.setSize(24f, 24f);
        s.setPosition(s.getWidth() * position.getX(), s.getHeight() * position.getY());
        s.draw(batch);
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Nullable
    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(@Nullable Inventory inventory) {
        this.inventory = inventory;
    }
}
