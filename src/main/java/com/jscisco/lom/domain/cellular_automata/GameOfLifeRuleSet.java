package com.jscisco.lom.domain.cellular_automata;

import java.util.Arrays;

public class GameOfLifeRuleSet extends RuleSet {

    @Override
    public Cell apply(Cell cell, Cell[] neighborhood) {
        Cell updated = cell;
        long livingNeighbors = Arrays.stream(neighborhood).filter(Cell::isAlive).count();
        if (cell.isAlive() && (livingNeighbors == 2 || livingNeighbors == 3)) {
            // Do nothing
        } else if (!cell.isAlive() && livingNeighbors == 3) {
            // It now lives
            updated.reproduce();
        } else{
            // It now dies
            updated.die();
        }
        return updated;
    }
}
