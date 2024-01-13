package com.jscisco.lom.raws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jscisco.lom.application.configuration.GameConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class RawMaster {
    private Logger logger = LoggerFactory.getLogger(RawMaster.class);
    private ObjectMapper mapper = GameConfiguration.mapper;

    private Raws raws = new Raws();

    public void load() {
        try {
            RawEntity[] rawEntities = new ObjectMapper().readValue(new File("raws/entities.json"), RawEntity[].class);
            raws.entities = Arrays.asList(rawEntities);
            logger.info("Successfully loaded entities: " + raws.entities);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
