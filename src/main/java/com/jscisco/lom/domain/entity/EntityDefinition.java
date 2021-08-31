package com.jscisco.lom.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityDefinition {
    String name;
    String ai;
    String glyph;

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
