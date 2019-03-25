package com.jscisco.lom.systems;

import com.artemis.ComponentMapper;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.jscisco.lom.components.flags.Destroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDestroySystem {

    private ComponentMapper<Destroy> mDestroy;

    World world;

    @BeforeEach
    public void init() {
        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(new DestroySystem())
                .build();
        world = new World(config);
        world.inject(this);
    }

    @Test
    public void destroy_system_removes_entity_from_world() {
        int e = world.create();
        mDestroy.create(e);
        world.process();

        Assertions.assertThat(mDestroy.has(e)).isFalse();
    }

}
