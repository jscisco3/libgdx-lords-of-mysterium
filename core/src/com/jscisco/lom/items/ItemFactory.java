package com.jscisco.lom.items;

import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.combat.Attack;
import com.jscisco.lom.combat.Damage;
import com.jscisco.lom.entity.Equipment;

import static com.jscisco.lom.combat.DamageType.SLASHING;

public class ItemFactory {

    public static Item buildSword() {
        return new Item().withName(new ItemName("Sword"))
                .withRarity(Rarity.NORMAL)
                .withValue(new ItemValue(5))
                .withGlyph(Assets.Glyphs.SWORD);

//        return new Item.Builder(new ItemType.Builder().withName("Sword")
//                .withDescription("A Cool Sword")
//                .withValue(5)
//                .withEquipSlot(Equipment.EquipmentSlot.HAND)
//                .build()
//        )
//                .withGlyph(Assets.Glyphs.SWORD)
//                .withAttack(new Attack(5, new Damage(SLASHING, 10)))
//                .build();
    }

//    public static Item buildBodyArmor() {
//        return new Item.Builder(new ItemType.Builder().withName("Body Armor")
//                .withDescription("A set of clinking armor")
//                .withValue(5)
//                .withEquipSlot(Equipment.EquipmentSlot.BODY).build()
//        )
//                .withGlyph(Assets.Glyphs.BODY_ARMOR)
//                .build();
//    }
//
//    public static Item buildBoots() {
//        return new Item.Builder(new ItemType.Builder().withName("Boots")
//                .withDescription("A pair of ill-fitting boots")
//                .withValue(5)
//                .withEquipSlot(Equipment.EquipmentSlot.BOOTS).build()
//        )
//                .withGlyph(Assets.Glyphs.BOOTS)
//                .build();
//    }
//
//    public static Item buildCloak() {
//        return new Item.Builder(new ItemType.Builder().withName("Cloak")
//                .withDescription("A swirly cloak")
//                .withValue(5)
//                .withEquipSlot(Equipment.EquipmentSlot.CLOAK).build()
//        )
//                .withGlyph(Assets.Glyphs.CLOAK)
//                .build();
//    }
//
//    public static Item buildGloves() {
//        return new Item.Builder(new ItemType.Builder().withName("Gloves")
//                .withDescription("A pair of ugly gloves")
//                .withValue(5)
//                .withEquipSlot(Equipment.EquipmentSlot.GLOVES).build()
//        )
//                .withGlyph(Assets.Glyphs.GLOVES)
//                .build();
//    }
//
//    public static Item buildHelmet() {
//        return new Item.Builder(new ItemType.Builder().withName("Helmet")
//                .withDescription("A dusty helmet")
//                .withValue(5)
//                .withEquipSlot(Equipment.EquipmentSlot.HELM).build()
//        )
//                .withGlyph(Assets.Glyphs.HELMET)
//                .build();
//    }
//
//    public static Item buildRing() {
//        return new Item.Builder(new ItemType.Builder().withName("Ring")
//                .withDescription("A shiny ring")
//                .withValue(5)
//                .withEquipSlot(Equipment.EquipmentSlot.RING).build()
//        )
//                .withGlyph(Assets.Glyphs.RING)
//                .build();
//    }

}
