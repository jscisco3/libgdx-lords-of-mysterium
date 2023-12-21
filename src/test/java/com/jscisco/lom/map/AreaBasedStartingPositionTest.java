package com.jscisco.lom.map;

import com.jscisco.lom.domain.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squidpony.squidmath.RNG;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// TODO: @CartesianTest and other parametrized stuff
public class AreaBasedStartingPositionTest {

    private RNG rng;
    private BuilderChain chain;

    @BeforeEach
    void setUp() {
        this.rng = new RNG(0xDEADBEEFL);
        this.chain = new BuilderChain(1, 20, 20);
        this.chain.startWith(new DebugStarterBuilder());
    }

    @Test
    void testTopLeft() {
        this.chain.with(new AreaBasedStartingPosition(XStart.LEFT, YStart.TOP));
        this.chain.build(this.rng);

        assertThat(this.chain.getBuildData().getStartingPosition()).isEqualTo(Position.of(1, 18));
    }

    @Test
    void testCenter() {
        this.chain.with(new AreaBasedStartingPosition(XStart.CENTER, YStart.CENTER));
        this.chain.build(this.rng);

        assertThat(this.chain.getBuildData().getStartingPosition()).isEqualTo(Position.of(10, 10));
    }

    @Test
    void testBottomRight() {
        this.chain.with(new AreaBasedStartingPosition(XStart.RIGHT, YStart.BOTTOM));
        this.chain.build(this.rng);

        assertThat(this.chain.getBuildData().getStartingPosition()).isEqualTo(Position.of(18, 1));
    }
}
