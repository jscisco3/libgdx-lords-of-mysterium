package com.jscisco.lom.map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.raws.RawMaster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squidpony.squidmath.RNG;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AreaBasedStartingPositionTest {

    private RNG rng;
    private BuilderChain chain;

    private RawMaster raws;

    @BeforeEach
    void setUp() {
        this.rng = new RNG(0xDEADBEEFL);
        this.chain = new BuilderChain(1, 20, 20);
        this.chain.startWith(new DebugStarterBuilder());
        this.raws = new RawMaster(new ObjectMapper());
    }

    @Test
    void testTopLeft() {
        this.chain.with(new AreaBasedStartingPosition(XStart.LEFT, YStart.TOP));
        this.chain.build(this.rng, this.raws);

        assertThat(this.chain.getBuildData().getStartingPosition()).isEqualTo(Position.of(1, 18));
    }

    @Test
    void testCenter() {
        this.chain.with(new AreaBasedStartingPosition(XStart.CENTER, YStart.CENTER));
        this.chain.build(this.rng, this.raws);

        assertThat(this.chain.getBuildData().getStartingPosition()).isEqualTo(Position.of(10, 10));
    }

    @Test
    void testBottomRight() {
        this.chain.with(new AreaBasedStartingPosition(XStart.RIGHT, YStart.BOTTOM));
        this.chain.build(this.rng, this.raws);

        assertThat(this.chain.getBuildData().getStartingPosition()).isEqualTo(Position.of(18, 1));
    }
}
