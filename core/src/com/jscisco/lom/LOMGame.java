package com.jscisco.lom;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.entity.NPCRepository;
import com.jscisco.lom.entity.Player;
import com.jscisco.lom.entity.PlayerFactory;
import com.jscisco.lom.items.ItemRepository;
import com.jscisco.lom.screens.ScreenManager;
import com.jscisco.lom.screens.ZoneScreen;
import com.jscisco.lom.screens.kingdom.HireHeroScreen;
import com.jscisco.lom.states.State;
import com.jscisco.lom.zone.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidmath.RNG;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LOMGame extends Game {

    private Logger logger = LoggerFactory.getLogger(LOMGame.class);
    private ZoneScreen zoneScreen;
    private ScreenManager screenManager;
    private Deque<State> stateManager;

    public static final RNG rng = new RNG(0xDEADBEEF);

    public LOMGame() {
        this.screenManager = new ScreenManager(this);
        this.stateManager = new ArrayDeque<>();
    }

    @Override
    public void create() {
        Assets.load();
        ItemRepository.load();
        NPCRepository.load();

        Zone zone = null;
        if (zone != null) {
            this.screenManager.pushScreen(new ZoneScreen(this, zone));
        } else {
            List<Player> heroes = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                heroes.add(PlayerFactory.createRandomHero());
            }
            this.screenManager.pushScreen(new HireHeroScreen(this, heroes));
        }

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        Assets.dispose();
    }

    public ScreenManager getScreenManager() {
        return screenManager;
    }

    public void popState() {
        if (stateManager.size() > 1) {
            this.stateManager.removeFirst();
        }
    }

    public void pushState(State state) {
        this.stateManager.push(state);
    }

    public State getCurrentState() {
        return stateManager.peekFirst();
    }


    public Deque<State> getStateManager() {
        return stateManager;
    }
}
