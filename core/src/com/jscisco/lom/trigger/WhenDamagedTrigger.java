package com.jscisco.lom.trigger;

import com.jscisco.lom.effect.Effect;

import java.util.List;

public class WhenDamagedTrigger extends AbstractTrigger {

    public WhenDamagedTrigger(List<Effect> sourceEffects, List<Effect> receiverEffects) {
        super(sourceEffects, receiverEffects);
    }

}
