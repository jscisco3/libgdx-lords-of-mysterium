package com.jscisco.lom.entity;

import com.jscisco.lom.attributes.*;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Stage;

public class Player extends Entity {

    public static class Builder {
        private String name;

        private Stage stage;
        private FieldOfView fieldOfView;
        private Position position;
        private Health health;
        private Energy energy;
        private Inventory inventory;
        private Equipment equipment;
        private String textureMapLookup;
        private Stats stats;
        private Job job;

        public Builder(String name) {
            this.name = name;
        }

        public Builder withStage(Stage stage) {
            this.stage = stage;
            return this;
        }

        public Builder withFieldOfView(FieldOfView fov) {
            this.fieldOfView = fov;
            return this;
        }

        public Builder withPosition(Position position) {
            this.position = position;
            return this;
        }

        public Builder withHealth(Health health) {
            this.health = health;
            return this;
        }

        public Builder withEnergy(Energy energy) {
            this.energy = energy;
            return this;
        }

        public Builder withInventory(Inventory inventory) {
            this.inventory = inventory;
            return this;
        }

        public Builder withEquipment(Equipment equipment) {
            this.equipment = equipment;
            return this;
        }

        public Builder withTexture(String textureMapLookup) {
            this.textureMapLookup = textureMapLookup;
            return this;
        }

        public Builder withStats(Stats stats) {
            this.stats = stats;
            return this;
        }

        public Builder withJob(Job job) {
            this.job = job;
            return this;
        }

        public Player build() {
            Player player = new Player();
            player.name = name;
            player.stage = stage;
            player.fieldOfView = fieldOfView;
            player.position = position;
            player.health = health;
            player.energy = energy;
            player.inventory = inventory;
            player.equipment = equipment;
            player.textureMapLookup = textureMapLookup;
            player.job = job;
            player.stats = stats;
            return player;
        }
    }

    private Player() {
    }
}
