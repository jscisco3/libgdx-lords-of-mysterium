package com.jscisco.lom.systems;

import com.artemis.*;
import com.jscisco.lom.components.InitiativeComponent;
import com.jscisco.lom.components.flags.ActiveTurn;
import com.jscisco.lom.components.flags.PlayerComponent;
import com.jscisco.lom.dungeon.Dungeon;
import com.jscisco.lom.util.Size3D;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestInitiativeSystem {

    private World world;

    private ComponentMapper<InitiativeComponent> mInitiative;
    private ComponentMapper<ActiveTurn> mActiveTurn;
    private ComponentMapper<PlayerComponent> mPlayer;
    private Archetype initiativeArchetype;
    private Dungeon dungeon;

    @BeforeEach
    public void init() {
        dungeon = new Dungeon(new Size3D(20, 20, 1));

        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(new InitiativeSystem(dungeon))
                .build();
        world = new World(config);

        initiativeArchetype = new ArchetypeBuilder()
                .add(InitiativeComponent.class)
                .build(world);
        world.inject(this);
    }

    @Test
    public void initiative_system_should_calculate_initiative_delta_correctly_for_one_entity() {
        int e = world.create(initiativeArchetype);
        mInitiative.get(e).initiative = 1000;
        InitiativeSystem initiativeSystem = world.getSystem(InitiativeSystem.class);
        int minimumInitiative = initiativeSystem.getMinimumInitiative();
        Assertions.assertThat(minimumInitiative).isEqualTo(1000);
    }

    @Test
    public void initiative_system_should_calculate_initiative_delta_correctly_for_multiple_entities() {
        int e1 = world.create(initiativeArchetype);
        mInitiative.get(e1).initiative = 500;

        int e2 = world.create(initiativeArchetype);
        mInitiative.get(e2).initiative = 1000;

        InitiativeSystem initiativeSystem = world.getSystem(InitiativeSystem.class);
        int minimumInitiative = initiativeSystem.getMinimumInitiative();
        Assertions.assertThat(minimumInitiative).isEqualTo(500);
    }

    @Test
    public void initiative_system_should_remove_initiative_delta_for_all_entities() {
        int e1 = world.create(initiativeArchetype);
        mInitiative.get(e1).initiative = 500;

        int e2 = world.create(initiativeArchetype);
        mInitiative.get(e2).initiative = 750;

        int e3 = world.create(initiativeArchetype);
        mInitiative.get(e3).initiative = 550;

        world.process();

        Assertions.assertThat(mInitiative.get(e1).initiative).isEqualTo(0);
        Assertions.assertThat(mInitiative.get(e2).initiative).isEqualTo(250);
        Assertions.assertThat(mInitiative.get(e3).initiative).isEqualTo(50);
    }

    @Test
    public void initiative_system_should_pause_if_players_turn() {
        int e = world.create(initiativeArchetype);
        mInitiative.get(e).initiative = 500;
        mPlayer.create(e);

        InitiativeSystem initiativeSystem = world.getSystem(InitiativeSystem.class);

        world.process();

        Assertions.assertThat(initiativeSystem.checkProcessing()).isFalse();

    }

    @Test
    public void initiative_system_should_add_active_turn_if_initiative_is_zero() {
        int e = world.create(initiativeArchetype);
        mInitiative.get(e).initiative = 500;

        world.process();

        Assertions.assertThat(mActiveTurn.has(e)).isTrue();

    }

}
