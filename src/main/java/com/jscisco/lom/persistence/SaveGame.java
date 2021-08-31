package com.jscisco.lom.persistence;

import java.time.LocalDateTime;

/**
 * This represents the metadata for a saved game. It contains the bare minimum information used to persist a full game
 */
public class SaveGame {
    private LocalDateTime lastPlayed;
    private GameVersion gameVersion;

    public SaveGame(GameVersion gameVersion) {
        this.gameVersion = gameVersion;
    }

    public LocalDateTime getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(LocalDateTime lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    public GameVersion getGameVersion() {
        return gameVersion;
    }

    public void setGameVersion(GameVersion gameVersion) {
        this.gameVersion = gameVersion;
    }

    @Override
    public String toString() {
        return "SaveGame{" +
                "lastPlayed=" + lastPlayed +
                ", gameVersion=" + gameVersion +
                '}';
    }
}
