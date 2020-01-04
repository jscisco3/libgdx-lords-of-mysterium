package com.jscisco.lom.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import net.dongliu.gson.GsonJava8TypeAdapterFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NPCRepository {

    public static Map<String, NPC> npcs = new HashMap<>();

    public static void load() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory())
                .registerTypeAdapter(NPC.class, new NPCDeserializer())
                .create();

        try {
            List<NPC> parsedNpcs = gson.fromJson(new FileReader("npcs.json"), new TypeToken<List<NPC>>() {
            }.getType());
            parsedNpcs.forEach(npc -> npcs.put(npc.getName().get(), npc));

        } catch (JsonSyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
