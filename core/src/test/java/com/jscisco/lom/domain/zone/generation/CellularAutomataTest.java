package com.jscisco.lom.domain.zone.generation;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CellularAutomataTest {

    @Test
    public void givenAnAutomata_whenIGetACellsNeighbors_thenIGetWhatIExpect() {
        int width = 5;
        int height = 5;

        Cell[][] seed = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                seed[i][j] = new Cell(Cell.State.DEAD);
            }
        }
        seed[2][2] = new Cell(Cell.State.ALIVE);

        CellularAutomata automata = new CellularAutomata(width, height);
        automata.setCurrentGeneration(seed);

        List<Cell> neighbors = automata.getNeighborhood(1, 1);
        assertThat(neighbors.size()).isEqualTo(8);
        assertThat(neighbors.stream().filter(Cell::alive).count()).isEqualTo(1);

        neighbors = automata.getNeighborhood(2, 2);
        assertThat(neighbors.size()).isEqualTo(8);
        assertThat(neighbors.stream().filter(Cell::alive).count()).isEqualTo(0);
    }

    @Test
    public void givenAWellUnderstoodInitializedSetOfCells_whenItTicksOnce_thenIGetTheResultsIExpect() {
        int width = 5;
        int height = 5;

        Cell[][] seed = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                seed[i][j] = new Cell(Cell.State.DEAD);
            }
        }

        // TUB
        seed[2][1] = new Cell(Cell.State.ALIVE);
        seed[1][2] = new Cell(Cell.State.ALIVE);
        seed[3][2] = new Cell(Cell.State.ALIVE);
        seed[2][3] = new Cell(Cell.State.ALIVE);

        CellularAutomata automata = new CellularAutomata(width, height);
        automata.setCurrentGeneration(seed);

        automata.tick();

        Cell[][] newGeneration = automata.getCurrentGeneration();

        for (int j = 0; j < height; j++) {
            String s = "";
            for (int i = 0; i < width; i++) {
                s += seed[i][j] + " | ";
            }
            System.out.println(s);
        }

        System.out.println("=======");

        for (int j = 0; j < height; j++) {
            String s = "";
            for (int i = 0; i < width; i++) {
                s += newGeneration[i][j] + " | ";
            }
            System.out.println(s);
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                assertThat(newGeneration[i][j].getState()).isEqualTo(seed[i][j].getState());
            }
        }
    }
}
