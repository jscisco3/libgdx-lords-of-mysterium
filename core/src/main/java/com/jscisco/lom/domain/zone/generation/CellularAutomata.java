package com.jscisco.lom.domain.zone.generation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CellularAutomata {

    private static final Logger logger = LoggerFactory.getLogger(CellularAutomata.class);

    private final int width;
    private final int height;
    Cell[][] currentGeneration;
    Cell[][] nextGeneration;

    public CellularAutomata(int width, int height) {
        this.width = width;
        this.height = height;
        currentGeneration = new Cell[width][height];
        nextGeneration = new Cell[width][height];
        initialize();
        for (int i = 0; i < this.width; i++) {
            currentGeneration[i][0] = new Cell(Cell.State.DEFINITIVELY_DEAD);
            currentGeneration[i][height - 1] = new Cell(Cell.State.DEFINITIVELY_DEAD);
        }
        for (int i = 0; i < this.height; i++) {
            currentGeneration[0][i] = new Cell(Cell.State.DEFINITIVELY_DEAD);
            currentGeneration[this.width - 1][i] = new Cell(Cell.State.DEFINITIVELY_DEAD);
        }
    }

    public void tick() {
        // Game of life rules
        for (int i = 1; i < this.width - 1; i++) {
            for (int j = 1; j < this.height - 1; j++) {
                Cell currentCell = currentGeneration[i][j];
                List<Cell> neighbors = getNeighborhood(i, j);
                long livingNeighbors = neighbors.stream().filter(Cell::alive).count();
                if (currentCell.alive() && (livingNeighbors == 2 || livingNeighbors == 3)) {
                    nextGeneration[i][j] = new Cell(Cell.State.ALIVE);
                } else if (!currentCell.alive() && livingNeighbors == 3) {
                    nextGeneration[i][j] = new Cell(Cell.State.ALIVE);
                } else {
                    nextGeneration[i][j] = new Cell(Cell.State.DEAD);
                }
            }
        }
        setCurrentGeneration(nextGeneration);
    }

    public List<Cell> getNeighborhood(int x, int y) {
        List<Cell> neighbors = new ArrayList<>();
        neighbors.add(currentGeneration[x - 1][y - 1]);
        neighbors.add(currentGeneration[x][y - 1]);
        neighbors.add(currentGeneration[x + 1][y - 1]);

        neighbors.add(currentGeneration[x - 1][y + 1]);
        neighbors.add(currentGeneration[x][y + 1]);
        neighbors.add(currentGeneration[x + 1][y + 1]);

        neighbors.add(currentGeneration[x - 1][y]);
        neighbors.add(currentGeneration[x + 1][y]);

//        for (int i = -1; i < 2; i++) {
//            for (int j = -1; j < 2; j++) {
//                if (i != 0 && j != 0) {
//                    neighbors.add(currentGeneration[x + i][y + j]);
//                }
//            }
//        }
        return neighbors;
    }

    public void setCurrentGeneration(Cell[][] currentGeneration) {
        this.currentGeneration = currentGeneration;
    }

    public Cell[][] getCurrentGeneration() {
        return currentGeneration;
    }

    private void initialize() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                currentGeneration[i][j] = new Cell(Cell.State.DEAD);
                nextGeneration[i][j] = new Cell(Cell.State.DEAD);
            }
        }
    }

}
