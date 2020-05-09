package com.jscisco.lom.shelf.entities;

import com.jscisco.lom.shelf.combat.Attack;
import com.jscisco.lom.shelf.combat.Damage;
import com.jscisco.lom.shelf.combat.DamageType;
import com.jscisco.lom.shelf.effect.TimedEffect;
import com.jscisco.lom.shelf.entity.Entity;
import com.jscisco.lom.shelf.entity.Equipment;
import com.jscisco.lom.shelf.entity.Inventory;
import com.jscisco.lom.shelf.items.Item;
import com.jscisco.lom.shelf.items.ItemCannotBeEquippedException;
import com.jscisco.lom.shelf.items.ItemName;
import com.jscisco.lom.shelf.items.Slot;
import com.jscisco.lom.shelf.zone.Stage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
    @Disabled
    void getAttackReturnsAListOfAttacksIfSomethingIsEquipped() throws ItemCannotBeEquippedException {
        Equipment equipment = new Equipment();
        entity.setEquipment(equipment);

        Attack expectedAttack = new Attack(
                100, new Damage(DamageType.PHYSICAL, 20, 25)
        );

        Item item = new Item.Builder()
                .withAttack(expectedAttack)
                .withEquipmentSlot(Slot.HAND)
                .build();

        entity.equip(item);

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

    /**
     * Given an item
     * With multiple available slots
     * It should be equipped
     */
    @Test
    void equipAnItemWithMultipleOptions() throws ItemCannotBeEquippedException {
        this.entity.setEquipment(new Equipment());
        Item item = new Item.Builder()
                .withEquipmentSlot(Slot.HAND)
                .build();
        this.entity.equip(item);

        Assertions.assertThat(1).isEqualTo(this.entity.getEquipment().getNumberOfEquippedItems());
    }

    @Test
    void applyingAnEffectToAnEntityShouldAttachIt() {
        TimedEffect effect = new TimedEffect(5) {
        };
        this.entity.applyEffect(effect);

        Assertions.assertThat(effect).isEqualTo(this.entity.getEffects().get(0));
    }

    @Test
    void whenTimedEffectTimerHits0ItIsRemovedFromAttachedEntity() {
        TimedEffect effect = Mockito.spy(new TimedEffect(2) {
        });

        this.entity.applyEffect(effect);
        effect.tick();
        Assertions.assertThat(effect.timeRemaining()).isEqualTo(1);
        effect.tick();
        Mockito.verify(effect).destroy();
        Assertions.assertThat(this.entity.getEffects().isEmpty()).isTrue();
    }
}
