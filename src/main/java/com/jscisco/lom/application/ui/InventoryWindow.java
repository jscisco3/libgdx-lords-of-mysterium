package com.jscisco.lom.application.ui;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jscisco.lom.application.configuration.GameConfiguration;
import com.jscisco.lom.domain.Observer;
import com.jscisco.lom.domain.action.DropItemAction;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.event.Event;
import com.jscisco.lom.domain.item.Item;

public class InventoryWindow extends PopupWindow implements Observer {
    private final Hero hero;

    public InventoryWindow(String title, Hero hero, InputMultiplexer previousInput) {
        super(title, GameConfiguration.getSkin(), previousInput);
        this.hero = hero;
        this.hero.getSubject().register(this);

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
    public void onNotify(Event event) {
        content.clear();
        setContent();
    }

    protected void setContent() {
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
