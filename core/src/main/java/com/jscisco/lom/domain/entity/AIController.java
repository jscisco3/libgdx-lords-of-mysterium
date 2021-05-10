package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.action.Action;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@javax.persistence.Entity
public abstract class AIController {

    @Id
    @GeneratedValue
    @Column(name = "entity_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "entity_id")
    protected Entity entity;

    public AIController() {}

    public AIController(Entity entity) {
        this.entity = entity;
    }

    public abstract Action getNextAction();

}
