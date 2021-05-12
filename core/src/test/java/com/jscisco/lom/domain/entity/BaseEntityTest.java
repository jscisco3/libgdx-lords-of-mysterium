package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.action.Action;
import fixtures.EntityFactory;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseEntityTest {

    protected Entity e;

    @BeforeEach
    public void setup() {
        e = EntityFactory.testHero();
    }

}
