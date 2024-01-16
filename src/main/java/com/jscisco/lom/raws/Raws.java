package com.jscisco.lom.raws;

import java.util.HashMap;
import java.util.Map;

public class Raws {
    protected Map<String, RawNPC> npcs = new HashMap<>();

    public RawNPC getNPC(String name) {
        RawNPC raw = npcs.get(name);
        if (raw == null) {
            throw new RuntimeException("Illegal name for a Raw NPC. Attempted to look up {name}, but was" +
                    "not in the raws.");
        }
        return raw;
    }
}
