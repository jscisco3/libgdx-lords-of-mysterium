package com.jscisco.lom.application.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jscisco.lom.application.configuration.GameConfiguration;
import com.jscisco.lom.domain.GameLog;
import com.jscisco.lom.domain.Observer;
import com.jscisco.lom.domain.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Fix the layout here so that messages are displayed correctly.
// TODO: Fix scrolling
public class GameLogUI extends Table implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(GameLogUI.class);
    private final GameLog gameLog;
    protected final Table content;
    protected final ScrollPane scroller;

    public GameLogUI(GameLog gameLog, float x, float y, float width, float height, Color color) {
        this.gameLog = gameLog;
        this.gameLog.getSubject().register(this);
        this.addActor(new Rectangle(0, 0, width, height, color));
        this.content = new Table(GameConfiguration.getSkin());
        this.content.setFillParent(true);
        this.scroller = new ScrollPane(content);
        this.add(scroller).fill().expand();

//        this.add(this.content).left().top().fillX().expandX();
    }

    @Override
    public void onNotify(Event event) {
        this.content.clear();
        for (String s : this.gameLog.getLogs()) {
            // TODO: Text wrapping
            this.content.add(new Label(s, GameConfiguration.getSkin(), "default")).top().left().fill().expandX();
            this.content.row();
        }
    }
}
