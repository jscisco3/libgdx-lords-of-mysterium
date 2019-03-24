package com.jscisco.lom.systems;

import com.artemis.ComponentMapper;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.jscisco.lom.components.EquipmentComponent;
import com.jscisco.lom.components.EquippableComponent;
import com.jscisco.lom.components.InventoryComponent;
import com.jscisco.lom.components.UnequippingItemComponent;
import com.jscisco.lom.components.model.EquipmentSlot;
import com.jscisco.lom.components.model.EquipmentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestUnequipItemSystem {

    private ComponentMapper<EquipmentComponent> mEquipment;
    private ComponentMapper<EquippableComponent> mEquippable;
    private ComponentMapper<UnequippingItemComponent> mUnequipping;
    private ComponentMapper<InventoryComponent> mInventory;

    World world;

    @BeforeEach
    public void init() {
        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(new UnequipItemSystem())
                .build();
        world = new World(config);
        world.inject(this);
    }

    @Test
    public void unequipping_an_equipped_item_puts_it_in_inventory() {
        int e = world.create();
        EquipmentComponent equipmentComponent = mEquipment.create(e);
        InventoryComponent inventoryComponent = mInventory.create(e);

        int item = world.create();
        EquippableComponent equippableComponent = mEquippable.create(item);
        equippableComponent.type = EquipmentType.HAND;

        EquipmentSlot slot = new EquipmentSlot(item, equippableComponent.type);

        equipmentComponent.equipment.add(slot);

        mUnequipping.create(item);
        world.process();

        Assertions.assertThat(equipmentComponent.equipment.size()).isEqualTo(1);
        Assertions.assertThat(slot.getItem()).isEqualTo(Integer.MIN_VALUE);
        Assertions.assertThat(inventoryComponent.inventory.size()).isEqualTo(1);
        Assertions.assertThat(inventoryComponent.inventory.get(0)).isEqualTo(item);
        Assertions.assertThat(mUnequipping.has(item)).isFalse();
    }
}
