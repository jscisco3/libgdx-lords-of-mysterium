package com.jscisco.lom.entity;

import com.jscisco.lom.LOMGame;
import squidpony.FakeLanguageGen;

public class PlayerFactory {

    private static FakeLanguageGen generator = new FakeLanguageGen();

    public static Player createRandomHero() {

        Job job = chooseJob();

        Player player = new Player.Builder(new EntityName(generator.word(true)))
                .withInventory(new Inventory())
                .withEquipment(new Equipment())
                .withHealth(new Health(150))
                .withJob(job)
                .withStats(job.getBaseStatistics())
                .withFieldOfView(new FieldOfView(10f))
                .build();
        return player;
    }

    private static Job chooseJob() {
        float res = LOMGame.rng.nextFloat();
        if (0 <= res && res <= 1f / 3f) {
            return Job.warrior();
        } else if (1f / 3 < res && res <= 2f / 3f) {
            return Job.rogue();
        } else {
            return Job.wizard();
        }
    }

}
