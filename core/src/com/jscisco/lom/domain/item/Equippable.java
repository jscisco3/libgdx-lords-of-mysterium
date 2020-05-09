package com.jscisco.lom.domain.item;

import com.jscisco.lom.domain.component.Equipment;

import java.util.List;

public class Equippable {

    /**
     * We need to define the possible slots an item can contain
     * We could have multiple possibilities.
     *
     * For example, we could have an item that takes up a BODY and HEAD slot
     * Or we could have some funny item that takes up _either_ a RING or a NECKLACE slot
     */

    List<List<Equipment.Slot>> possibleSlots;

    public Equippable(List<List<Equipment.Slot>> possibleSlots) {
        this.possibleSlots = possibleSlots;
    }
}
