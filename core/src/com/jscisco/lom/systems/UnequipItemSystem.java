package com.jscisco.lom.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.jscisco.lom.components.EquipmentComponent;
import com.jscisco.lom.components.InventoryComponent;
import com.jscisco.lom.components.UnequippingItemComponent;
import com.jscisco.lom.components.model.EquipmentSlot;

public class UnequipItemSystem extends IteratingSystem {

    private ComponentMapper<EquipmentComponent> mEquipment;
    private ComponentMapper<UnequippingItemComponent> mUnequipping;
    private ComponentMapper<InventoryComponent> mInventory;

    public UnequipItemSystem() {
        super(Aspect.all(UnequippingItemComponent.class));
    }

    @Override
    protected void process(int entityId) {
        int unequipper = mUnequipping.get(entityId).unequipper;
        EquipmentComponent equipmentComponent = mEquipment.get(unequipper);
        for (EquipmentSlot slot : equipmentComponent.equipment) {
            if (slot.getItem() == entityId) {
                // Only unequip an item if it was equipped
                slot.setItem(Integer.MIN_VALUE);
                mInventory.get(unequipper).inventory.add(entityId);
            }
        }
        mUnequipping.remove(entityId);
    }
}
