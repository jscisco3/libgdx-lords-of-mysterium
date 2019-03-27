package com.jscisco.lom.archetypes;

import com.artemis.Archetype;
import com.artemis.ArchetypeBuilder;
import com.artemis.World;
import com.jscisco.lom.components.InitiativeComponent;
import com.jscisco.lom.components.PositionComponent;
import com.jscisco.lom.components.Tile;
import com.jscisco.lom.components.flags.BlockOccupier;
import com.jscisco.lom.components.flags.BlocksVision;
import com.jscisco.lom.components.flags.PlayerComponent;

public class ArchetypeFactory {

    private World world;

    public Archetype playerArchetype;
    public Archetype floorArchetype;
    public Archetype wallArchetype;

    public Archetype stairsUpArchetype;
    public Archetype stairsDownArchetype;

    public ArchetypeFactory(World world) {
        this.world = world;
        world.inject(this);

        playerArchetype = new ArchetypeBuilder()
                .add(PositionComponent.class)
                .add(PlayerComponent.class)
                .add(Tile.class)
                .add(InitiativeComponent.class)
                .build(world);

        floorArchetype = new ArchetypeBuilder()
                .add(PositionComponent.class)
                .add(Tile.class)
                .build(world);

        wallArchetype = new ArchetypeBuilder()
                .add(PositionComponent.class)
                .add(Tile.class)
                .add(BlocksVision.class)
                .add(BlockOccupier.class)
                .build(world);
    }
}
