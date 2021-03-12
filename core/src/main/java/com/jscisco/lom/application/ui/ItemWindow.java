package com.jscisco.lom.application.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jscisco.lom.application.GameConfiguration;
import com.jscisco.lom.domain.action.DropItemAction;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.item.Item;

import java.util.List;

public class ItemWindow extends Window {
    private final Hero hero;
    private final List<Item> items;
    private final TextButton close;
    private final ScrollPane scroller;
    private final InputMultiplexer previousInput;

    public ItemWindow(String title, Hero hero, List<Item> items, InputMultiplexer previousInput) {
        super(title, GameConfiguration.getSkin());
        this.hero = hero;
        this.previousInput = previousInput;
        this.items = items;
        this.close = new TextButton("X", GameConfiguration.getSkin(), "default");
        close.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setVisible(false);
            }
        });
        getTitleTable().add(close).size(38, 38).padRight(10).padTop(0);

        // TODO: Scrollable
        Table content = new Table(this.getSkin());
        scroller = new ScrollPane(content);
        scroller.setFadeScrollBars(false);

        for (Item i : items) {
            ItemBlock block = new ItemBlock(i);
            block.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    hero.setAction(new DropItemAction(hero, block.getItem()));
                }
            });
            content.add(block);
            content.row();
        }

        this.add(scroller).fill().expand();
        this.pack();
        this.setMovable(false);
    }

    public ScrollPane getScroller() {
        return scroller;
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (!visible) {
            Gdx.input.setInputProcessor(previousInput);
        }
    }
}
