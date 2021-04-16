package com.jscisco.lom.domain.zone;

import java.util.ArrayList;
import java.util.List;

public class Zone {
    List<Level> levels;
    int depth;

    public Zone(int depth) {
        this.levels = new ArrayList<>();
        this.depth = depth;
        for (int i = 0; i < depth; i++) {
            levels.add(new Level());
        }
    }
}
