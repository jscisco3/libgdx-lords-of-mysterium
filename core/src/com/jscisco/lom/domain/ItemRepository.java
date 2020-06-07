package com.jscisco.lom.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ItemRepository {

    public ItemRepository() {
        Gson gson = new GsonBuilder().create();
    }

    public static GameObject createSword() {
        GameObject item = GameObject.item(new EntityName("Bronze shortsword"), new Item());
        assert item.item != null;
        item.item.equippable = new Equippable(Equipment.EquipmentType.HAND);
        return item;
    }

    public static GameObject createPotion() {
        GameObject item = GameObject.item(new EntityName("Small Health Potion"), new Item());
        item.name = new EntityName("Small Health Potion");
        return item;
    }

}
