package com.jscisco.lom.domain.item;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.Glyph;
import com.jscisco.lom.domain.Name;

public class Item {

    private Name name;
    private Glyph glyph;
    private ItemType itemType;

    private Item() {

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

    public void draw(SpriteBatch batch, Assets assets, int x, int y) {
        TextureRegion t = assets.getTextureRegion(this.glyph);
        Sprite s = new Sprite(t);
        s.setSize(24f, 24f);
        s.setPosition(s.getWidth() * x, s.getHeight() * y);
        s.draw(batch);
    }

    public Name getName() {
        return name;
    }

}
