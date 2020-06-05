package com.jscisco.lom.domain.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ItemRepository {

    public ItemRepository() {
        Gson gson = new GsonBuilder().create();
    }

    public static GameObject createSword() {
        GameObject item = new GameObject();
        item.name = new EntityName("Bronze shortsword");
        item.item = new Item();
        item.item.equippable = new Equippable(Equipment.EquipmentType.HAND);
        return item;
    }

    public static GameObject createPotion() {
        GameObject item = new GameObject();
        item.name = new EntityName("Small Health Potion");
        return item;
    }

}
