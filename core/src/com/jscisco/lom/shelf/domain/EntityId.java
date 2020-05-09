package com.jscisco.lom.shelf.domain;

import java.util.Objects;

public final class EntityId {
    private Long id;

    public EntityId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }

    public Long id() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityId entityId = (EntityId) o;
        return Objects.equals(id, entityId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "EntityId{" +
                "id=" + id +
                '}';
    }
}
