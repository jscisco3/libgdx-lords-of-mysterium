package com.jscisco.lom.domain.repository;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jscisco.lom.application.configuration.GameConfiguration;
import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.entity.EntityDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidmath.IRNG;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class NpcDefinitionRepository {

    private static final Logger logger = LoggerFactory.getLogger(NpcDefinitionRepository.class);
    Set<EntityDefinition> entityDefinitions = new HashSet<>();

    public NpcDefinitionRepository() {
        FileHandle fh = Gdx.files.internal("data/entities.json");
        String data = fh.readString();
        try {
            EntityDefinition[] definitions = GameConfiguration.mapper.readValue(data, EntityDefinition[].class);
            entityDefinitions.addAll(Arrays.asList(definitions));
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<EntityDefinition> byName(Name name) {
        return entityDefinitions.stream().filter(e -> e.getName().equals(name.getName())).collect(Collectors.toList());
    }

    public EntityDefinition randomDefinition(IRNG rng) {
        return entityDefinitions.stream().skip(rng.nextLong(entityDefinitions.size())).findFirst().get();
    }

}
