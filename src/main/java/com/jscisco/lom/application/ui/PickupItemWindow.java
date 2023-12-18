package com.jscisco.lom.application.ui;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jscisco.lom.application.configuration.GameConfiguration;
import com.jscisco.lom.domain.action.ActionResult;
import com.jscisco.lom.domain.action.PickUpItemAction;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PickupItemWindow extends PopupWindow {

    private static final Logger logger = LoggerFactory.getLogger(PickupItemWindow.class);

    private Hero hero;
    private List<Item> items;

    public PickupItemWindow(Hero hero, List<Item> items, InputMultiplexer previousInput) {
        super("Items", GameConfiguration.getSkin(), previousInput);
        this.hero = hero;
        this.items = items;

        scroller.setFadeScrollBars(false);

        setContent();

        this.add(scroller).fill().expand();
        this.pack();
        this.setMovable(false);
    }

    protected void setContent() {
        for (Item i : items) {
            ItemBlock block = new ItemBlock(i);
            block.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    PickUpItemAction action = new PickUpItemAction(hero, block.getItem());
                    ActionResult result = action.execute();
                    if (result.success()) {
                        items.remove(block.getItem());
                        content.clear();
                        setContent();
                    }
                }
            });
            content.add(block);
            content.row();
        }
    }
}
