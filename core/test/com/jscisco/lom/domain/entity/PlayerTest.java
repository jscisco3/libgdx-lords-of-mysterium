package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Nested
    class Inventory {

        Player player;

        @BeforeEach
        void setUp() {
            this.player = new Player(new EntityName("Test Player"));
        }

        @Test
        public void testPickingUpAnItemPutsItInThePlayersInventory() {
            Item item = new Item();
            this.player.pickUpItem(item);
            assertThat(this.player.inventory.items()).isNotEmpty();
            assertThat(this.player.inventory.items().get(0).getPosition()).isEqualTo(this.player.position);
        }

        @Test
        public void testDroppingAnItemRemovesItFromTheInventory() {
            Item item = new Item();
            this.player.pickUpItem(item);
            this.player.dropItem(0);
            assertThat(this.player.inventory.items()).isEmpty();
        }

    }

}
