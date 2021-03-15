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
import com.jscisco.lom.domain.action.ActionResult;
import com.jscisco.lom.domain.action.PickUpItemAction;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PickupItemWindow extends Window {

    private static final Logger logger = LoggerFactory.getLogger(PickupItemWindow.class);

    private Hero hero;
    private List<Item> items;
    private final Table content;
    private final ScrollPane scroller;
    private final InputMultiplexer previousInput;

    public PickupItemWindow(Hero hero, List<Item> items, InputMultiplexer previousInput) {
        super("Items", GameConfiguration.getSkin());
        this.hero = hero;
        this.items = items;
        this.previousInput = previousInput;

        // TODO: abstract this to something?
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

    private void setContent() {
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
