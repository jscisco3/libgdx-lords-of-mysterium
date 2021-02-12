package com.jscisco.lom.domain;

import java.util.ArrayList;
import java.util.List;

public class Stage {

    private List<Actor> actors = new ArrayList<>();
    private int currentActorIndex = 0;

    public Stage() {
        this.actors.add(new Player.Builder()
                .withName(ActorName.of("Johnny"))
                .withPosition(Position.of(1, 1))
                .build());
    }

    /**
     * Process actions from the actors in the current stage
     */
    public void process() {
        Command command = actors.get(currentActorIndex).command;
        // No action, so skip
        if (command == null) {
            return;
        }
        command.execute();
        currentActorIndex = (currentActorIndex + 1) % actors.size();
    }

}
