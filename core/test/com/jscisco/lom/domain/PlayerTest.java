package com.jscisco.lom.domain;

import com.jscisco.lom.domain.EntityName;
import com.jscisco.lom.domain.GameObject;
import com.jscisco.lom.domain.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Nested
    class Inventory {

        Entity player;

        @BeforeEach
        void setUp() {
            this.player = Entity.player(EntityName.of("Test Player"), new Health(100));
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
