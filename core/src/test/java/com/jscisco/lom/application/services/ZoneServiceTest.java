package com.jscisco.lom.application.services;

import com.jscisco.lom.configuration.ApplicationConfiguration;
import com.jscisco.lom.configuration.PersistenceConfiguration;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.LevelGeneratorStrategy;
import com.jscisco.lom.domain.zone.Zone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class, PersistenceConfiguration.class})
public class ZoneServiceTest {

    @Autowired
    ZoneService zoneService;

    @Test
    public void able_to_create_a_zone() {
        Zone zone = zoneService.createZone();
        assertThat(zone.getId()).isEqualTo(1L);
    }

    @Test
    public void given_a_noneexistent_zone_when_i_create_a_level_it_fails() {
        assertThatThrownBy(() -> zoneService.createLevel(12345L, 100, 100, new LevelGeneratorStrategy.EmptyLevelStrategy()))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    public void given_an_existing_zone_when_i_create_a_level_it_is_persisted_correctly() {
        // Given
        Zone createdZone = zoneService.createZone();
        // When
        Level createdLevel = zoneService.createLevel(createdZone.getId(), 100, 100, new LevelGeneratorStrategy.EmptyLevelStrategy());
        // Then
        assertThat(createdLevel.getId()).isEqualTo(1L);
    }
}
