package com.jscisco.lom.domain.zone;

public class LevelGeneratorStrategyFactory {

    public static LevelGeneratorStrategy EMPTY = new LevelGeneratorStrategy.EmptyLevelStrategy();
    public static LevelGeneratorStrategy GENERIC = new LevelGeneratorStrategy.GenericStrategy();
    public static LevelGeneratorStrategy RANDOM_ROOMS = new LevelGeneratorStrategy.RandomRoomStrategy();
    public static LevelGeneratorStrategy CELLULAR_AUTOMATA = new LevelGeneratorStrategy.CellularAutomataStrategy();

    


}
