package com.jscisco.lom.attributes;

import com.jscisco.lom.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Equipment {

    private List<EquipmentSlot> slotTypes;
    private List<Item> slots;

    public Equipment() {
        this.slots = new ArrayList<>();
        // The available slots for this equipment.
        // Certain bodies might have more availabe!?
        this.slotTypes = new ArrayList<>();
        this.slotTypes.add(EquipmentSlot.HAND);
        this.slotTypes.add(EquipmentSlot.HAND);
        this.slotTypes.add(EquipmentSlot.RING);
        this.slotTypes.add(EquipmentSlot.RING);
        this.slotTypes.add(EquipmentSlot.NECKLACE);
        this.slotTypes.add(EquipmentSlot.BODY);
        this.slotTypes.add(EquipmentSlot.CLOAK);
        this.slotTypes.add(EquipmentSlot.HELM);
        this.slotTypes.add(EquipmentSlot.GLOVES);
        this.slotTypes.add(EquipmentSlot.BOOTS);

        for (int i = 0; i < this.slotTypes.size(); i++) {
            this.slots.add(null);
        }
    }

    public int getNumberOfEquippedItems() {
        return (int) this.slots.stream()
                .filter(Objects::nonNull).count();
    }

    public boolean canEquip(Item item) {
        return slotTypes.stream().anyMatch(slot -> item.getItemType().getEquipSlot() == slot);
    }

    /**
     * Equips [Item]
     *
     * @param item The item being equipped
     * @return Any items that had to be unequipped to make room for it. This is usually nothing or a single item.
     */
    public List<Item> equip(Item item) {
        List<Item> unequipped = new ArrayList<>();
        // Handle hands and multi-handed items specially. We MAY need to preserve an invariant that
        // you can never hold a two-handed item and something else.
        // TODO: Feat that let's you hold two handed item in one hand
        if (item.getItemType().getEquipSlot() == EquipmentSlot.HAND) {

        }

        int usedSlot = -1;
        for (int i = 0; i < slotTypes.size(); i++) {
            if (slotTypes.get(i).equals(item.getItemType().getEquipSlot())) {
                if (slots.get(i) == null) {
                    // This is an empty slot, so put the item here.
                    slots.set(i, item);
                    return unequipped;
                } else {
                    // Found suitable slot, but it is occupied
                    usedSlot = i;
                }
            }
        }

        // If we get here, all matching slots are full. So swap out an item.
        assert (usedSlot != -1);
        unequipped.add(this.slots.get(usedSlot));
        this.slots.set(usedSlot, item);
        return unequipped;
    }

    public Item unequip(int index) {
        Item unequipped = this.slots.get(index);
        this.slots.set(index, null);
        return unequipped;
    }

    public List<EquipmentSlot> getSlotTypes() {
        return slotTypes;
    }

    public List<Item> getSlots() {
        return slots;
    }

    public List<Item> getWeapons() {
        return slots.stream().filter(i -> i != null && i.getAttack().isPresent()).collect(Collectors.toList());
    }

    public enum EquipmentSlot {
        HAND,
        RING,
        NECKLACE,
        BODY,
        CLOAK,
        HELM,
        GLOVES,
        BOOTS,
        TAIL
    }
}
