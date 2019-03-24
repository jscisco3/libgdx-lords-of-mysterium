package com.jscisco.lom.systems;

import com.artemis.ComponentMapper;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.jscisco.lom.components.InventoryComponent;
import com.jscisco.lom.components.flags.DroppingItemComponent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDropItemSystem {

    private ComponentMapper<InventoryComponent> mInventory;
    private ComponentMapper<DroppingItemComponent> mDropping;

    World world;

    @BeforeEach
    public void init() {
        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(new DropItemSystem())
                .build();
        world = new World(config);
        world.inject(this);
    }

    @Test
    public void dropping_an_item_removes_it_from_inventory() {
        int holder = world.create();
        mInventory.create(holder);

        int item = world.create();
        mInventory.get(holder).inventory.add(item);

        Assertions.assertThat(mInventory.get(holder).inventory.size()).isEqualTo(1);

        mDropping.create(item);
        mDropping.get(item).holder = holder;

        Assertions.assertThat(mDropping.get(item).holder).isEqualTo(holder);

        world.process();

        Assertions.assertThat(mDropping.has(item)).isFalse();
        Assertions.assertThat(mInventory.get(holder).inventory.isEmpty()).isTrue();

    }

}
