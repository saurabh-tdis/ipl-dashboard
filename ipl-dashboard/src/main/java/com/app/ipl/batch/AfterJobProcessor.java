package com.app.ipl.batch;

import com.app.ipl.model.Team;
import com.app.ipl.repository.MatchesRepository;
import com.app.ipl.repository.TeamRepository;
import com.app.ipl.repository.projection.TeamNameAndCount;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author saurabh vaish
 * @Date 04-07-2022
 */

@Log4j2
@Service
public class AfterJobProcessor {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchesRepository matchesRepository;

    @Transactional(readOnly = true)
    public Long getTotalMatches(){
        return this.matchesRepository.count();
    }

    @Transactional(readOnly = true)
    public List<String> getUniqueTeamNames(){
        return this.matchesRepository.getAllUniqueTeamNames();
    }

    @Transactional(readOnly = true)
    public Map<String,Long> getTotalMatchesByTeam(){
        List<TeamNameAndCount> firstTeamTotal = this.matchesRepository.findAllTeamNameGroupByFirstTeam();
        List<TeamNameAndCount> secondTeamTotal = this.matchesRepository.findAllTeamNameGroupBySecondTeam();

        Map<String,Long> map = new HashMap<>();

        firstTeamTotal.forEach(t->map.put(t.getTeamName(),t.getTotal()));
        secondTeamTotal.forEach(t->{
            map.putIfAbsent(t.getTeamName(),t.getTotal());
            map.computeIfPresent(t.getTeamName(),(k,v)->v+t.getTotal());
        });

        return map;
    }

    @Transactional(readOnly = true)
    public Map<String,Long> getTotalMatchWinsByTeam(){
        return this.matchesRepository.findAllTeamNameGroupByWinnerTeam().stream().collect(Collectors.toMap(TeamNameAndCount::getTeamName, TeamNameAndCount::getTotal));
    }

    @Transactional
    public void saveTeamRecords(){

        Map<String, Long> totalMatchesByTeam = this.getTotalMatchesByTeam();

        Map<String, Long> winsByTeam = this.getTotalMatchWinsByTeam();

        List<Team> teamList = new ArrayList<>();

        totalMatchesByTeam.forEach((k,v)->{
            Team team = Team.builder()
                    .teamName(k)
                    .totalMatches(v)
                    .totalWins(winsByTeam.getOrDefault(k,0L))
                    .build();
            teamList.add(team);
        });

        if(!teamList.isEmpty())
            this.teamRepository.saveAll(teamList);
    }

    @Transactional(readOnly = true)
    public Long getTotalTeams(){
        return this.teamRepository.count();
    }
}
