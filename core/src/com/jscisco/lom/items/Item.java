package com.jscisco.lom.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.util.Position;

import java.util.Optional;

public class Item {

    /**
     * If position is present, then it is on the ground.
     */
    private Optional<Position> position;
    private Assets.Glyphs glyph;
    private ItemValue value;
    private Rarity rarity;
    private ItemLevel itemLevel;
    private ItemName itemName;

    private ItemType itemType;

    private Item() {
    }

    public static class Builder {
        private ItemName name;
        private ItemValue value;
        private Position position;
        private Assets.Glyphs glyph;
        private Rarity rarity;

        public Builder() {
        }

        public Builder withPosition(Position position) {
            this.position = position;
            return this;
        }

        public Builder withValue(ItemValue value) {
            this.value = value;
            return this;
        }

        public Builder withName(ItemName name) {
            this.name = name;
            return this;
        }

        public Builder withGlyph(Assets.Glyphs glyph) {
            this.glyph = glyph;
            return this;
        }

        public Builder withRarity(Rarity rarity) {
            this.rarity = rarity;
            return this;
        }

        public Item build() {
            Item item = new Item();
            item.position = this.position != null ? Optional.of(this.position) : Optional.empty();
            item.itemName = this.name;
            item.glyph = this.glyph;
            item.rarity = rarity;
            return item;
        }
    }

    public Item withName(ItemName name) {
        this.itemName = name;
        return this;
    }

    public Item withRarity(Rarity rarity) {
        this.rarity = rarity;
        return this;
    }

    public Item withValue(ItemValue value) {
        this.value = value;
        return this;
    }

    public Item withGlyph(Assets.Glyphs glyph) {
        this.glyph = glyph;
        return this;
    }

    public Optional<Position> getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return Assets.textureMap.get(this.glyph);
    }

    public Assets.Glyphs getGlyph() {
        return glyph;
    }

    public ItemValue getValue() {
        return value;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public ItemLevel getItemLevel() {
        return itemLevel;
    }

    public ItemName getItemName() {
        return itemName;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setPosition(Position position) {
        if (position == null) {
            this.position = Optional.empty();
        } else {
            this.position = Optional.of(position);
        }
    }

    @Override
    public String toString() {
        return String.format("%s | $%s", this.itemName.getName(), this.value.getValue());
    }
}
