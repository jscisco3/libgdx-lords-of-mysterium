package com.jscisco.lom.systems;

import com.artemis.ComponentMapper;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.jscisco.lom.components.*;
import com.jscisco.lom.components.model.EquipmentSlot;
import com.jscisco.lom.components.model.EquipmentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEquippingItemSystem {

    private ComponentMapper<EquipmentComponent> mEquipment;
    private ComponentMapper<EquippableComponent> mEquippable;
    private ComponentMapper<EquippingItemComponent> mEquipping;
    private ComponentMapper<InventoryComponent> mInventory;
    private ComponentMapper<UnequippingItemComponent> mUnequipping;

    World world;

    @BeforeEach
    public void init() {
        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(new EquipItemSystem())
                .build();
        world = new World(config);
        world.inject(this);
    }

    @Test
    public void equipping_an_item_to_an_empty_slot_should_put_it_there() {
        EquipmentSlot slot = new EquipmentSlot(EquipmentType.HAND);
        int equipment = world.create();
        EquipmentComponent equipmentComponent = mEquipment.create(equipment);
        equipmentComponent.equipment.add(slot);

        int equippable = world.create();
        EquippableComponent equippableComponent = mEquippable.create(equippable);
        equippableComponent.type = EquipmentType.HAND;

        EquippingItemComponent equippingItemComponent = mEquipping.create(equippable);
        equippingItemComponent.equipper = equipment;

        world.process();
        Assertions.assertThat(slot.getItem()).isEqualTo(equippable);
        Assertions.assertThat(mEquipping.has(equippable)).isFalse();
    }

    @Test
    public void equipping_an_item_but_no_valid_slots_does_nothing() {
        int equipment = world.create();
        mEquipment.create(equipment);

        int equippable = world.create();
        EquippableComponent equippableComponent = mEquippable.create(equippable);
        equippableComponent.type = EquipmentType.TWO_HAND;

        EquippingItemComponent equippingItemComponent = mEquipping.create(equippable);
        equippingItemComponent.equipper = equipment;

        world.process();

        Assertions.assertThat(mEquipping.has(equippable)).isFalse();

    }

    @Test
    public void eqiupping_an_item_to_occupied_slot_should_give_occupying_item_unequipping_component() {
        EquipmentSlot slot = new EquipmentSlot(EquipmentType.HAND);
        int equipment = world.create();
        EquipmentComponent equipmentComponent = mEquipment.create(equipment);
        equipmentComponent.equipment.add(slot);

        int equippable = world.create();
        EquippableComponent equippableComponent = mEquippable.create(equippable);
        equippableComponent.type = EquipmentType.HAND;

        EquippingItemComponent equippingItemComponent = mEquipping.create(equippable);
        equippingItemComponent.equipper = equipment;


        int equippable2 = world.create();
        equippableComponent = mEquippable.create(equippable2);
        equippableComponent.type = EquipmentType.HAND;
        slot.setItem(equippable2);

        world.process();
        Assertions.assertThat(mUnequipping.has(equippable2)).isTrue();
        Assertions.assertThat(slot.getItem()).isEqualTo(equippable);
    }

    @Test
    public void equipping_an_item_should_remove_it_from_inventory() {
        EquipmentSlot slot = new EquipmentSlot(EquipmentType.HAND);
        int equipment = world.create();
        EquipmentComponent equipmentComponent = mEquipment.create(equipment);
        equipmentComponent.equipment.add(slot);
        InventoryComponent inventoryComponent = mInventory.create(equipment);

        int equippable = world.create();
        EquippableComponent equippableComponent = mEquippable.create(equippable);
        equippableComponent.type = EquipmentType.HAND;
        inventoryComponent.inventory.add(equippable);

        EquippingItemComponent equippingItemComponent = mEquipping.create(equippable);
        equippingItemComponent.equipper = equipment;

        world.process();

        Assertions.assertThat(inventoryComponent.inventory.isEmpty()).isTrue();
    }

}
