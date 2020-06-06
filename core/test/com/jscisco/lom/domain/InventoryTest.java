package com.jscisco.lom.domain;

import com.jscisco.lom.domain.GameObject;
import com.jscisco.lom.domain.Inventory;
import com.jscisco.lom.domain.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InventoryTest {

    @Nested
    class RemoveItem {

        Inventory inventory;

        @BeforeEach
        public void setUp() {
            inventory = new Inventory();
            inventory.addItem(ItemRepository.createSword());
        }

        @Test
        public void removeItemReturnsItemIfItIsInInventory() {
            GameObject item = inventory.removeItem(0);
            assertNotNull(item);
        }

        @Test
        public void removeItemThrowsExceptionIfIndexOutOfBounds() {
            Throwable throwable = catchThrowable(() -> {
                inventory.removeItem(100);
            });
            assertThat(throwable).isInstanceOf(IndexOutOfBoundsException.class);
        }
    }

    @Nested
    class AddItem {

        Inventory inventory;

        @BeforeEach
        public void setUp() {
            this.inventory = new Inventory();
        }

        @Test
        public void addItemAddsTheItem() {
            this.inventory.addItem(ItemRepository.createSword());
            assertThat(this.inventory.items()).isNotEmpty();
        }
    }
}
