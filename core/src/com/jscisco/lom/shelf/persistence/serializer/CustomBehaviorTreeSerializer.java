package com.jscisco.lom.shelf.persistence.serializer;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.Task;
import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

public class CustomBehaviorTreeSerializer implements JsonSerializer<BehaviorTree> {

    private static final Logger logger = LoggerFactory.getLogger(CustomBehaviorTreeSerializer.class);

    @Override
    public JsonElement serialize(BehaviorTree behaviorTree, Type type, JsonSerializationContext jsonSerializationContext) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Task.class, new CustomTaskSerializer())
                .create();

        logger.info("Serializing tree");
        JsonObject obj = new JsonObject();
        return obj;
    }
}
