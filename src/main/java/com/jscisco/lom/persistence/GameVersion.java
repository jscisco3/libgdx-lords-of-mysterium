package com.jscisco.lom.persistence;

public class GameVersion {
    private final String version;

    private GameVersion(String version) {
        this.version = version;
    }

    public static GameVersion of(String version) {
        return new GameVersion(version);
    }

    public String getVersion() {
        return version;
    }

    @Override
    public String toString() { return "GameVersion{" +
                "version='" + version + '\'' +
                '}';
    }
}
