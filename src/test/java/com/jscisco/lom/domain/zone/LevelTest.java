package com.jscisco.lom.domain.zone;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.EntityFactory;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.entity.NPC;
import com.jscisco.lom.domain.entity.RestAIController;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.in;

public class LevelTest {

    private static final Logger logger = LoggerFactory.getLogger(LevelTest.class);

    private Level level;

    @Test
    public void whenStageIsCreated_itHasWalls() {
        level = new Level(25, 25, new LevelGeneratorStrategy.EmptyLevelStrategy());

        assertThat(level.getTileAt(Position.of(0, 0)).getFeature()).isEqualTo(FeatureFactory.WALL);
        assertThat(level.getTileAt(Position.of(level.getWidth() - 1, level.getHeight() - 1)).getFeature()).isEqualTo(FeatureFactory.WALL);
        assertThat(level.getTileAt(Position.of(0, level.getHeight() - 1)).getFeature()).isEqualTo(FeatureFactory.WALL);
        assertThat(level.getTileAt(Position.of(level.getWidth() - 1, 0)).getFeature()).isEqualTo(FeatureFactory.WALL);

        assertThat(level.getTileAt(Position.of(level.getWidth() / 2, level.getHeight() / 2)).getFeature()).isEqualTo(FeatureFactory.FLOOR);
    }

    @Test
    public void whenActorAddedToStage_positionAndTileUpdatedAppropriately() {
        level = new Level(25, 25, new LevelGeneratorStrategy.EmptyLevelStrategy());

        Hero p = EntityFactory.player();
        Position expectedPosition = Position.of(5, 5);

        level.addEntityAtPosition(p, expectedPosition);

        assertThat(p.getPosition()).isEqualTo(expectedPosition);
        assertThat(level.getEntityAtPosition(expectedPosition).isPresent()).isTrue();
        assertThat(level.getEntityAtPosition(expectedPosition).get()).isEqualTo(p);
        assertThat(p.getLevel()).isEqualTo(level);
    }

    @Test
    public void givenAListOfEntities_processProcessesASingleEntity() {
        // Given
        level = new Level(25, 25, new LevelGeneratorStrategy.EmptyLevelStrategy());
        for (int i = 0; i < 10; i++) {
            NPC golem = EntityFactory.golem();
            level.addEntityAtPosition(golem, Position.of(i + + 10, i + 10));
        }
        int initialActorIndex = level.getCurrentActorIndex();
        int expectedActorIndex = initialActorIndex + 1;
        // When
        level.process();
        // Then
        assertThat(level.getCurrentActorIndex()).isEqualTo(expectedActorIndex);
    }

    @Test
    public void givenAListOfEntitiesThatRest_howLongIsASingleLoop() {
        level = new Level(25, 25, new LevelGeneratorStrategy.EmptyLevelStrategy());
        int numberOfEntities = 100;
        for (int i = 0; i < numberOfEntities; i++) {
            NPC golem = EntityFactory.golem();
//            golem.setAiController(new RestAIController(golem));
            level.addEntityAtPosition(golem, level.getEmptyTile(golem));
        }
        long totalTime = 0L;
        for (int i = 0; i < 10; i++) {
            Instant startTime = Instant.now();
            for (int j = 0; j < level.getEntities().size(); j++) {
                level.process();
            }
            Instant endTime = Instant.now();
            long duration = Duration.between(startTime, endTime).toMillis();
            totalTime += duration;
            logger.info("Loop " + i + " took " + duration + "ms");
        }
        logger.info("Total time: " + totalTime + "ms");
    }

}
