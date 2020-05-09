package com.jscisco.lom.shelf.items;

import java.util.Optional;

public class EquipmentSlot {

    private Optional<Item> item;
    private Slot slot;

    public EquipmentSlot(Slot slot) {
        item = Optional.empty();
        this.slot = slot;
    }

    /**
     * Equip an item to this slot.
     * Return an item that already occupies this slot if present
     * Otherwise, return an empty Optional.
     *
     * @param item the item to be equipped
     * @return an Optional that is either empty or contains the item being unequipped.
     * @throws ItemCannotBeEquippedException
     */
    public Optional<Item> equip(Item item) throws ItemCannotBeEquippedException {
        if (item.getSlot().isPresent()) {
            if (this.slot != item.getSlot().get()) {
                throw new ItemCannotBeEquippedException();
            }
        }
        if (this.hasItem()) {
            Item unequipped = this.item.get();
            this.item = Optional.of(item);
            return Optional.of(unequipped);
        } else {
            this.item = Optional.of(item);
        }
        return Optional.empty();
    }

    public Item unequip() {
        if (this.item.isPresent()) {
            Item item = this.item.get();
            this.item = Optional.empty();
            return item;
        }
        return null;
    }

    public Optional<Item> getItem() {
        return item;
    }

    public Slot getSlot() {
        return slot;
    }

    public boolean hasItem() {
        return item.isPresent();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.slot);
        sb.append(" - ");
        sb.append(getItem().isPresent() ? getItem().get().getItemName() : "Nothing");
        return sb.toString();
    }
}
