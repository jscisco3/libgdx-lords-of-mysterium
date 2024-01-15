package com.jscisco.lom.raws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jscisco.lom.application.configuration.GameConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

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
            RawNPC[] rawEntities = this.mapper.readValue(new File("raws/entities.json"), RawNPC[].class);
            Arrays.stream(rawEntities).forEach(e -> raws.npcs.put(e.name, e));
            logger.info("Successfully loaded entities: ");
            for (String name : raws.npcs.keySet()) {
                logger.info("---> " + name);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Raws getRaws() {
        return raws;
    }
}
