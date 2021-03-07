package com.jscisco.lom.domain.kingdom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class InnTest {

    Inn inn;

    @BeforeEach
    public void setup() {
        this.inn = new Inn();
    }

    @Test
    void whenIGenerateHeroes_thenTheCorrectNumberAreGenerated() {
        inn.generateHeroes(10);

        assertThat(inn.getAvailableHeroes().size()).isEqualTo(10);
    }
}
