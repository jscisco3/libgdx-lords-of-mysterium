package com.jscisco.lom.shelf.trigger;

import com.jscisco.lom.shelf.effect.Effect;

import java.util.List;

public class WhenDamagedTrigger extends AbstractTrigger {

    public WhenDamagedTrigger(List<Effect> sourceEffects, List<Effect> receiverEffects) {
        super(sourceEffects, receiverEffects);
    }

}
