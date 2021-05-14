package com.jscisco.lom.domain.entity;


import com.jscisco.lom.domain.item.Item;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
public class Inventory {

    @Id
    @Column(name = "entity_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "entity_id")
    private Entity entity;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
        item.setInventory(this);
    }

    public void removeItem(Item item) {
        items.remove(item);
        item.setInventory(null);
    }

    public List<Item> getItems() {
        return items;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

}
