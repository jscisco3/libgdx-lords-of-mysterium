package com.jscisco.lom.domain.cellular_automata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameOfLifeRuleSetTest {

    private GameOfLifeRuleSet ruleSet = new GameOfLifeRuleSet();
    private Cell[] neighbors;

    @BeforeEach
    public void setup() {
        neighbors = new Cell[8];
        for (int i = 0; i < 8; i++) {
            neighbors[i] = new Cell(Cell.State.DEAD);
        }
    }

    @Test
    public void givenALiveCellWith2LivingNeighbors_itsStateDoesNotChange() {
        Cell cell = new Cell(Cell.State.ALIVE);
        neighbors[0] = new Cell(Cell.State.ALIVE);
        neighbors[1] = new Cell(Cell.State.ALIVE);

        Cell output = ruleSet.apply(cell, neighbors);

        assertThat(output.isAlive()).isTrue();
    }

    @Test
    public void givenALiveCellWith3LivingNeighbors_itsStateDoesNotChange() {
        Cell cell = new Cell(Cell.State.ALIVE);
        neighbors[0] = new Cell(Cell.State.ALIVE);
        neighbors[1] = new Cell(Cell.State.ALIVE);
        neighbors[2] = new Cell(Cell.State.ALIVE);

        Cell output = ruleSet.apply(cell, neighbors);

        assertThat(output.isAlive()).isTrue();
    }

    @Test
    public void givenALiveCellWith1LivingNeighbors_itDies() {
        Cell cell = new Cell(Cell.State.ALIVE);
        neighbors[0] = new Cell(Cell.State.ALIVE);

        Cell output = ruleSet.apply(cell, neighbors);

        assertThat(output.isAlive()).isFalse();
    }

    @Test
    public void givenALiveCellWith4LivingNeighbors_itDies() {
        Cell cell = new Cell(Cell.State.ALIVE);

        neighbors[0] = new Cell(Cell.State.ALIVE);
        neighbors[1] = new Cell(Cell.State.ALIVE);
        neighbors[2] = new Cell(Cell.State.ALIVE);
        neighbors[3] = new Cell(Cell.State.ALIVE);

        Cell output = ruleSet.apply(cell, neighbors);

        assertThat(output.isAlive()).isFalse();
    }

    @Test
    public void givenDeadCellWithNoLivingNeighbors_itStaysDead() {
        Cell cell = new Cell(Cell.State.DEAD);
        Cell output = ruleSet.apply(cell, neighbors);

        assertThat(output.isAlive()).isFalse();
    }

    @Test
    public void givenDeadCellWith3LivingNeighbors_itLives() {
        Cell cell = new Cell(Cell.State.DEAD);
        neighbors[0] = new Cell(Cell.State.ALIVE);
        neighbors[1] = new Cell(Cell.State.ALIVE);
        neighbors[2] = new Cell(Cell.State.ALIVE);
        Cell output = ruleSet.apply(cell, neighbors);

        assertThat(output.isAlive()).isTrue();
    }

    @Test
    public void givenDeadCellWithMoreThan3LivingNeighbors_staysDead() {
        Cell cell = new Cell(Cell.State.DEAD);
        neighbors[0] = new Cell(Cell.State.ALIVE);
        neighbors[1] = new Cell(Cell.State.ALIVE);
        neighbors[2] = new Cell(Cell.State.ALIVE);
        neighbors[3] = new Cell(Cell.State.ALIVE);
        neighbors[4] = new Cell(Cell.State.ALIVE);
        Cell output = ruleSet.apply(cell, neighbors);

        assertThat(output.isAlive()).isFalse();
    }

}
