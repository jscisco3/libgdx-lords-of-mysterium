package com.jscisco.lom.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Nested
    class Inventory {

        GameObject player;

        @BeforeEach
        void setUp() {
            this.player = new GameObject(new EntityName("Test Player"));
        }

        @Test
        public void testPickingUpAnItemPutsItInThePlayersInventory() {
            GameObject item = ItemRepository.createSword();
            this.player.pickUpItem(item);
            assertThat(this.player.inventory.items()).isNotEmpty();
            assertThat(this.player.inventory.items().get(0).position).isEqualTo(this.player.position);
        }

        @Test
        public void testDroppingAnItemRemovesItFromTheInventory() {
            GameObject item = ItemRepository.createSword();
            this.player.pickUpItem(item);
            this.player.dropItem(0);
            assertThat(this.player.inventory.items()).isEmpty();
        }

    }

}
