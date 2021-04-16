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
    public static final AssetDescriptor<Texture> stairsDown = new AssetDescriptor<Texture>("textures/features/stairsDown.png", Texture.class);
    public static final AssetDescriptor<Texture> stairsUp = new AssetDescriptor<Texture>("textures/features/stairsUp.png", Texture.class);
    // Entities
    public static final AssetDescriptor<Texture> warrior = new AssetDescriptor<Texture>("textures/entities/warrior.png", Texture.class);
    public static final AssetDescriptor<Texture> golem = new AssetDescriptor<Texture>("textures/entities/golem.png", Texture.class);
    // Items
    public static final AssetDescriptor<Texture> sword = new AssetDescriptor<Texture>("textures/items/sword.png", Texture.class);
    public static final AssetDescriptor<Texture> ring = new AssetDescriptor<Texture>("textures/items/ring.png", Texture.class);
    // Kingdom screen
    public static final AssetDescriptor<Texture> background = new AssetDescriptor<Texture>("textures/kingdom/parchmentAncient.png", Texture.class);
    public static final AssetDescriptor<Texture> inn = new AssetDescriptor<Texture>("textures/kingdom/inn.png", Texture.class);
    public static final AssetDescriptor<Texture> portal = new AssetDescriptor<Texture>("textures/kingdom/skull.png", Texture.class);

    public Assets() {
        this.assetManager = new AssetManager();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void load() {
        // Features
        assetManager.load(floor);
        assetManager.load(wall);
        assetManager.load(stairsDown);
        assetManager.load(stairsUp);
        // Entities
        assetManager.load(warrior);
        assetManager.load(golem);
        // Items
        assetManager.load(sword);
        assetManager.load(ring);
        // Kingdom
        assetManager.load(background);
        assetManager.load(inn);
        assetManager.load(portal);
    }

    public Texture getTexture(AssetDescriptor<Texture> descriptor) {
        return assetManager.get(descriptor);
    }

    public void dispose() {
        this.assetManager.dispose();
    }
}
