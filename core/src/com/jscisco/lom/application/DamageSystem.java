package com.jscisco.lom.application;

import com.jscisco.lom.domain.event.DamageEvent;
import com.jscisco.lom.domain.event.Event;
import com.jscisco.lom.domain.event.Listener;

public class DamageSystem implements Listener {

    @Override
    public void handle(Event event) {
        if (event instanceof DamageEvent) {
            handleDamageEvent((DamageEvent) event);
        }
    }

    private void handleDamageEvent(DamageEvent event) {
        // TODO: Calculate resistances
        event.getTarget().getHealth().decrease(event.getDamage().getDamage());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
