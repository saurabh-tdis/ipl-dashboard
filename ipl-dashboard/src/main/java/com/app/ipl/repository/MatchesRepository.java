package com.app.ipl.repository;

import com.app.ipl.dto.MatchDto;
import com.app.ipl.dto.TeamDto;
import com.app.ipl.model.Matches;
import com.app.ipl.repository.projection.TeamNameAndCount;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface MatchesRepository extends JpaRepository<Matches, Long> {

    @Query(value = "select distinct firstTeam from Matches union select distinct secondTeam from Matches",nativeQuery = true)
    List<String> getAllUniqueTeamNames();


    @Query("select firstTeam as teamName ,count(id) as total from Matches group by firstTeam")
    List<TeamNameAndCount> findAllTeamNameGroupByFirstTeam();
    @Query("select secondTeam as teamName ,count(id) as total from Matches group by secondTeam")
    List<TeamNameAndCount> findAllTeamNameGroupBySecondTeam();

    @Query("select winnerTeam as teamName ,count(id) as total from Matches group by winnerTeam")
    List<TeamNameAndCount> findAllTeamNameGroupByWinnerTeam();

    @Query("""
            select distinct m from Matches m
            where upper(m.firstTeam) = upper(?1) or upper(m.secondTeam) = upper(?1)
            order by m.matchDate DESC""")
    List<Matches> findAllByTeamName(String teamName, Pageable pageable);

    @Query("""
            select distinct m from Matches m
            where m.firstTeam in (?1) or m.secondTeam in (?1)
            order by m.matchDate DESC""")
    List<Matches> findAllByTeamNameIn(List<String> teamName, Pageable pageable);

    @Query("select m from Matches m where (m.firstTeam=?1 or m.secondTeam=?1) and year(m.matchDate)=?2 order by m.matchDate desc ")
    List<Matches> findByTeamNameAndYear(String name, int year, Pageable matchDate);
}

