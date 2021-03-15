package com.jscisco.lom.application.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jscisco.lom.application.GameConfiguration;
import com.jscisco.lom.domain.Observer;
import com.jscisco.lom.domain.action.DropItemAction;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.event.Event;
import com.jscisco.lom.domain.item.Item;

public class InventoryWindow extends Window implements Observer {
    private final Hero hero;
    private final Table content;
    private final ScrollPane scroller;
    private final InputMultiplexer previousInput;

    public InventoryWindow(String title, Hero hero, InputMultiplexer previousInput) {
        super(title, GameConfiguration.getSkin());
        this.hero = hero;
        this.hero.getSubject().register(this);
        this.previousInput = previousInput;
        TextButton close = new TextButton("X", GameConfiguration.getSkin(), "default");
        close.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setVisible(false);
            }
        });
        getTitleTable().add(close).size(38, 38).padRight(10).padTop(0);

        content = new Table(this.getSkin());
        scroller = new ScrollPane(content);
        scroller.setFadeScrollBars(false);

        setContent();

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

    @Override
    public void onNotify(Event event) {
        content.clear();
        setContent();
    }

    private void setContent() {
        for (Item i : hero.getInventory().getItems()) {
            ItemBlock block = new ItemBlock(i);
            block.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    DropItemAction action = new DropItemAction(hero, block.getItem());
                    action.execute();
                }
            });
            content.add(block);
            content.row();
        }
    }
}
