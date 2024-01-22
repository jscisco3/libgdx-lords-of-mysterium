package com.jscisco.lom.raws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jscisco.lom.RandomEntry;
import com.jscisco.lom.RandomTable;
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
        logger.info("Loading data");
        try {
            logger.info("Loading NPC data");
            this.loadRawNPCs();
            this.loadNPCSpawnTable();

            logger.info("Loading Item Data");
            this.loadRawItems();
            this.loadItemSpawnTable();
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

    private void loadRawItems() throws IOException {
        RawItem[] rawItems = this.mapper.readValue(new File("raws/items.json"), RawItem[].class);
        Arrays.stream(rawItems).forEach(e -> raws.items.put(e.name, e));
        logger.info("Successfully loaded items: ");
        for (String name : raws.items.keySet()) {
            logger.info("---> " + name);
        }
    }

    private void loadNPCSpawnTable() throws IOException {
        SpawnTableEntry[] npcSpawnTableEntries = this.mapper.readValue(new File("raws/npc_spawn_table.json"), SpawnTableEntry[].class);
        raws.npcSpawnTable = List.of(npcSpawnTableEntries);
    }

    private void loadItemSpawnTable() throws IOException {
        SpawnTableEntry[] itemSpawnTableEntries = this.mapper.readValue(new File("raws/item_spawn_table.json"), SpawnTableEntry[].class);
        raws.itemSpawnTable = List.of(itemSpawnTableEntries);
    }

    public Raws getRaws() {
        return raws;
    }

    public RandomTable getNpcSpawnTableForDepth(int depth) {
        RandomTable table = new RandomTable();
        this.raws.npcSpawnTable.stream()
                .filter(e -> depth >= e.minimumDepth && depth <= e.maximumDepth)
                .forEach(e -> table.add(new RandomEntry(e.name, e.addMapDepthToWeight ? e.weight + depth : e.weight)));
        return table;
    }

    public RandomTable getItemSpawnTableForDepth(int depth) {
        RandomTable table = new RandomTable();
        this.raws.itemSpawnTable.stream()
                .filter(e -> depth >= e.minimumDepth && depth <= e.maximumDepth)
                .forEach(e -> table.add(new RandomEntry(e.name, e.addMapDepthToWeight ? e.weight + depth : e.weight)));
        return table;
    }
}
