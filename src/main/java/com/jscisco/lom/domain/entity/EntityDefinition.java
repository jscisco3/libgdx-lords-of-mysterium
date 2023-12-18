package com.jscisco.lom.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jscisco.lom.domain.Rarity;

import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityDefinition {
    String name;
    String ai;
    String glyph;

    // Tags that are relevant for this entity
    List<String> tags;
    // The known abilities this entity starts with?
    List<UUID> abilities;

    // Starting stats
    // Difficulty rating
    // Rarity?
    Rarity rarity;
    // Environments?

    public EntityDefinition() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAi() {
        return ai;
    }

    public void setAi(String ai) {
        this.ai = ai;
    }

    public String getGlyph() {
        return glyph;
    }

    public void setGlyph(String glyph) {
        this.glyph = glyph;
    }
}
