package com.jscisco.lom.domain.cellular_automata;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CellTest {

    // Alive tests
    @Test
    public void givenACellThatIsDefinitivelyAlive_whenIQueryIfItIsAlive_thenIGetTrue() {
        Cell cell = new Cell(Cell.State.DEFINITIVELY_ALIVE);
        assertThat(cell.isAlive()).isTrue();
    }

    @Test
    public void givenACellThatIsAlive_whenIQueryIfItIsAlive_thenIGetTrue() {
        Cell cell = new Cell(Cell.State.ALIVE);
        assertThat(cell.isAlive()).isTrue();
    }

    @Test
    public void givenACellThatIsDead_whenIQueryIfItIsAlive_thenIGetFalse() {
        Cell cell = new Cell(Cell.State.DEAD);
        assertThat(cell.isAlive()).isFalse();
    }

    @Test
    public void givenACellThatIsDefinitivelyDead_whenIQueryIfItIsAlive_thenIGetFalse() {
        Cell cell = new Cell(Cell.State.DEFINITIVELY_DEAD);
        assertThat(cell.isAlive()).isFalse();
    }

    // Toggle tests
    @Test
    public void givenACellThatIsDefinitivelyAlive_whenIToggleIt_thenThereAreNoChanges() {
        Cell cell = new Cell(Cell.State.DEFINITIVELY_ALIVE);
        cell.die();

        assertThat(cell.isAlive()).isTrue();
    }

    @Test
    public void givenACellThatIsAlive_whenIToggleIt_thenItIsNowDead() {
        Cell cell = new Cell(Cell.State.ALIVE);
        cell.die();

        assertThat(cell.isAlive()).isFalse();
    }

    @Test
    public void givenACellThatIsDead_whenIToggleIt_thenItIsNowAlive() {
        Cell cell = new Cell(Cell.State.DEAD);
        cell.reproduce();

        assertThat(cell.isAlive()).isTrue();
    }

    @Test
    public void givenACellThatIsDefinitivelyDead_whenIToggleIt_thenThereAreNoChanges() {
        Cell cell = new Cell(Cell.State.DEFINITIVELY_DEAD);
        cell.reproduce();

        assertThat(cell.isAlive()).isFalse();
    }

}
