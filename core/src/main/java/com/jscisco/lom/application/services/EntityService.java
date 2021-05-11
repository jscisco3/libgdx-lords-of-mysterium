package com.jscisco.lom.application.services;

import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntityService {

    final EntityRepository entityRepository;

    @Autowired
    public EntityService(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    public Entity createEntity(Entity e) {
        return this.entityRepository.save(e);
    }

    public void deleteEntity(Long entityId) {
        this.entityRepository.deleteById(entityId);
    }

}
