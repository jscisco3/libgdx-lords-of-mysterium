package com.jscisco.lom.domain;

import com.jscisco.lom.domain.combat.Damage;

import javax.annotation.Nullable;
import java.util.Objects;

public abstract class GameObject {
    EntityName name;
    Position position;


    public void moveTo(Position position) {
        this.position = position;
    }

}
