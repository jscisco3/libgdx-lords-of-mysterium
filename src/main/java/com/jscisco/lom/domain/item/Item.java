package com.jscisco.lom.domain.item;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.Position;

import java.util.UUID;

public class Item {

    private UUID id = UUID.randomUUID();

    private Name name;

    private String glyph = Assets.ring;

    private Position position;

    private ItemType itemType;

    public Item() {

    }

    public static class Builder {
        Name name;
        ItemType itemType;
        String glyph;

        public Builder withName(Name name) {
            this.name = name;
            return this;
        }

        public Builder ofType(ItemType itemType) {
            this.itemType = itemType;
            return this;
        }

        public Builder withGlyph(String glyph) {
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

    public UUID getId() {
        return id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getGlyph() {
        return glyph;
    }

    public void setGlyph(String glyph) {
        this.glyph = glyph;
    }

}
