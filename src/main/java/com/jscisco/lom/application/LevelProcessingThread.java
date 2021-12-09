package com.jscisco.lom.application;

import com.jscisco.lom.domain.zone.Level;

public class LevelProcessingThread implements Runnable {

    private Level level;
    private volatile boolean stop = false;

    public LevelProcessingThread(Level level) {
        this.level = level;
    }

    @Override
    public void run() {
        while (!stop) {
            level.process();
        }
    }

    public void stop() {
        this.stop = true;
    }
}
