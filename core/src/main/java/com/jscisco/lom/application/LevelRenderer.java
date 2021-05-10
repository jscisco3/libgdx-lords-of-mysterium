package com.jscisco.lom.application;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.Tile;

public class LevelRenderer {

    public static void draw(SpriteBatch batch, Assets assets, Camera camera, Level level, Hero hero) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (int i = 0; i < level.getWidth(); i++) {
            for (int j = 0; j < level.getHeight(); j++) {
                Tile t = level.getTileAt(Position.of(i, j));
                if (hero.getFieldOfView().isInSight(Position.of(i, j))) {
                    t.draw(batch, assets, i, j, true);
                } else if (t.isExplored()) {
                    batch.setColor(Color.DARK_GRAY);
                    t.draw(batch, assets, i, j, false);
                    batch.setColor(Color.WHITE);
                }
            }
        }
        batch.end();
    }

}
