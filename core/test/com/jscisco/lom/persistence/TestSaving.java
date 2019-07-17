package com.jscisco.lom.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jscisco.lom.attributes.Equipment;
import com.jscisco.lom.attributes.Inventory;
import com.jscisco.lom.attributes.Job;
import com.jscisco.lom.entity.Player;
import com.jscisco.lom.entity.PlayerFactory;
import com.jscisco.lom.zone.Stage;
import com.jscisco.lom.zone.StageImpl;
import com.jscisco.lom.zone.strategies.GenericStrategy;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

class TestSaving {

    private static final Logger logger = LoggerFactory.getLogger(TestSaving.class);

    /**
     * Given a player with
     */
    @Test
    void shouldSavePlayerWithoutAStageReference() {
        Job job = Job.warrior();
        Player player = new Player.Builder("Saved")
                .withJob(job)
                .withStats(job.getBaseStats())
                .withInventory(new Inventory())
                .withEquipment(new Equipment())
                .build();

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(player);
        writeToFile(json, "save_player.json");
    }

    /**
     * Give a stage that has a player
     * When we save the stage
     * It should have the player in it
     */
    @Test
    void saveAStage() {
        Stage stage = new StageImpl(25, 25, true, true, new GenericStrategy());
        Job job = Job.warrior();
        Player player = new Player.Builder("Saved")
                .withJob(job)
                .withStats(job.getBaseStats())
                .withInventory(new Inventory())
                .withEquipment(new Equipment())
                .withPosition(stage.findEmptyPosition())
                .build();

        stage.addEntity(player);

//        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(stage);
        writeToFile(json, "save_stage.json");
    }

    /**
     * Given a random player
     * When we save it
     * Then we can load it
     */
    @Test
    void testSaveAndLoadRandomPlayer() {
        Player player = PlayerFactory.createRandomHero();
        writeToFile(new Gson().toJson(player), player.getName());
        Player loaded = readFromFile(player.getName(), Player.class);
        writeToFile(new Gson().toJson(loaded), loaded.getName() + "loaded");
    }

    private void writeToFile(String json, String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false));
            writer.append(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private <T> T readFromFile(String filename, Class<T> classOf) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String json = reader.readLine();
            reader.close();
            Gson gson = new Gson();
            return gson.fromJson(json, classOf);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
