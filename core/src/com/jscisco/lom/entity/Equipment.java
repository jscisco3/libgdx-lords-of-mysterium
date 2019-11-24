package com.jscisco.lom.entity;

import com.jscisco.lom.items.EquipmentSlot;
import com.jscisco.lom.items.Item;
import com.jscisco.lom.items.ItemCannotBeEquippedException;
import com.jscisco.lom.items.Slot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    /**
     * Equips [Item]
     *
     * @param item The item being equipped
     * @return Any items that had to be unequipped to make room for it. This is usually nothing or a single item.
     */
    public List<Item> equip(Item item) throws ItemCannotBeEquippedException {
        if (!item.getSlot().isPresent()) {
            throw new ItemCannotBeEquippedException();
        }
        Slot slotToBeFilled = item.getSlot().get();
        List<Item> unequipped = new ArrayList<>();
        // Handle hands and multi-handed items specially. We MAY need to preserve an invariant that
        // you can never hold a two-handed item and something else.
        // TODO: Feat that let's you hold two handed item in one hand
        if (slotToBeFilled == Slot.HAND) {
        }

        List<EquipmentSlot> validSlots = getSlotsByType(slotToBeFilled);
        for (EquipmentSlot s : validSlots) {
            // If we don't have an item, equip it.
            if (!s.hasItem()) {
                s.equip(item);
                return unequipped;
            }
        }
        // Otherwise, every slot has an item equipped, so lets
        // replace the first one with this new item.
        Optional<Item> replacedItem = validSlots.get(0).equip(item);
        replacedItem.ifPresent(unequipped::add);
        return unequipped;
    }

    public Item unequip(EquipmentSlot slot) {
        return slot.unequip();
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
