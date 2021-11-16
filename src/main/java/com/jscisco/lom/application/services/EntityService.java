package com.jscisco.lom.application.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jscisco.lom.application.configuration.GameConfiguration;
import com.jscisco.lom.content.EntityDefinition;
import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.entity.HunterSeekerAI;
import com.jscisco.lom.domain.entity.NPC;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class EntityService {

    private Map<String, EntityDefinition> entities = new HashMap<>();

    public EntityService() throws JsonProcessingException {
        ObjectMapper mapper = GameConfiguration.mapper;

//        EntityDefinition[] entityDefinitions = mapper.readValue("data/entities.json", EntityDefinition[].class);
//        Arrays.stream(entityDefinitions).forEach(ed -> {
//            entities.put(ed.getName(), ed);
//        });
    }

    /**
     * Generate an NPC, but do not add it to any particular level.
     *
     * @param definition The base definition of the NPC.
     * @return the generated NPC
     */
    public NPC generateNPC(EntityDefinition definition) {
        NPC npc = new NPC.Builder()
                .withName(Name.of(definition.getName()))
                .withGlyph(definition.getGlyph())
                .build();

        // Switch on ai?
        npc.setAiController(new HunterSeekerAI());
        return npc;
    }

}
