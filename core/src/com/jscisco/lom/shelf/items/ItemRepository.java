package com.jscisco.lom.shelf.items;

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

public class ItemRepository {

    public static Map<ItemName, Item> items = new HashMap<>();

    public static void load() {
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory()).create();
        try {
            List<Item> parsedItems = gson.fromJson(new FileReader("items.json"), new TypeToken<List<Item>>() {
            }.getType());
            parsedItems.forEach(x -> items.put(x.getItemName(), x));
        } catch (JsonSyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
