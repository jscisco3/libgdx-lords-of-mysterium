package com.jscisco.lom.domain.item;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.Name;

public class Item {

    private Name name;
    private AssetDescriptor<Texture> asset;
    private ItemType itemType;

    private Item() {

    }

    public static class Builder {
        Name name;
        ItemType itemType;
        AssetDescriptor<Texture> asset;


        public Builder withName(Name name) {
            this.name = name;
            return this;
        }

        public Builder ofType(ItemType itemType) {
            this.itemType = itemType;
            return this;
        }

        public Builder withAsset(AssetDescriptor<Texture> asset) {
            this.asset = asset;
            return this;
        }

        public Item build() {
            Item item = new Item();
            item.name = this.name;
            item.asset = this.asset;
            item.itemType = this.itemType;
            return item;
        }
    }

    public void draw(SpriteBatch batch, Assets assets, int x, int y) {
        Texture t = assets.getTexture(this.asset);
        Sprite s = new Sprite(t);
        s.setSize(24f, 24f);
        s.setPosition(s.getWidth() * x, s.getHeight() * y);
        s.draw(batch);
    }

    public Name getName() {
        return name;
    }

    public AssetDescriptor<Texture> getAsset() {
        return asset;
    }
}
