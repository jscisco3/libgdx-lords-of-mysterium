package com.jscisco.lom.shelf.entity;

import com.jscisco.lom.shelf.LOMGame_Deprecated;
import com.jscisco.lom.shelf.ability.Ability;
import com.jscisco.lom.shelf.ability.AbilityDescription;
import com.jscisco.lom.shelf.ability.AbilityName;
import com.jscisco.lom.shelf.combat.Damage;
import com.jscisco.lom.shelf.combat.DamageType;
import com.jscisco.lom.shelf.domain.Health;
import com.jscisco.lom.shelf.effect.DamageEffect;
import squidpony.FakeLanguageGen;
import squidpony.squidai.BlastAOE;
import squidpony.squidgrid.Radius;
import squidpony.squidmath.Coord;

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
        player.learnAbility(new Ability.Builder()
                .withName(new AbilityName("Fireball"))
                .withDescription(new AbilityDescription("Burn a bunch of people"))
                .withEffect(new DamageEffect(new Damage(DamageType.FIRE, 10, 30)))
                .withCooldown(0)
                .withAOE(new BlastAOE(Coord.get(0, 0), 2, Radius.SQUARE))
                .withTargetRequired()
                .build()
        );
        return player;
    }

    private static Job chooseJob() {
        float res = LOMGame_Deprecated.rng.nextFloat();
        if (0 <= res && res <= 1f / 3f) {
            return Job.warrior();
        } else if (1f / 3 < res && res <= 2f / 3f) {
            return Job.rogue();
        } else {
            return Job.wizard();
        }
    }

}
