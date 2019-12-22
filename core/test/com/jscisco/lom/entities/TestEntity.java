package com.jscisco.lom.entities;

import com.jscisco.lom.combat.Attack;
import com.jscisco.lom.combat.Damage;
import com.jscisco.lom.combat.DamageType;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.entity.Equipment;
import com.jscisco.lom.entity.Inventory;
import com.jscisco.lom.items.Item;
import com.jscisco.lom.items.ItemCannotBeEquippedException;
import com.jscisco.lom.items.ItemName;
import com.jscisco.lom.items.Slot;
import com.jscisco.lom.zone.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class TestEntity {

    private Entity entity = new Entity() {
    };

    @BeforeEach
    public void setUp() {
        entity.setInventory(new Inventory());
        entity.setStage(mock(Stage.class));
    }

    @Test
    void entityCanDropItemIfInInventory() {
        Item item = new Item.Builder()
                .withName(new ItemName("Test Item"))
                .build();
        entity.getInventory().addItem(item);
        assertTrue(entity.drop(item));
    }

    @Test
    void entityCannotDropItemsNotInInventory() {
        Item item = new Item.Builder()
                .withName(new ItemName("Test Item"))
                .build();
        assertFalse(entity.drop(item));
    }

    @Test
    void getAttackReturnsAListOfAttacksIfNothingEquipped() {
        List<Attack> attacks = entity.getAttacks();
        assertFalse(attacks.isEmpty());
    }

    @Test
    @Disabled
    void getAttackReturnsAListOfAttacksIfSomethingIsEquipped() throws ItemCannotBeEquippedException {
        Equipment equipment = new Equipment();
        entity.setEquipment(equipment);

        Attack expectedAttack = new Attack(
                100, new Damage(DamageType.PHYSICAL, 1, 20)
        );

        Item item = new Item.Builder()
                .withAttack(expectedAttack)
                .withEquipmentSlot(Slot.HAND)
                .build();

        equipment.equip(item);

        List<Attack> attacks = entity.getAttacks();
        assertFalse(attacks.isEmpty());
        assertEquals(expectedAttack, attacks.get(0));
    }

    @Test
    void entityCannotEquipIfEquipmentIsNull() {
        entity.setEquipment(null);
        assertThrows(IllegalStateException.class, () -> {
            entity.equip(mock(Item.class));
        });
    }

    @Test
    void entityCannotEquipItemIfItemIsUnequippable() {
        entity.setEquipment(new Equipment());
        assertThrows(ItemCannotBeEquippedException.class, () -> {
            entity.equip(new Item.Builder().build());
        });
    }
}
