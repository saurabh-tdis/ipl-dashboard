package com.app.ipl.dto;

import com.app.ipl.model.enums.CommonEnums;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @Author saurabh vaish
 * @Date 03-07-2022
 */

@ToString
@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MatchDto {

    private Long id;

    private String city;

    private LocalDate matchDate;

    private String manOfTheMatch;

    private String venue;

    private String firstTeam;

    private String secondTeam;

    private String tossWinner;

    private String tossDecision;

    private String winnerTeam;

    private String result;

    private Integer resultMargin;

    private Boolean eliminator;

    private String winningMethod;

    private String firstUmpire;

    private String secondUmpire;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchDto matches = (MatchDto) o;
        return getId().equals(matches.getId()) && getCity().equals(matches.getCity()) && getMatchDate().equals(matches.getMatchDate()) && getFirstTeam().equals(matches.getFirstTeam()) && getSecondTeam().equals(matches.getSecondTeam()) && getWinnerTeam().equals(matches.getWinnerTeam()) && getResult() == matches.getResult();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCity(), getMatchDate(), getFirstTeam(), getSecondTeam(), getWinnerTeam(), getResult());
    }


}
