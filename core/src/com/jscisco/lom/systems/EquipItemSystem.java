package com.jscisco.lom.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.jscisco.lom.components.*;
import com.jscisco.lom.components.model.EquipmentSlot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EquipItemSystem extends IteratingSystem {

    private Logger logger = LoggerFactory.getLogger(EquipItemSystem.class);

    private ComponentMapper<EquippingItemComponent> mEquipping;
    private ComponentMapper<UnequippingItemComponent> mUnequipping;
    private ComponentMapper<EquipmentComponent> mEquipment;
    private ComponentMapper<EquippableComponent> mEquippable;
    private ComponentMapper<InventoryComponent> mInventory;

    public EquipItemSystem() {
        super(Aspect.all(EquippableComponent.class, EquippingItemComponent.class));
    }

    @Override
    protected void process(int entityId) {
        int equipper = mEquipping.get(entityId).equipper;
        EquipmentComponent equipmentComponent = mEquipment.get(equipper);

        // Add it to the equipper's equipment
        List<EquipmentSlot> validSlots = equipmentComponent.getSlotsByType(mEquippable.get(entityId).type);
        // If there are no valid slots, do nothing
        if (validSlots.isEmpty()) {
            mEquipping.remove(entityId);
            return;
        }
        EquipmentSlot slot = validSlots.get(0);
        // If this is in the equipper's inventory, remove it.
        if (mInventory.has(equipper)) {
            mInventory.get(equipper).inventory.remove((Integer) entityId);
        }
        // If there is something in the slot, unequip it
        if (slot.getItem() != Integer.MIN_VALUE) {
            UnequippingItemComponent unequippingItemComponent = mUnequipping.create(slot.getItem());
            unequippingItemComponent.unequipper = equipper;
        }
        slot.setItem(entityId);
        mEquipping.remove(entityId);
    }
}
