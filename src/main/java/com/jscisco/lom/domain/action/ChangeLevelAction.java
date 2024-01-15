package com.jscisco.lom.domain.action;

import com.jscisco.lom.application.ServiceLocator;
import com.jscisco.lom.services.ZoneService;
import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.event.HeroChangedLevelEvent;
import com.jscisco.lom.domain.zone.LevelChange;
import com.jscisco.lom.domain.zone.Tile;

public class ChangeLevelAction extends Action {

    private final ZoneService zoneService;

    public ChangeLevelAction(Entity source) {
        super(source);
        this.zoneService = ServiceLocator.getBean(ZoneService.class);
    }

    @Override
    public ActionResult execute() {
        Tile t = source.getLevel().getTile(source.getPosition());
        if (t.getFeature() instanceof LevelChange) {
            LevelChange feature = (LevelChange) t.getFeature();
            // zoneService.changeLevel(source, source.getLevel().getZone(), feature.getToLevelId(),
            // feature.getToPosition());
            if (source instanceof Hero) {
                source.getSubject().notify(new HeroChangedLevelEvent());
            }
            return ActionResult.succeeded();
        }
        return ActionResult.failed();
    }
}
