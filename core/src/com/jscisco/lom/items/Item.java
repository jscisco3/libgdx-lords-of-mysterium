package com.jscisco.lom.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.combat.Attack;
import com.jscisco.lom.util.Position;

import java.util.Optional;

public class Item {

    private ItemName itemName;
    private ItemValue value;
    private Rarity rarity;
    /**
     * If present, the base attack this item grants when equipped
     */
    private Optional<Attack> attack;
    /**
     * If present, the equipment slot this item requires to be equipped.
     * Must be present to be equipped!
     */
    private Optional<EquipmentSlot> equipmentSlot;
    /**
     * If position is present, then it is on the ground.
     */
    private Optional<Position> position;
    /**
     * Visual representation of the item on the ground
     */
    private Assets.Glyphs glyph;
    private ItemLevel itemLevel;

    private Item() {
    }

    public Optional<Position> getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        if (position == null) {
            this.position = Optional.empty();
        } else {
            this.position = Optional.of(position);
        }
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

    public Optional<Attack> getAttack() {
        return attack;
    }

    public Optional<EquipmentSlot> getEquipmentSlot() {
        return equipmentSlot;
    }

    @Override
    public String toString() {
        return String.format("%s | $%s", this.itemName.getName(), this.value.getValue());
    }

    public static class Builder {
        private ItemName name;
        private ItemValue value;
        private Rarity rarity;
        private Attack attack;
        private EquipmentSlot equipmentSlot;
        private Position position;
        private Assets.Glyphs glyph;

        public Builder() {
        }

        public Builder withName(ItemName name) {
            this.name = name;
            return this;
        }

        public Builder withValue(ItemValue value) {
            this.value = value;
            return this;
        }

        public Builder withRarity(Rarity rarity) {
            this.rarity = rarity;
            return this;
        }

        public Builder withAttack(Attack attack) {
            this.attack = attack;
            return this;
        }

        public Builder withEquipmentSlot(EquipmentSlot equipmentSlot) {
            this.equipmentSlot = equipmentSlot;
            return this;
        }

        public Builder withPosition(Position position) {
            this.position = position;
            return this;
        }

        public Builder withGlyph(Assets.Glyphs glyph) {
            this.glyph = glyph;
            return this;
        }

        public Item build() {
            Item item = new Item();
            item.itemName = this.name;
            item.value = this.value;
            item.rarity = this.rarity;
            item.attack = this.attack != null ? Optional.of(this.attack) : Optional.empty();
            item.equipmentSlot = this.equipmentSlot != null ? Optional.of(this.equipmentSlot) : Optional.empty();
            item.position = this.position != null ? Optional.of(this.position) : Optional.empty();
            item.glyph = this.glyph;
            return item;
        }
    }
}
