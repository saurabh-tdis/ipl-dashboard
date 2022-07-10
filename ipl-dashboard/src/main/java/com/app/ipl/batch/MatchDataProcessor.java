package com.app.ipl.batch;

import com.app.ipl.Exception.TeamNotFoundException;
import com.app.ipl.model.Matches;
import com.app.ipl.model.enums.CommonEnums;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @Author saurabh vaish
 * @Date 03-07-2022
 */

@Log4j2
public class MatchDataProcessor implements ItemProcessor<MatchInputData, Matches> {

    @Override
    public Matches process(final MatchInputData input) throws Exception {

        log.info("Converting match input to match object having id {} ", input.getId());

        if(!StringUtils.hasText(input.getTeam1()) || !StringUtils.hasText(input.getTeam2()) )throw new TeamNotFoundException("Team name is not present");
        if(input.getTeam1().equals("N/A")|| input.getTeam2().equals("N/A") )throw new TeamNotFoundException("Team name is not present");

        Matches matches = Matches.builder()
                .city(this.removeNA(input.getCity()))
                .matchDate(LocalDate.parse(input.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .manOfTheMatch(removeNA(input.getPlayer_of_match()))
                .venue(removeNA(input.getVenue()))
                .firstTeam(input.getTeam1())
                .secondTeam(input.getTeam2())
                .tossWinner(removeNA(input.getToss_winner()))
                .tossDecision(CommonEnums.getValue(removeNA(input.getToss_decision())))
                .winnerTeam(removeNA(input.getWinner()))
                .result(CommonEnums.getValue(removeNA(input.getResult())))
                .resultMargin(Integer.valueOf(!input.getResult_margin().equals( "NA") ?input.getResult_margin():"0"))
                .eliminator(!input.getEliminator().equalsIgnoreCase("N"))
                .winningMethod(removeNA(input.getMethod()))
                .firstUmpire(removeNA(input.getUmpire1()))
                .secondUmpire(removeNA(input.getUmpire2()))
                .build();

        // improve logic here
        // check who won the toss
        // make first team to the team who won the task
        boolean isFirstTeamWon = matches.getTossWinner().equals(matches.getFirstTeam());
        if (!isFirstTeamWon) {
            String temp = matches.getFirstTeam();
            matches.setFirstTeam(matches.getSecondTeam());
            matches.setSecondTeam(temp);
        }

        return matches;
    }

    private String removeNA(String data){
        if(data==null)return "";
        if(data.equalsIgnoreCase("N/A") || data.equalsIgnoreCase("NA"))return "";
        return data;
    }
}
