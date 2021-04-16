package com.jscisco.lom.domain.cellular_automata;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CellularAutomataTest {

    @Test
    public void whenICreateAnAutomata_heightMustBeGreaterThan3() {
        assertThatThrownBy(() -> {
            new CellularAutomata(10, 1, new GameOfLifeRuleSet());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenICreateAnAutomata_widthMustBeGreaterThan3() {
        assertThatThrownBy(() -> {
            new CellularAutomata(1, 10, new GameOfLifeRuleSet());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void givenAnAutomata_whenIGetANeighborhood_IMustHaveA3x3Region() {
        CellularAutomata automata = new CellularAutomata(5, 5, new GameOfLifeRuleSet());
        assertThatThrownBy(() -> {
            automata.getNeighbors(0, 1);
        }).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> {
            automata.getNeighbors(1, 0);
        }).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> {
            automata.getNeighbors(4, 1);
        }).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> {
            automata.getNeighbors(1, 4);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void givenAnAutomata_whenIGetANeighborhood_IGet8Cells() {
        CellularAutomata automata = new CellularAutomata(3, 3, new GameOfLifeRuleSet());
        Cell[] neighborhood = automata.getNeighbors(1, 1);
        assertThat(neighborhood.length).isEqualTo(8);
    }

}
