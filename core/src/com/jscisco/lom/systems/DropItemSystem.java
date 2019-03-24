package com.jscisco.lom.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.jscisco.lom.components.InventoryComponent;
import com.jscisco.lom.components.DroppingItemComponent;

public class DropItemSystem extends IteratingSystem {

    private ComponentMapper<InventoryComponent> mInventory;
    private ComponentMapper<DroppingItemComponent> mDropping;

    public DropItemSystem() {
        super(Aspect.all(DroppingItemComponent.class));
    }

    @Override
    protected void process(int entityId) {
        int dropper = mDropping.get(entityId).holder;
        InventoryComponent inventoryComponent = mInventory.get(dropper);
        inventoryComponent.inventory.remove((Integer) entityId);
        mDropping.remove(entityId);
    }
}
