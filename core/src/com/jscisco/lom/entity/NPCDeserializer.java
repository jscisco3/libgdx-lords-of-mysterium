package com.jscisco.lom.entity;

import com.google.gson.*;
import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.config.BehaviorRepository;

import java.lang.reflect.Type;

public class NPCDeserializer implements JsonDeserializer<NPC> {

    BehaviorRepository behaviorRepository;

    public NPCDeserializer() {
        behaviorRepository = new BehaviorRepository();
    }

    @Override
    public NPC deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        EntityName name = new EntityName(object.get("name").getAsJsonObject().get("value").getAsString());

        return new NPC.Builder(name)
                .withHealth(new Health(object.get("health").getAsJsonObject().get("maxHp").getAsInt()))
                .withGlyph(Assets.Glyphs.valueOf(object.get("glyph").getAsString()))
//                .withBehaviorTree(behaviorRepository.retrieveTree(object.get("behavior").getAsString()))
                .build();
    }
}
