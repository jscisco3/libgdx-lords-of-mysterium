package com.jscisco.lom.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Equipment {
    private static final Logger logger = LoggerFactory.getLogger(Equipment.class);

    List<EquipSlot> equipment;

    public Equipment(EquipSlot... slots) {
        this.equipment = Arrays.asList(slots);
    }

    public boolean canEquip(Item item) {
        if (item.isEquippable()) {
            logger.info("Can equip it, but do we have any available slots?");
            return equipment.stream().anyMatch(eq -> eq.type == item.equippable().getSlot());
        }
        return false;
    }

    public Map<Integer, EquipSlot> possibleSlots(Item item) {
        Map<Integer, EquipSlot> slots = new HashMap<>();
        if (!item.isEquippable()) {
            logger.warn("Finding possible slots for an unequippable item.");
            return slots;
        }
        for (int i = 0; i < equipment.size(); i++) {
            if (equipment.get(i).canEquip(item)) {
                slots.put(i, equipment.get(i));
            }
        }
        return slots;
    }

    public Item equipToDefaultSlot(Item item) {
        Map<Integer, EquipSlot> slots = possibleSlots(item);
        if (slots.size() == 1) {
            return slots.get(0).equip(item);
        }
        throw new IllegalArgumentException("Item does not have a default slot");
    }

    public enum EquipmentType {
        HAND,
        OFFHAND,
        AMULET,
        RING,
        HEAD,
        BODY,
        GLOVES
    }

}
