package com.app.ipl.service.impl;

import com.app.ipl.Exception.TeamNotFoundException;
import com.app.ipl.dto.MatchDto;
import com.app.ipl.dto.TeamDto;
import com.app.ipl.model.Team;
import com.app.ipl.repository.MatchesRepository;
import com.app.ipl.repository.TeamRepository;
import com.app.ipl.service.MatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * @Author saurabh vaish
 * @Date 05-07-2022
 */

@Log4j2
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MatchServiceImpl implements MatchService {

    private final MatchesRepository matchesRepository;

    private final TeamRepository teamRepository;

    @Override
    public TeamDto getTeamByName(String name) {
        Team team = teamRepository.findByTeamNameIgnoreCase(name).orElseThrow(()->new TeamNotFoundException("No team found with given name"));
        return new TeamDto(team,List.of());
    }

    @Override
    public List<TeamDto> getTeamByNameLike(String name, Optional<Pageable> pageable) {
        if(!StringUtils.hasText(name))throw new TeamNotFoundException("Invalid Team Name");
        return this.teamRepository.findByTeamNameContainingIgnoreCase(name.trim(),pageable.orElse(PageRequest.of(0,4, Sort.by(Sort.Direction.DESC,"teamName")))).stream().map(TeamDto::new).toList();
    }

    @Override
    public TeamDto getTeamByNameWithMatchDetails(String name) {
        Team team = teamRepository.findByTeamNameIgnoreCase(name).orElseThrow(()->new TeamNotFoundException("No team found with given name"));
        List<MatchDto> matches = this.matchesRepository.findAllByTeamName(team.getTeamName(), PageRequest.of(0, 4,Sort.by(Sort.Direction.DESC,"matchDate"))).stream().map(m->m.getDto(m)).toList();
        return new TeamDto(team,matches);
    }

    @Override
    public List<TeamDto> searchTeamByNameWithMatchDetails(String name, Optional<Pageable> pageable) {
        if(!StringUtils.hasText(name))throw new TeamNotFoundException("Invalid Team Name");
        List<Team> list = this.teamRepository.findByTeamNameContainingIgnoreCase(name.trim(),pageable.orElse(PageRequest.of(0,10,Sort.by(Sort.Direction.DESC,"teamName"))));
        return list.stream().map(t->{
            List<MatchDto> matches = this.matchesRepository.findAllByTeamName(t.getTeamName(), PageRequest.of(0, 4)).stream().map(m->m.getDto(m)).toList();
            return new TeamDto(t,matches);
        }).toList();
    }

    @Override
    public List<MatchDto> getTeamMatchesByNameAndYear(String name, int year, Optional<Pageable> pageable) {
        if(!StringUtils.hasText(name))throw new TeamNotFoundException("Invalid Team Name");
        return this.matchesRepository.findByTeamNameAndYear(name.trim(),year,pageable.orElse(PageRequest.of(0,20,Sort.by(Sort.Direction.DESC,"matchDate")))).stream().map(m->m.getDto(m)).toList();
    }

    @Override
    public List<TeamDto> getAllTeams(Optional<Pageable> pageable) {
//        return this.teamRepository.findAll(pageable.orElse(PageRequest.of(0,20,Sort.by(Sort.Direction.DESC,"teamName")))).stream().map(TeamDto::new).toList();
        return this.teamRepository.findAll().stream().map(TeamDto::new).toList();
    }


}
