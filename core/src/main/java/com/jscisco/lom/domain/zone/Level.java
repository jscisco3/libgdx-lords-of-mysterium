package com.jscisco.lom.domain.zone;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.action.ActionResult;
import com.jscisco.lom.domain.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Level {

    private List<Entity> entities = new ArrayList<>();
    private int currentActorIndex = 0;

    private List<List<Tile>> tiles = new ArrayList<>();

    private int width = 80;
    private int height = 40;

    public Level() {
        // Let's first create the floors.
        for (int i = 0; i < width; i++) {
            List<Tile> column = new ArrayList<>();
            for (int j = 0; j < height; j++) {
                column.add(TileFactory.floorTile());
            }
            tiles.add(column);
        }
        // Now let's do the top and bottom walls
        for (int i = 0; i < width; i++) {
            tiles.get(i).set(0, TileFactory.wallTile());
            tiles.get(i).set(height - 1, TileFactory.wallTile());
        }
        // Left and right walls
        for (int i = 0; i < height; i++) {
            tiles.get(0).set(i, TileFactory.wallTile());
            tiles.get(width - 1).set(i, TileFactory.wallTile());
        }

    }

    /**
     * Process actions from the actors in the current stage
     */
    public void process() {
        Action action = entities.get(currentActorIndex).nextAction();
        // No action, so skip
        if (action == null) {
            return;
        }
        while (true) {
            ActionResult result = action.execute();
            if (!result.success()) {
                // Action failed, so don't increment active actor
                return;
            }
            if (!result.hasAlternate()) {
                // No alternative and the action has succeeded, so continue on.
                break;
            }
            // We have an alternative, so we must process that one before we know if we have ultimately succeeded
            action = result.getAlternative();
        }
        currentActorIndex = (currentActorIndex + 1) % entities.size();
    }

    public Tile getTileAt(Position position) {
        return tiles.get(position.getX()).get(position.getY());
    }

    public void addEntityAtPosition(Entity entity, Position position) {
        this.entities.add(entity);
        this.getTileAt(position).occupy(entity);
        entity.setStage(this);
        entity.move(position);
    }

    public void draw(SpriteBatch batch) {
        batch.begin();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles.get(i).get(j).draw(batch, i, j);
            }
        }
        for (Entity e : entities) {
            e.draw(batch);
        }
        batch.end();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
