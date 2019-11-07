package com.jscisco.lom.entities;

import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.entity.Inventory;
import com.jscisco.lom.items.Item;
import com.jscisco.lom.items.ItemName;
import com.jscisco.lom.zone.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEntity {

    private Entity entity = new Entity() {
    };

    @BeforeEach
    public void setUp() {
        entity.setInventory(new Inventory());
        entity.setStage(Mockito.mock(Stage.class));
    }

    @Test
    void entityCanDropItemIfInInventory() {
        Item item = new Item()
                .withName(new ItemName("Test Item"));
        entity.getInventory().addItem(item);
        assertTrue(entity.drop(item));
    }

    @Test
    void entityCannotDropItemsNotInInventory() {
        Item item = new Item()
                .withName(new ItemName("Test Item"));
        assertFalse(entity.drop(item));
    }
}
