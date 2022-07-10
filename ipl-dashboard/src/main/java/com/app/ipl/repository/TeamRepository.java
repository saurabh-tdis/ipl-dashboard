package com.app.ipl.repository;

import com.app.ipl.model.Team;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByTeamNameIgnoreCase(String name);

    List<Team> findByTeamNameContainingIgnoreCase(String name);
    List<Team> findByTeamNameContainingIgnoreCase(String name, Pageable pageable);

}