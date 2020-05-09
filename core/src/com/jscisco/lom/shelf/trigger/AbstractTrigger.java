package com.jscisco.lom.shelf.trigger;

import com.jscisco.lom.shelf.effect.Effect;
import com.jscisco.lom.shelf.entity.Entity;
import com.jscisco.lom.shelf.zone.Stage;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTrigger {

    protected List<Effect> sourceEffects;
    protected List<Effect> receiverEffects;

    public AbstractTrigger() {
        this.sourceEffects = new ArrayList<>();
        this.receiverEffects = new ArrayList<>();
    }

    public AbstractTrigger(List<Effect> sourceEffects, List<Effect> receiverEffects) {
        this.sourceEffects = sourceEffects;
        this.receiverEffects = receiverEffects;
    }

    public void trigger(Entity source, Entity receiver, Stage stage) {
        if (source != null) {
            sourceEffects.forEach(source::applyEffect);
        }
        if (receiver != null) {
            receiverEffects.forEach(receiver::applyEffect);
        }
        if (stage != null) {
            //TODO
        }
    }

}
