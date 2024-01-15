package com.jscisco.lom.domain.entity;

import com.jscisco.lom.ai.WanderAIController;
import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.Pool;
import com.jscisco.lom.domain.Pools;

public class EntityFactory {

    public static Hero player() {
        return new Hero.Builder()
                .withName(Name.of("Player"))
                .withGlyph(Assets.warrior)
                .withPools(new Pools(new Pool(50), new Pool(25), 0, 1))
                .build();
    }

}
