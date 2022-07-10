package com.app.ipl.dto;

import com.app.ipl.model.Team;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author saurabh vaish
 * @Date 05-07-2022
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TeamDto {

    private Long id;

    private String teamName;

    private Long totalMatches;

    private Long totalWins;

    private List<MatchDto> latestMatches = new ArrayList<>();

    public TeamDto(Team team,List<MatchDto> matchDtos){
       this.getDto(team, matchDtos);
    }

    public TeamDto(Team team){
        this.getDto(team,List.of());
    }

    private void getDto(Team team,List<MatchDto> matchDtos){
        if(team==null) return;
        this.setId(team.getId());
        this.setTeamName(team.getTeamName());
        this.setTotalMatches(team.getTotalMatches());
        this.setTotalWins(team.getTotalWins());
        this.setLatestMatches(matchDtos);
    }

}
