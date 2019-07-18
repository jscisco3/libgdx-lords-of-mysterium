package com.jscisco.lom.persistence.serializer;

import com.badlogic.gdx.ai.btree.Task;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

public class CustomTaskSerializer implements JsonSerializer<Task> {

    private static final Logger logger = LoggerFactory.getLogger(CustomTaskSerializer.class);


    @Override
    public JsonElement serialize(Task task, Type type, JsonSerializationContext jsonSerializationContext) {
        logger.info("Serializing task");
        JsonObject obj = new JsonObject();
        return obj;
    }
}
