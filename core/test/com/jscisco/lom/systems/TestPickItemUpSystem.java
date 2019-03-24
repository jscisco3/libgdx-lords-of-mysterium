package com.jscisco.lom.systems;

import com.artemis.*;
import com.jscisco.lom.components.InventoryComponent;
import com.jscisco.lom.components.PickingUpItemComponent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPickItemUpSystem {

    private ComponentMapper<InventoryComponent> mInventory;
    private ComponentMapper<PickingUpItemComponent> mPicker;

    World world;

    @BeforeEach
    public void init() {
        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(new PickItemUpSystem())
                .build();
        world = new World(config);
        world.inject(this);
    }

    @Test
    public void picking_item_up_should_add_it_to_inventory() {
        int picker = world.create(new ArchetypeBuilder()
                .add(InventoryComponent.class)
                .build(world));

        int item = world.create();
        PickingUpItemComponent pick = mPicker.create(item);
        pick.picker = picker;

        world.process();

        InventoryComponent inventoryComponent = mInventory.get(picker);
        Assertions.assertThat(inventoryComponent.inventory.size()).isEqualTo(1);
    }

}
