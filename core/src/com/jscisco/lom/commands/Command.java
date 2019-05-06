package com.jscisco.lom.commands;

import com.jscisco.lom.actor.Actor;

public interface Command {
    void invoke(Actor actor);
}
