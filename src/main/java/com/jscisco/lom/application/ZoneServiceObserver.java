package com.jscisco.lom.application;

import com.jscisco.lom.application.services.ZoneService;
import com.jscisco.lom.domain.Observer;
import com.jscisco.lom.domain.event.Event;
import com.jscisco.lom.domain.event.level.LevelEvent;
import com.jscisco.lom.domain.event.level.TileExplored;

import java.util.ArrayList;
import java.util.List;

public class ZoneServiceObserver implements Observer {

    private final ZoneService zoneService;
    private final List<LevelEvent> eventList;

    public ZoneServiceObserver() {
        this.zoneService = ServiceLocator.getBean(ZoneService.class);
        this.eventList = new ArrayList<>();
    }

    @Override
    public void onNotify(Event event) {
        if (event instanceof TileExplored) {
            TileExplored te = (TileExplored) event;
            eventList.add(te);
            if (eventList.size() > 100) {
                // zoneService.saveLevelEvents(eventList);
                eventList.clear();
            }
        }
    }
}
