package com.jscisco.lom.raws;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class RawMaster {
    private final Logger logger = LoggerFactory.getLogger(RawMaster.class);
    private final ObjectMapper mapper;

    private final Raws raws;

    @Autowired
    public RawMaster(ObjectMapper mapper) {
        this.mapper = mapper;
        this.raws = new Raws();
        this.load();
    }

    private void load() {
        try {
            this.loadRawNPCs();
            this.loadNPCSpawnTable();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadRawNPCs() throws IOException {
        RawNPC[] rawNPCs = this.mapper.readValue(new File("raws/npcs.json"), RawNPC[].class);
        Arrays.stream(rawNPCs).forEach(e -> raws.npcs.put(e.name, e));
        logger.info("Successfully loaded entities: ");
        for (String name : raws.npcs.keySet()) {
            logger.info("---> " + name);
        }
    }

    private void loadNPCSpawnTable() throws IOException {
        SpawnTableEntry[] npcSpawnTableEntries = this.mapper.readValue(new File("raws/npc_spawn_table.json"), SpawnTableEntry[].class);
        raws.npc_spawn_table = List.of(npcSpawnTableEntries);
    }

    public Raws getRaws() {
        return raws;
    }
}
