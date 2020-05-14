package com.jscisco.lom.domain.item;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jscisco.lom.domain.component.Equipment;

public class ItemRepository {

    public ItemRepository() {
        Gson gson = new GsonBuilder().create();
    }

    public static Item createSword() {
        Item item = new Item();
        item.name = new ItemName("Bronze shortsword");
        item.equippable = new Equippable(Equipment.EquipmentType.HAND);
        return item;
    }

    public static Item createPotion() {
        Item item = new Item();
        item.name = new ItemName("Small Health Potion");
        return item;
    }

}
