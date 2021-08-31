package com.jscisco.lom.application.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jscisco.lom.application.configuration.GameConfiguration;
import com.jscisco.lom.domain.entity.EntityDefinition;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EntityService {

    private Map<String, EntityDefinition> entities = new HashMap<>();

    public EntityService() throws JsonProcessingException {
        ObjectMapper mapper = GameConfiguration.mapper;

        EntityDefinition[] entityDefinitions = mapper.readValue("data/entities.json", EntityDefinition[].class);
        Arrays.stream(entityDefinitions).forEach(ed -> {
            entities.put(ed.getName(), ed);
        });
    }

}
