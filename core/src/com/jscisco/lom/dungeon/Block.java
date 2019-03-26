package com.jscisco.lom.dungeon;

public class Block {

    private boolean seen;
    private boolean inFov;

    public Block() {
        this.seen = false;
        this.inFov = false;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public boolean isInFov() {
        return inFov;
    }

    public void setInFov(boolean inFov) {
        this.inFov = inFov;
    }
}
