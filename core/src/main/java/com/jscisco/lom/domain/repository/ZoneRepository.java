package com.jscisco.lom.domain.repository;

import com.jscisco.lom.domain.zone.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {

    @Query(value = "VALUES NEXT VALUE FOR level_sequence", nativeQuery = true)
    Long nextLevelId();

}
