package com.jscisco.lom.shelf.effect;

import com.jscisco.lom.shelf.entity.Constitution;
import com.jscisco.lom.shelf.entity.Entity;
import com.jscisco.lom.shelf.entity.StatBonus;
import com.jscisco.lom.shelf.entity.Statistics;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class TestTimedBuffEffect {

    @Spy
    Entity entity = new Entity() {
    };

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        entity.setStatistics(new Statistics.Builder()
                .withConstitution(new Constitution(5))
                .build());
    }

    @Test
    void applyingATimedBuffModifiesTheStatItShould() {
        Constitution constitution = entity.getStatistics().getConstitution();
        Assertions.assertThat(constitution.value()).isEqualTo(5);
        TimedBuffEffect tbe = new TimedBuffEffect(5, new BuffEffect(constitution, new StatBonus(15)));
        tbe.apply(entity);
        Assertions.assertThat(constitution.value()).isEqualTo(20);
    }

    @Test
    void buffThatRunsOutOfTimeShouldFallOff() {
        Constitution constitution = entity.getStatistics().getConstitution();
        Assertions.assertThat(constitution.value()).isEqualTo(5);
        TimedBuffEffect tbe = new TimedBuffEffect(5, new BuffEffect(constitution, new StatBonus(15)));
        tbe.apply(entity);
        Assertions.assertThat(constitution.value()).isEqualTo(20);
        while (tbe.timeRemaining() > 0) {
            tbe.tick();
        }
        Assertions.assertThat(constitution.value()).isEqualTo(5);
    }

}
