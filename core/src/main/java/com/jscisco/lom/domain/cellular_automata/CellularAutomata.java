package com.jscisco.lom.domain.cellular_automata;

public class CellularAutomata {

    private final RuleSet rules;
    private Cell[][] cells;

    public CellularAutomata(int width, int height, RuleSet rules) {
        this.rules = rules;
        if (width < 3 || height < 3) {
            throw new IllegalArgumentException("Minimum width and height are 3");
        }
        cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public CellularAutomata(Cell[][] seed, RuleSet rules) {
        this.rules = rules;
        if (seed.length < 3 || seed[0].length < 3) {
            throw new IllegalArgumentException("Minimum dimensions are 3x3");
        }
        this.cells = seed;
    }

    public Cell[] getNeighbors(int x, int y) {
        // 5, 5
        // x >= 1 <= 3
        if (x < 1 || y < 1 || x > cells.length - 2 || y > cells[0].length - 2) {
            throw new IllegalArgumentException("X and Y must be within bounds - 1");
        }
        Cell[] neighborhood = new Cell[8];
        neighborhood[0] = cells[x - 1][y - 1];
        neighborhood[1] = cells[x][y - 1];
        neighborhood[2] = cells[x + 1][y - 1];
        neighborhood[3] = cells[x - 1][y];
        neighborhood[4] = cells[x + 1][y];
        neighborhood[5] = cells[x - 1][y + 1];
        neighborhood[6] = cells[x][y + 1];
        neighborhood[7] = cells[x + 1][y + 1];
        return neighborhood;
    }

    public void tick() {
        Cell[][] nextGeneration = cells;
        for (int i = 1; i < nextGeneration.length - 1; i++) {
            for (int j = 1; j < nextGeneration[i].length - 1; j++) {
                nextGeneration[i][j] = rules.apply(cells[i][j], getNeighbors(i, j));
            }
        }
        cells = nextGeneration;
    }

    public Cell[][] getCells() {
        return cells;
    }
}
