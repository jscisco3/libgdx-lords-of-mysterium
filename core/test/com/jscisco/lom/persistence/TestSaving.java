package com.jscisco.lom.persistence;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.config.Config;
import com.jscisco.lom.domain.Health;
import com.jscisco.lom.entity.*;
import com.jscisco.lom.persistence.serializer.CustomBehaviorTreeSerializer;
import com.jscisco.lom.persistence.serializer.CustomTaskSerializer;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.Size3D;
import com.jscisco.lom.zone.Stage;
import com.jscisco.lom.zone.Zone;
import com.jscisco.lom.zone.strategies.EmptyStageGenerationStrategy;
import com.jscisco.lom.zone.strategies.GenericStrategy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
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
        Player player = new Player.Builder(new EntityName("Saved"))
                .withJob(job)
                .withStats(job.getBaseStatistics())
                .withInventory(new Inventory())
                .withEquipment(new Equipment())
                .build();

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(player);
//        writeToFile(json, "save_player.json");
    }

    /**
     * Give a stage that has a player
     * When we save the stage
     * It should have the player in it
     */
    @Test
    void saveAStage() {
        Stage stage = new Stage(25, 25, true, true, new GenericStrategy());
        Job job = Job.warrior();
        Player player = new Player.Builder(new EntityName("Saved"))
                .withJob(job)
                .withStats(job.getBaseStatistics())
                .withInventory(new Inventory())
                .withEquipment(new Equipment())
                .withPosition(stage.findEmptyPosition().get())
                .build();

        stage.addEntity(player);

//        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(stage);
//        writeToFile(json, "save_stage.json");
    }

    /**
     * Given a random player
     * When we save it
     * Then we can load it
     */
    @Test
    void testSaveAndLoadRandomPlayer() {
        Player player = PlayerFactory.createRandomHero();
//        writeToFile(new Gson().toJson(player), player.getName());
        Player loaded = readFromFile(player.getName().name(), Player.class);
//        writeToFile(new Gson().toJson(loaded), loaded.getName() + "loaded");
    }

    /**
     * Given a Zone
     * When we serialize it
     * Then we should have valid json
     */
    @Test
    @Disabled
    void shouldBeAbleToSerializeZone() {
        Zone zone = new Zone(new Size3D(30, 30, 2), PlayerFactory.createRandomHero());

        String json = new Gson().toJson(zone);
//        writeToFile(json, "zone.json");
    }


    @Disabled
    @Test
    void shouldBeAbleToDeserializeZone() {
        Zone zone = new Zone(new Size3D(50, 50, 5), PlayerFactory.createRandomHero());
        String json = new Gson().toJson(zone);

        Zone zone2 = new Gson().fromJson(json, Zone.class);

        Assertions.assertThat(zone.getHeight()).isEqualTo(zone2.getHeight());
    }

    /**
     * Given an NPC with a BehaviorTree
     * When I serialize it
     * Then I should have valid JSON
     */
    @Test
    void shouldBeAbleToSerializeNPCWithoutBehaviorTree() {
        NPC npc = new NPC.Builder(new EntityName("NPC"))
                .withGlyph(Assets.Glyphs.RAT)
                .withHealth(new Health(50))
                .withPosition(new Position(20, 20))
                .withEquipment(new Equipment())
                .withJob(Job.rogue())
                .build();

        String json = new Gson().toJson(npc);
        Assertions.assertThat(json.isEmpty()).isFalse();
    }

    /**
     * Given an NPC with a behavior tree
     * When I serialize it
     * Then I should have valid json
     */
    @Test
    void shouldBeAbleToSerializeNPCWithBehaviorTree() {
        NPC npc = new NPC.Builder(new EntityName("NPC"))
                .withGlyph(Assets.Glyphs.RAT)
                .withHealth(new Health(50))
                .withPosition(new Position(20, 20))
                .withEquipment(new Equipment())
                .withJob(Job.rogue())
                .withBehaviorTree(Config.repository.retrieveTree("wander"))
                .build();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(BehaviorTree.class, new CustomBehaviorTreeSerializer())
                .create();

        String json = gson.toJson(npc);
        Assertions.assertThat(json.isEmpty()).isFalse();
    }

    /**
     * Given an NPC with a behavior tree
     * that has taken a step
     * When I serialize it
     * Then I should have valid json
     */
    @Test
    void shouldBeAbleToSerializeNPCWithBehaviorTreeThatTookStep() {

        Stage stage = new Stage(50, 50, true, false, new EmptyStageGenerationStrategy());

        NPC npc = new NPC.Builder(new EntityName("NPC"))
                .withGlyph(Assets.Glyphs.RAT)
                .withHealth(new Health(50))
                .withPosition(new Position(20, 20))
                .withEquipment(new Equipment())
                .withJob(Job.rogue())
                .withBehaviorTree(Config.repository.retrieveTree("wander"))
                .withStage(stage)
                .build();

        stage.addEntity(npc);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(BehaviorTree.class, new CustomBehaviorTreeSerializer())
                .registerTypeAdapter(Task.class, new CustomTaskSerializer())
                .create();

        npc.getNextAction();
        String json = gson.toJson(npc);
        logger.info(json);
        Assertions.assertThat(json.isEmpty()).isFalse();
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
