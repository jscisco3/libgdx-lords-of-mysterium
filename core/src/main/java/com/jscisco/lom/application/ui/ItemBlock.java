package com.jscisco.lom.application.ui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jscisco.lom.application.GameConfiguration;
import com.jscisco.lom.domain.item.Item;

public class ItemBlock extends Block {
    private final Item item;
    private final Skin skin = GameConfiguration.getSkin();
    private final Table table = new Table(skin);

    public ItemBlock(Item item) {
        this.item = item;

        Container<Table> container = new Container<>();
        container.setSize(300f, 100f);
        container.setActor(table);

        // Initialize table
        Label name = new Label(item.getName().getName(), skin, "default");
        table.add(name).top().center();

        this.addActor(container);
        this.setSize(container.getWidth(), container.getHeight());
    }

    public Item getItem() {
        return item;
    }
}
