package com.jscisco.lom.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.combat.Attack;
import com.jscisco.lom.util.Position;

public class Item {

    private Item() {

    }

    private ItemType itemType;
    /**
     * Items have a non-null position only if they are on the ground
     */
    private Position position;
    private Assets.Glyphs glyph;
    private Attack attack;

    // Do items need to have a reference to the stage they are in?

    public Attack getAttack() {
        return attack;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public TextureRegion getTexture() {
        return Assets.textureMap.get(this.glyph);
    }

    public Assets.Glyphs getGlyph() {
        return glyph;
    }

    public static class Builder {
        private ItemType itemType;
        private Position position;
        private Assets.Glyphs glyph;
        private Attack attack;

        public Builder(ItemType itemType) {
            this.itemType = itemType;
        }

        public Builder withPosition(Position position) {
            this.position = position;
            return this;
        }

        public Builder withGlyph(Assets.Glyphs glyph) {
            this.glyph = glyph;
            return this;
        }

        public Builder withAttack(Attack attack) {
            this.attack = attack;
            return this;
        }

        public Item build() {
            Item item = new Item();
            item.itemType = this.itemType;
            item.position = this.position;
            item.glyph = this.glyph;
            item.attack = this.attack;
            return item;
        }

    }

    @Override
    public String toString() {
        return String.format("%s - %s   | $%s", this.itemType.getName(), this.itemType.getDescription(), this.itemType.getValue());
    }
}
