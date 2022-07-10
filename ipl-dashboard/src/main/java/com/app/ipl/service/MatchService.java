package com.app.ipl.service;

import com.app.ipl.dto.MatchDto;
import com.app.ipl.dto.TeamDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @Author saurabh vaish
 * @Date 05-07-2022
 */
public interface MatchService {

    TeamDto getTeamByName(String name);

    List<TeamDto> getTeamByNameLike(String name, Optional<Pageable> pageable);

    TeamDto getTeamByNameWithMatchDetails(String name);

    List<TeamDto> searchTeamByNameWithMatchDetails(String name, Optional<Pageable> pageable);

    List<MatchDto> getTeamMatchesByNameAndYear(String name, int year, Optional<Pageable> pageable);

    List<TeamDto> getAllTeams(Optional<Pageable> pageable);
}
