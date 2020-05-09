package com.jscisco.lom.domain.component;

public class Equipment {

    /**
     * We need a way for a user to specify _where_ they want to equip an item.
     * e.g. equip(item, 0);
     *
     * If we have multiple hands, we might do equip(item, 0..3)
     * If we have three hands, and a two handed item, we could do equip(item, 0..1)
     *
     * If we equip an item that occupies multiple slots, we should disable the other slots
     * Should consider if we can return the fact it is occupied by another slot
     *
     * So we may need a method that returns the list of possible slots for an item
     */



    public enum Slot {
        HAND,
        BODY,
        HEAD,
        NECKLACE,
        RING,
    }
}
