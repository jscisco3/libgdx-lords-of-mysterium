package com.jscisco.lom.application;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.jscisco.lom.Game;
import com.jscisco.lom.application.configuration.GameConfiguration;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.FieldOfView;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.zone.Tile;
import com.jscisco.lom.map.BuilderChain;
import com.jscisco.lom.map.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class DebugLevelScreen extends AbstractScreen {
    private final Logger logger = LoggerFactory.getLogger(DebugLevelScreen.class);
    private final BuilderChain chain;
    private final OrthographicCamera camera;
    private int historyIndex = 0;
    private Level currentLevel;
    // Matrix4 levelBatchTransform = new Matrix4(new Vector3(0f, 0f, 0f), new Quaternion(), new Vector3(1f, 1f, 1f));

    public DebugLevelScreen(Game game, BuilderChain chain) {
        super(game);
        this.chain = chain;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, GameConfiguration.SCREEN_WIDTH, GameConfiguration.SCREEN_HEIGHT);
        this.camera.update();
        this.currentLevel = this.chain.getBuildData().getHistory().getFirst();
        logger.info(MessageFormat.format("Amount of history: {0}", this.chain.getBuildData().getHistory().size()));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (delta * 1000.0f > 16f) {
            this.historyIndex += 1;
            this.currentLevel = this.chain.getBuildData().getHistory().get(this.historyIndex);
            if (this.historyIndex > this.chain.getBuildData().getHistory().size() - 1) {
                this.historyIndex = 0;
            }
        }
        // batch.setTransformMatrix(levelBatchTransform);
        batch.setProjectionMatrix(this.camera.combined);
        batch.begin();
        drawTerrain(batch, this.game.getAssets(), this.currentLevel);
        batch.end();
    }

    @Override
    public void show() {
        super.show();
    }

    private static void drawTerrain(SpriteBatch batch, Assets assets, Level level) {
        for (int i = 0; i < level.getWidth(); i++) {
            for (int j = 0; j < level.getHeight(); j++) {
                Tile t = level.getTile(Position.of(i, j));
                t.draw(batch, assets, i, j);
            }
        }
    }

    private static void drawItems(SpriteBatch batch, Assets assets, Level level) {
        // level.getItems().forEach(item -> {
        // assert item.getPosition() != null;
        // if (fov.isInSight(item.getPosition())) {
        // item.draw(batch, assets);
        // }
        // });
    }

    private static void drawEntities(SpriteBatch batch, Assets assets, Level level) {
        // level.getEntities().forEach(entity -> {
        // assert entity.getPosition() != null;
        // if (fov.isInSight(entity.getPosition())) {
        // entity.draw(batch, assets);
        // }
        // });
    }
}
