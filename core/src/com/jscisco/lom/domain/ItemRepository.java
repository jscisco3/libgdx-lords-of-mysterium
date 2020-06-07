package com.jscisco.lom.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ItemRepository {

    public ItemRepository() {
        Gson gson = new GsonBuilder().create();
    }

    public static Item createSword() {
        Item item = Item.equipment(EntityName.of("Bronze shortsword"), new Equippable(Equipment.EquipmentType.HAND));
        return item;
    }

    public static Item createPotion() {
        Item item = Item.nonEquippable(EntityName.of("Small Health Potion"));
        return item;
    }

}
