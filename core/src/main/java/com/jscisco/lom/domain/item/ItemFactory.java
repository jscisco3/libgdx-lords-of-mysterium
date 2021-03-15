package com.jscisco.lom.domain.item;

import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.Name;

public class ItemFactory {

    public static Item sword() {
        return new Item.Builder()
                .withName(Name.of("Sword"))
                .withAsset(Assets.sword)
                .ofType(ItemType.WEAPON)
                .build();
    }

    public static Item ring() {
        return new Item.Builder()
                .withName(Name.of("Ring"))
                .withAsset(Assets.ring)
                .ofType(ItemType.WEAPON)
                .build();
    }

}
