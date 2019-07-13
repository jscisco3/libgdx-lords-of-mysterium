package com.jscisco.lom.ai.goap;

import com.jscisco.lom.action.Action;
import com.jscisco.lom.attic.goap.actions.GOAPGoal;
import com.jscisco.lom.entity.NPC;
import com.jscisco.lom.entity.Player;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Stage;
import com.jscisco.lom.zone.StageImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TestGOAPAgent {

    private static final Logger logger = LoggerFactory.getLogger(TestGOAPAgent.class);

    private NPC TEST_NPC;

    @BeforeEach
    void setUp() {
        Stage stage = new StageImpl(20, 20);
        this.TEST_NPC = new NPC(stage, null, new Position(5, 5));
        Player player = new Player(stage, new Position(4, 10), 10.0f);

        stage.addEntity(TEST_NPC);
        stage.addEntity(player);

        // NPC needs to get in position
        this.TEST_NPC.setGoal(GOAPGoal.IN_POSITION, true);
        // NPC needs target
        this.TEST_NPC.updateWorldState(GOAPGoal.NEEDS_TARGET, true);
        this.TEST_NPC.updateWorldState(GOAPGoal.HAS_TARGET, false);
        // The NPC is not in position
        this.TEST_NPC.updateWorldState(GOAPGoal.IN_POSITION, false);
    }

    @Disabled
    @Test
    void test() {
        for (int i = 0; i < 30; i++) {
//            this.TEST_NPC.getAgent().update();
            Action action = this.TEST_NPC.getNextAction();
            if (action != null) {
                action.invoke();
            }
        }
    }

}
