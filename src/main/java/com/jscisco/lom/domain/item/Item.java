package com.jscisco.lom.domain.item;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.raws.RawItem;

import java.util.UUID;

public class Item {

    private UUID id = UUID.randomUUID();

    private Name name;

    private String glyph = Assets.ring;

    private int weight;
    private int value;

    private Position position;

    private ItemCategory itemCategory;

    public Item() {

    }

    public static Item from(RawItem raw) {
        Item item = new Item();
        item.name = Name.of(raw.name);
        item.glyph = raw.glyph;
        item.weight = raw.weight;
        item.value = raw.value;
        item.itemCategory = ItemCategory.valueOf(raw.category);
        // TODO: Consumable, Equippable
        return item;
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

    public ItemCategory getItemType() {
        return itemCategory;
    }

    public void setItemType(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
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
