package com.jscisco.lom.ai.goap;

import com.jscisco.lom.action.Action;
import com.jscisco.lom.attributes.ai.goap.actions.GOAPGoal;
import com.jscisco.lom.entity.NPC;
import com.jscisco.lom.entity.Player;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Stage;
import com.jscisco.lom.zone.StageImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestGOAPAgent {

    private static final Logger logger = LoggerFactory.getLogger(TestGOAPAgent.class);

    Stage stage;
    NPC TEST_NPC;
    Player player;

    @BeforeEach
    public void setUp() {
        this.stage = new StageImpl(20, 20);
        this.TEST_NPC = new NPC(stage, null, new Position(5, 5));
        this.player = new Player(stage, new Position(4, 10), 10.0f);

        this.stage.addEntity(TEST_NPC);
        this.stage.addEntity(player);

        this.TEST_NPC.setGoal(GOAPGoal.ATTACK_TARGET, true);
        this.TEST_NPC.updateWorldState(GOAPGoal.NEEDS_TARGET, true);

    }

    @Test
    public void test() {
        for (int i = 0; i < 5; i++) {
            Action nextAction = this.TEST_NPC.getNextAction();
            logger.info("Next Action: {}", nextAction);
        }
    }

}
