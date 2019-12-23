package com.jscisco.lom.entities;

import com.jscisco.lom.entity.AbstractStat;
import com.jscisco.lom.entity.StatBonus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestAbstractStat {

    private AbstractStat stat = new AbstractStat(){};

    @Test
    public void statWithNoBonusesShouldJustReturnBaseValueForValue() {
        stat.setBaseValue(10);
        Assertions.assertThat(stat.value()).isEqualTo(10);
    }

    @Test
    public void statWithAppliedBonusHasThatBonusWhenValueRetrieved() {
        stat.setBaseValue(10);
        stat.applyBonus(new StatBonus(10));
        Assertions.assertThat(stat.value()).isEqualTo(20);
    }

    @Test
    public void statAfterRemovingBonusHasBaseValue() {
        stat.setBaseValue(5);
        Assertions.assertThat(stat.value()).isEqualTo(5);
        stat.applyBonus(new StatBonus(10));
        Assertions.assertThat(stat.value()).isEqualTo(15);
        // This should be the same object as what was added above.
        stat.removeBonus(new StatBonus(10));
        Assertions.assertThat(stat.value()).isEqualTo(5);
    }

}
