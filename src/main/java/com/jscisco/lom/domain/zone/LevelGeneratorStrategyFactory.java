package com.jscisco.lom.domain.zone;

import java.util.AbstractMap;
import java.util.Map;

public class LevelGeneratorStrategyFactory {

    // TODO: Remove references to this and replace them with the map
    public static LevelGeneratorStrategy EMPTY = new LevelGeneratorStrategy.EmptyLevelStrategy();
    public static LevelGeneratorStrategy GENERIC = new LevelGeneratorStrategy.GenericStrategy();

    public static final Map<LevelGeneratorStrategy.Strategy, LevelGeneratorStrategy> generators = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(LevelGeneratorStrategy.Strategy.EMPTY, new LevelGeneratorStrategy.EmptyLevelStrategy()),
            new AbstractMap.SimpleEntry<>(LevelGeneratorStrategy.Strategy.GENERIC, new LevelGeneratorStrategy.GenericStrategy()),
            new AbstractMap.SimpleEntry<>(LevelGeneratorStrategy.Strategy.RANDOM_ROOM, new LevelGeneratorStrategy.RandomRoomStrategy()),
            new AbstractMap.SimpleEntry<>(LevelGeneratorStrategy.Strategy.CELLULAR_AUTOMATA, new LevelGeneratorStrategy.CellularAutomataStrategy())
    );

    public static LevelGeneratorStrategy getStrategy(LevelGeneratorStrategy.Strategy strategy) {
        return generators.get(strategy);
    }

}
