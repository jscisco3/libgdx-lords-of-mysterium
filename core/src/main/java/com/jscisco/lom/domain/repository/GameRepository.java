package com.jscisco.lom.domain.repository;

import com.jscisco.lom.domain.SaveGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<SaveGame, Long> {

}
