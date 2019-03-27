package com.jscisco.lom.systems;

import com.artemis.*;
import com.jscisco.lom.components.PositionComponent;
import com.jscisco.lom.components.Tile;
import com.jscisco.lom.components.flags.PlayerComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class TestRenderSystem {

    private ComponentMapper<PositionComponent> mPosition;
    private ComponentMapper<Tile> mTile;

    World world;
    Archetype tileArchetype;
    Archetype playerArchetype;

    @BeforeEach
    public void init() {
        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(new RenderSystem())
                .build();
        world = new World(config);
        world.inject(this);

        tileArchetype = new ArchetypeBuilder()
                .add(Tile.class)
                .add(PositionComponent.class)
                .build(world);

        playerArchetype = new ArchetypeBuilder()
                .add(PlayerComponent.class)
                .add(PositionComponent.class)
                .build(world);
    }

    @Test
    public void render_system_should_set_tile_actor_location_correctly() {

//        File file = new File("assets/floor.png");
//        System.out.println(file.getAbsolutePath());
//        Texture texture = new Texture(new FileHandle(file));
//
//        Position3D position = new Position3D(100, 100, 0);
//        int tileEntity = world.create(tileArchetype);
//        mTile.get(tileEntity).actor = new TileActor(new TextureRegion(texture));
//        mPosition.get(tileEntity).position = position;
//
//        world.process();
//
//        TileActor actor = mTile.get(tileEntity).actor;
//
//        Assertions.assertThat(actor.getX()).isEqualTo(100 * texture.getWidth());
//        Assertions.assertThat(actor.getY()).isEqualTo(100 * texture.getHeight());
    }

    @Test
    public void render_system_with_no_player_should_set_everything_visible() {

    }

    @Test
    public void render_system_with_player_should_hide_actors_on_different_z_level() {

    }

}
