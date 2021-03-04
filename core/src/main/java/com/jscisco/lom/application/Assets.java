package com.jscisco.lom.application;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    private final AssetManager assetManager;

    /**
     * Assets go here
     */
    // Terrain features
    public static final AssetDescriptor<Texture> floor = new AssetDescriptor<Texture>("textures/features/floor.png", Texture.class);
    public static final AssetDescriptor<Texture> wall = new AssetDescriptor<Texture>("textures/features/wall.png", Texture.class);
    // Entities
    public static final AssetDescriptor<Texture> warrior = new AssetDescriptor<Texture>("textures/entities/warrior.png", Texture.class);
    public static final AssetDescriptor<Texture> golem = new AssetDescriptor<Texture>("textures/entities/golem.png", Texture.class);
    // Kingdom screen
    public static final AssetDescriptor<Texture> background = new AssetDescriptor<Texture>("textures/kingdom/parchmentAncient.png", Texture.class);

    public Assets() {
        this.assetManager = new AssetManager();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void load() {
        assetManager.load(floor);
        assetManager.load(wall);
        assetManager.load(warrior);
        assetManager.load(golem);
        assetManager.load(background);
    }

    public Texture getTexture(AssetDescriptor<Texture> descriptor) {
        return assetManager.get(descriptor);
    }

    public void dispose() {
        this.assetManager.dispose();
    }
}
