package com.jscisco.lom.shelf.entity;

import com.jscisco.lom.shelf.items.EquipmentSlot;
import com.jscisco.lom.shelf.items.Item;
import com.jscisco.lom.shelf.items.Slot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Equipment {

    private static final Logger logger = LoggerFactory.getLogger(Equipment.class);

    private List<EquipmentSlot> slots;

    public Equipment() {
        this.slots = new ArrayList<>();
        // The available slots for this equipment.
        // Certain bodies might have more availabe!?
        this.slots.add(new EquipmentSlot(Slot.HAND));
        this.slots.add(new EquipmentSlot(Slot.HAND));
        this.slots.add(new EquipmentSlot(Slot.RING));
        this.slots.add(new EquipmentSlot(Slot.RING));
        this.slots.add(new EquipmentSlot(Slot.NECKLACE));
        this.slots.add(new EquipmentSlot(Slot.BODY));
        this.slots.add(new EquipmentSlot(Slot.CLOAK));
        this.slots.add(new EquipmentSlot(Slot.HELM));
        this.slots.add(new EquipmentSlot(Slot.BOOTS));
    }

    public int getNumberOfEquippedItems() {
        return (int) this.slots.stream()
                .filter(EquipmentSlot::hasItem)
                .count();
    }

    public boolean canEquip(Item item) {
        if (!item.getSlot().isPresent()) {
            return false;
        }
        return slots.stream().anyMatch(slot -> slot.getSlot() == item.getSlot().get());
    }

    public List<EquipmentSlot> getSlots() {
        return slots;
    }

    public List<Item> getWeapons() {
        List<EquipmentSlot> possibleSlots = this.slots.stream()
                .filter(slot -> slot.getSlot().equals(Slot.HAND))
                .collect(Collectors.toList());

        List<Item> items = new ArrayList<>();
        for (EquipmentSlot s : possibleSlots) {
            if (s.getItem().isPresent()) {
                Item item = s.getItem().get();
                if (item.getAttack().isPresent()) {
                    items.add(item);
                }
            }
        }
        return items;
    }

    public List<EquipmentSlot> getSlotsByType(Slot slot) {
        return slots.stream().filter(s -> s.getSlot().equals(slot)).collect(Collectors.toList());
    }

    public EquipmentSlot getSlotByIndex(int index) {
        if (index > slots.size()) {
            logger.error("The provided index: {} is out of range for the equipment slots.", index);
            throw new IndexOutOfBoundsException();
        }
        return slots.get(index);
    }

}
