package com.jscisco.lom.shelf.effect;

import com.jscisco.lom.shelf.entity.Constitution;
import com.jscisco.lom.shelf.entity.StatBonus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBuffEffect {

    BuffEffect effect;
    Constitution constitution;

    @BeforeEach
    void setUp() {
        this.constitution = new Constitution(10);
        this.effect = new BuffEffect(constitution, StatBonus.of(4));
    }

    @Test
    public void applyingBuffEffectIncreasesConstitution() {
        this.effect.apply(null);
        Assertions.assertThat(this.constitution.value()).isEqualTo(14);
    }

    @Test
    public void destroyingBuffEffectAfterApplicationRemovesItFromStat() {
        this.effect.apply(null);
        Assertions.assertThat(this.constitution.value()).isEqualTo(14);
        this.effect.destroy();
        Assertions.assertThat(this.constitution.value()).isEqualTo(10);
    }

    @Test
    public void destroyingEffectThatHasntBeenAppliedDoesNothing() {
        this.effect.destroy();
        Assertions.assertThat(this.constitution.value()).isEqualTo(10);
    }

}
