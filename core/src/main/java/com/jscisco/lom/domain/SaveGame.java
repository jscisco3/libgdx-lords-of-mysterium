package com.jscisco.lom.domain;

import com.jscisco.lom.domain.kingdom.Kingdom;
import com.jscisco.lom.domain.zone.Zone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "games")
public class SaveGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The kingdom you are part of
     */
    @OneToOne(mappedBy = "saveGame", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private Kingdom kingdom;

    /**
     * Last time you played this particular kingdom
     */
    @Column(name = "last_played")
    private LocalDateTime lastPlayed;

    /**
     * The zones you have been to, in general.
     */
    @ElementCollection
    private List<Long> zones = new ArrayList<>();

    /**
     * Optional current level - if it is present, load into that level.
     * Otherwise, load into the kingdom(?)
     */
    private UUID levelId;

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

    public List<Long> getZones() {
        return zones;
    }

    public void setZones(List<Long> zones) {
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
