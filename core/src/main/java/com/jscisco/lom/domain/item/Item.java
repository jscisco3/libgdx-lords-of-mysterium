package com.jscisco.lom.domain.item;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
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
}
