package com.jscisco.lom.application;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.entity.FieldOfView;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.item.Item;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.Tile;

import java.util.List;

public class LevelRenderer {

    public static void draw(SpriteBatch batch, Assets assets, Camera camera, Level level, Hero hero) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawTerrain(batch, assets, level, hero);
        drawItems(batch, assets, level, hero);
        drawEntities(batch, assets, level, hero);
        batch.end();
    }

    private static void drawTerrain(SpriteBatch batch, Assets assets, Level level, Hero hero) {
        for (int i = 0; i < level.getWidth(); i++) {
            for (int j = 0; j < level.getHeight(); j++) {
                Tile t = level.getTileAt(Position.of(i, j));
                if (hero.getFieldOfView().isInSight(Position.of(i, j))) {
                    t.draw(batch, assets, i, j);
                } else if (t.isExplored()) {
                    batch.setColor(Color.DARK_GRAY);
                    t.draw(batch, assets, i, j);
                    batch.setColor(Color.WHITE);
                }
            }
        }
    }

    private static void drawItems(SpriteBatch batch, Assets assets, Level level, Hero hero) {
        FieldOfView fov = hero.getFieldOfView();
        level.getItems().forEach(item -> {
            assert item.getPosition() != null;
            if (fov.isInSight(item.getPosition())) {
                item.draw(batch, assets);
            }
        });
    }

    private static void drawEntities(SpriteBatch batch, Assets assets, Level level, Hero hero) {
        FieldOfView fov = hero.getFieldOfView();
        level.getEntities().forEach(entity -> {
            assert entity.getPosition() != null;
            if (fov.isInSight(entity.getPosition())) {
                entity.draw(batch, assets);
            }
        });
    }

}
