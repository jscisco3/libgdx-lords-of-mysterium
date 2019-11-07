package com.jscisco.lom.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.combat.Attack;
import com.jscisco.lom.util.Position;

public class Item {

    /**
     * Items have a non-null position only if they are on the ground
     */
    private Position position;
    private Assets.Glyphs glyph;
    private Attack attack;
    private ItemType itemType;

    private ItemValue value;
    private Rarity rarity;
    private ItemLevel itemLevel;
    private ItemName itemName;

    public Item() {
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

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public void setGlyph(Assets.Glyphs glyph) {
        this.glyph = glyph;
    }

    public void setAttack(Attack attack) {
        this.attack = attack;
    }

    @Override
    public String toString() {
        return String.format("%s - %s   | $%s", this.itemType.getName(), this.itemType.getDescription(), this.itemType.getValue());
    }
}
