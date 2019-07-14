package com.jscisco.lom.items;

import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.attributes.Equipment;

public class ItemFactory {

    public static Item buildSword() {
        Item sword = new Item.Builder(new ItemType.Builder().withName("Sword")
                .withDescription("A Cool Sword")
                .withValue(5)
                .withEquipSlot(Equipment.EquipmentSlot.HAND).build()
        )
                .withTextureRegion(Assets.sword)
                .build();
        return sword;
    }

    public static Item buildBodyArmor() {
        return new Item.Builder(new ItemType.Builder().withName("Body Armor")
                .withDescription("A set of clinking armor")
                .withValue(5)
                .withEquipSlot(Equipment.EquipmentSlot.BODY).build()
        )
                .withTextureRegion(Assets.body_armor)
                .build();
    }

    public static Item buildBoots() {
        return new Item.Builder(new ItemType.Builder().withName("Boots")
                .withDescription("A pair of ill-fitting boots")
                .withValue(5)
                .withEquipSlot(Equipment.EquipmentSlot.BOOTS).build()
        )
                .withTextureRegion(Assets.boots)
                .build();
    }

    public static Item buildCloak() {
        return new Item.Builder(new ItemType.Builder().withName("Cloak")
                .withDescription("A swirly cloak")
                .withValue(5)
                .withEquipSlot(Equipment.EquipmentSlot.CLOAK).build()
        )
                .withTextureRegion(Assets.cloak)
                .build();
    }

    public static Item buildGloves() {
        return new Item.Builder(new ItemType.Builder().withName("Gloves")
                .withDescription("A pair of ugly gloves")
                .withValue(5)
                .withEquipSlot(Equipment.EquipmentSlot.GLOVES).build()
        )
                .withTextureRegion(Assets.gloves)
                .build();
    }

    public static Item buildHelmet() {
        return new Item.Builder(new ItemType.Builder().withName("Helmet")
                .withDescription("A dusty helmet")
                .withValue(5)
                .withEquipSlot(Equipment.EquipmentSlot.HELM).build()
        )
                .withTextureRegion(Assets.helmet)
                .build();
    }

    public static Item buildRing() {
        return new Item.Builder(new ItemType.Builder().withName("Ring")
                .withDescription("A shiny ring")
                .withValue(5)
                .withEquipSlot(Equipment.EquipmentSlot.RING).build()
        )
                .withTextureRegion(Assets.ring)
                .build();
    }

}
