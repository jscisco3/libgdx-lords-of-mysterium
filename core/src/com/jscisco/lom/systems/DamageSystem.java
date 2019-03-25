package com.jscisco.lom.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.jscisco.lom.components.DamageComponent;
import com.jscisco.lom.components.HealthComponent;
import com.jscisco.lom.components.flags.Destroy;

public class DamageSystem extends IteratingSystem {

    private ComponentMapper<HealthComponent> mHealth;
    private ComponentMapper<DamageComponent> mDamage;
    private ComponentMapper<Destroy> mDestroy;

    public DamageSystem() {
        super(Aspect.all(DamageComponent.class, HealthComponent.class));
    }

    @Override
    protected void process(int entityId) {
        mHealth.get(entityId).hp -= mDamage.get(entityId).damage;
        if (mHealth.get(entityId).hp <= 0) {
            mDestroy.create(entityId);
        }
        mDamage.remove(entityId);
    }
}
