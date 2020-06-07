package com.jscisco.lom.domain;

import java.util.List;

public class Cell {
    Terrain terrain;
    Entity entity;
    List<Item> items;

    private Cell() {
    }

    public static Cell emptyFloor() {
        Cell cell = new Cell();
        cell.terrain = Terrain.floor();
        return cell;
    }

    public static Cell withTerrain(Terrain terrain) {
        Cell cell = new Cell();
        cell.terrain = terrain;
        return cell;
    }

    public boolean isOccupied() {
        return entity != null;
    }
}
