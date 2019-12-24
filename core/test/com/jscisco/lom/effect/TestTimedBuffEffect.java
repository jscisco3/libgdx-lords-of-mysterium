package com.jscisco.lom.effect;

import com.jscisco.lom.entity.Constitution;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.entity.StatBonus;
import com.jscisco.lom.entity.Statistics;
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
        TimedBuffEffect tbe = new TimedBuffEffect(5, constitution, new StatBonus(15));
        tbe.apply(entity);
        Assertions.assertThat(constitution.value()).isEqualTo(20);
    }

    @Test
    void buffThatRunsOutOfTimeShouldFallOff() {
        Constitution constitution = entity.getStatistics().getConstitution();
        Assertions.assertThat(constitution.value()).isEqualTo(5);
        TimedBuffEffect tbe = new TimedBuffEffect(5, constitution, new StatBonus(15));
        tbe.apply(entity);
        Assertions.assertThat(constitution.value()).isEqualTo(20);
        while (tbe.timeRemaining() > 0) {
            tbe.tick();
        }
        Assertions.assertThat(constitution.value()).isEqualTo(5);
    }

}
