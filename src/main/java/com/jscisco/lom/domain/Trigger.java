package com.jscisco.lom.domain;

import com.jscisco.lom.domain.event.Handler;

import java.util.UUID;

public class Trigger {

    // Identifier so we can save the trigger
    UUID id = UUID.randomUUID();

    // Descriptive information
    Name name;
    Description description;

    /**
     * This is the event handler. It is actually what controls the trigger.
     *
     * So for example, we may have a trigger:
     *  When {Entity} is hit with a melee attack
     *  The {Attacker} is poisoned for 5 turns, 2 damage per turn
     *
     *  When {An enemy} enters line of sight
     *  {Trigger haver} gains 2 ev for 3 turns
     *
     *  When {an entity}
     *
     */
    Handler handler;

}
