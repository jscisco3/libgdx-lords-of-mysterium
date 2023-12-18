package com.jscisco.lom.application.ui;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jscisco.lom.application.configuration.GameConfiguration;

import java.util.List;

public class BlockTable extends Table {
    private final List<? extends Block> blocks;

    // Table table = new Table(GameConfiguration.getSkin());
    // for (
    // Hero p : heroes) {
    // table.add(new HeroBlock(p)).top();
    //// table.add(new Label(p.getName().getName(), table.getSkin(), "default")).top().center();
    // table.row();
    // }
    // Table scrollTable = new Table(GameConfiguration.getSkin());
    // scrollTable.setFillParent(true);
    // ScrollPane scroller = new ScrollPane(table, GameConfiguration.getSkin());
    // scroller.setFadeScrollBars(false);
    // float width = 350f;
    // scrollTable.add(scroller)
    // .width(width)
    // .expandY();
    // return scrollTable;

    public BlockTable(List<? extends Block> blocks) {
        this.blocks = blocks;
        this.setSkin(GameConfiguration.getSkin());

        Table contents = new Table(GameConfiguration.getSkin());
        for (Block b : this.blocks) {
            contents.add(b).top();
            contents.row();
        }
        this.setFillParent(false);
        ScrollPane scroller = new ScrollPane(this, GameConfiguration.getSkin());
        scroller.setFadeScrollBars(false);
        this.add(scroller).width(350f).expandY();
    }
}
