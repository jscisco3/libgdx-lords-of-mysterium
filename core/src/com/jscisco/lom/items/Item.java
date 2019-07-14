package com.jscisco.lom.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.util.Position;

public class Item {

    private Item() {

    }

    private ItemType itemType;
    /**
     * Items have a non-null position only if they are on the ground
     */
    private Position position;
    private TextureRegion texture;

    // Do items need to have a reference to the stage they are in?

    public static class Builder {
        private ItemType itemType;
        private Position position;
        private TextureRegion textureRegion;

        public Builder(ItemType itemType) {
            this.itemType = itemType;
        }

        public Builder withPosition(Position position) {
            this.position = position;
            return this;
        }

        public Builder withTextureRegion(TextureRegion textureRegion) {
            this.textureRegion = textureRegion;
            return this;
        }

        public Item build() {
            Item item = new Item();
            item.itemType = this.itemType;
            item.position = this.position;
            item.texture = this.textureRegion;
            return item;
        }

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
        return texture;
    }

    @Override
    public String toString() {
        return String.format("%s - %s   | $%s", this.itemType.getName(), this.itemType.getDescription(), this.itemType.getValue());
    }
}
