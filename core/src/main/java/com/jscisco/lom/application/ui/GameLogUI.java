package com.jscisco.lom.application.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jscisco.lom.application.GameConfiguration;
import com.jscisco.lom.domain.GameLog;
import com.jscisco.lom.domain.Observer;
import com.jscisco.lom.domain.event.Event;

public class GameLogUI extends Table implements Observer {

    private final GameLog gameLog;
    protected final Table content;
    protected final ScrollPane scroller;

    public GameLogUI(GameLog gameLog, float x, float y, float width, float height, Color color) {
        this.gameLog = gameLog;
        this.gameLog.getSubject().register(this);
        this.addActor(new Rectangle(x, y, width, height, color));
        this.content = new Table(GameConfiguration.getSkin());
        this.scroller = new ScrollPane(content);

        this.add(this.content);
    }

    @Override
    public void onNotify(Event event) {
        this.content.clear();
        for (String s : this.gameLog.getLogs(5)) {
            // TODO: Text wrapping
            this.content.add(new Label(s, GameConfiguration.getSkin(), "default"));
            this.content.row();
        }
    }
}
