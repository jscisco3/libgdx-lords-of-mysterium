package com.jscisco.lom.application.ui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jscisco.lom.application.GameConfiguration;
import com.jscisco.lom.domain.entity.Player;

/**
 * This class represents a displayable block of information about the hero
 * Used for:
 * Initial hero select
 * (possibly) Graveyeard
 */
public class HeroBlock extends Group {
    private final Player player;
    private final Skin skin = GameConfiguration.getSkin();
    private final Table table = new Table(skin);

    public HeroBlock(Player player) {
        this.player = player;

        Container<Table> container = new Container<>();
        container.setSize(300f, 100f);
        container.setActor(table);

        // Initialize table
        Label name = new Label(player.getName().getName(), skin, "default");
        table.add(name).top().center();

        this.addActor(container);
        this.setSize(container.getWidth(), container.getHeight());
    }
}
