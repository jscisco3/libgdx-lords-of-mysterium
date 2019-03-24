package com.jscisco.lom.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.jscisco.lom.components.InventoryComponent;
import com.jscisco.lom.components.flags.PickingUpItemComponent;

public class PickItemUpSystem extends IteratingSystem {

    private ComponentMapper<InventoryComponent> mInventory;
    private ComponentMapper<PickingUpItemComponent> mPicker;

    public PickItemUpSystem() {
        super(Aspect.all(PickingUpItemComponent.class));
    }

    @Override
    protected void process(int entityId) {
        int picker = mPicker.get(entityId).picker;
        InventoryComponent inventoryComponent = mInventory.get(picker);
        inventoryComponent.inventory.add(entityId);
        mPicker.remove(entityId);
    }
}
