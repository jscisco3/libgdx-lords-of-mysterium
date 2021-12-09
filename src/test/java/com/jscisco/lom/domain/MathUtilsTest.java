package com.jscisco.lom.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MathUtilsTest {

    @Test
    public void clampShouldClampValuesThatAreTooHighToTheMax() {
        float value = 100f;
        float min = 10f;
        float max = 50f;

        float result = MathUtils.clamp(min, max, value);

        assertThat(result).isEqualTo(max);
    }

    @Test
    public void clampsShouldClampValuesThatAreSmallerThanMinimumToMinimum() {
        float value = 1f;
        float min = 10f;
        float max = 50f;

        float result = MathUtils.clamp(min, max, value);

        assertThat(result).isEqualTo(min);
    }

    @Test
    public void clampShouldNotChangeValueThatIsInAcceptableRange() {
        float value = 25f;
        float min = 10f;
        float max = 50f;

        float result = MathUtils.clamp(min, max, value);

        assertThat(result).isEqualTo(value);
    }
}
