package com.jscisco.lom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidmath.RNG;

import java.util.ArrayList;
import java.util.List;

public class RandomTable {

    private final Logger logger = LoggerFactory.getLogger(RandomTable.class);
    List<RandomEntry> entries = new ArrayList<>();
    int totalWeight = 0;

    public RandomTable() {
    }

    public RandomTable(List<RandomEntry> entries) {
        for (RandomEntry entry : entries) {
            this.add(entry);
        }
    }

    public RandomTable add(RandomEntry entry) {
        this.entries.add(entry);
        this.totalWeight += entry.weight;
        return this;
    }

    public String roll(RNG rng) {
        if (this.totalWeight == 0) {
            logger.warn("Rolling on a random table with 0 total weight");
            return null;
        }
        int roll = rng.between(0, this.totalWeight);
        int index = 0;
        while (roll >= 0) {
            if (roll < this.entries.get(index).weight) {
                return this.entries.get(index).name;
            }
            roll -= this.entries.get(index).weight;
            index += 1;
        }
        logger.warn("Tried to roll on a table, but unable to find an entry. This is really unexpected and bad!");
        return null;
    }

}
