package com.jscisco.lom.domain;

import com.jscisco.lom.domain.kingdom.Kingdom;
import com.jscisco.lom.domain.zone.Zone;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SaveGame {

    private Long id;

    /**
     * The kingdom you are part of
     */
    private Kingdom kingdom;

    /**
     * Last time you played this particular kingdom
     */
    private LocalDateTime lastPlayed;

    /**
     * The zones you have been to, in general.
     */
    private List<UUID> zones = new ArrayList<UUID>();

    /**
     * Optional current level - if it is present, load into that level.
     * Otherwise, load into the kingdom(?)
     */
    private UUID levelId;
    /**
     * Optional current zone - if it is present, load into this zone.
     * Then we will get the level from that zone and set it for the game screen.
     */
    private UUID zoneId;

    public SaveGame() {
        this.lastPlayed = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public void setKingdom(Kingdom kingdom) {
        this.kingdom = kingdom;
        this.kingdom.setSaveGame(this);
    }

    public LocalDateTime getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(LocalDateTime lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    public List<UUID> getZones() {
        return zones;
    }

    public void setZones(List<UUID> zones) {
        this.zones = zones;
    }

    public void addZone(Zone zone) {
        this.zones.add(zone.getId());
    }

    public UUID getLevelId() {
        return levelId;
    }

    public void setLevelId(UUID levelId) {
        this.levelId = levelId;
    }

    public UUID getZoneId() {
        return zoneId;
    }

    public void setZoneId(UUID zoneId) {
        this.zoneId = zoneId;
    }

    @Override
    public String toString() {
        return "SaveGame{" +
                "id=" + id +
                ", kingdom=" + kingdom +
                ", lastPlayed=" + lastPlayed +
                ", levelId=" + levelId +
                '}';
    }
}
