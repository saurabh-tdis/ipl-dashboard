package com.app.ipl.model;

import com.app.ipl.dto.MatchDto;
import com.app.ipl.model.enums.CommonEnums;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.xml.transform.Result;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @Author saurabh vaish
 * @Date 03-07-2022
 */

@ToString
@Entity
@Table(name = "matches", indexes = {
        @Index(name = "idx_matches_id_city_matchdate", columnList = "id, city, matchDate, firstTeam, secondTeam, winnerTeam")
})
@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Matches {

    @Id
    @GeneratedValue
    private Long id;

    private String city;

    private LocalDate matchDate;

    private String manOfTheMatch;

    private String venue;

    private String firstTeam;

    private String secondTeam;

    private String tossWinner;

    @Enumerated(EnumType.STRING)
    private CommonEnums tossDecision;

    private String winnerTeam;

    @Enumerated(EnumType.STRING)
    private CommonEnums result;

    private Integer resultMargin;

    @ColumnDefault("false")
    private Boolean eliminator;

    private String winningMethod;

    private String firstUmpire;

    private String secondUmpire;


//    @Override
//    public int hashCode() {
//        int result1 = getId().hashCode();
//        result1 = 31 * result1 + getCity().hashCode();
//        result1 = 31 * result1 + getMatchDate().hashCode();
//        result1 = 31 * result1 + getFirstTeam().hashCode();
//        result1 = 31 * result1 + getSecondTeam().hashCode();
//        result1 = 31 * result1 + getWinnerTeam().hashCode();
//        result1 = 31 * result1 + getResult().hashCode();
//        return result1;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matches matches = (Matches) o;
        return getId().equals(matches.getId()) && getCity().equals(matches.getCity()) && getMatchDate().equals(matches.getMatchDate()) && getFirstTeam().equals(matches.getFirstTeam()) && getSecondTeam().equals(matches.getSecondTeam()) && getWinnerTeam().equals(matches.getWinnerTeam()) && getResult() == matches.getResult();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCity(), getMatchDate(), getFirstTeam(), getSecondTeam(), getWinnerTeam(), getResult());
    }

    public MatchDto getDto(Matches matches){
        MatchDto matchDto = new MatchDto();
        matchDto.setId(matches.getId());
        matchDto.setCity(matches.getCity());
        matchDto.setMatchDate(matches.getMatchDate());
        matchDto.setManOfTheMatch(matches.getManOfTheMatch());
        matchDto.setVenue(matches.getVenue());
        matchDto.setFirstTeam(matches.getFirstTeam());
        matchDto.setSecondTeam(matches.getSecondTeam());
        matchDto.setTossWinner(matches.getTossWinner());
        matchDto.setTossDecision(String.valueOf(matches.getTossDecision()));
        matchDto.setWinnerTeam(matches.getWinnerTeam());
        matchDto.setResult(String.valueOf(matches.getResult()));
        matchDto.setResultMargin(matches.getResultMargin());
        matchDto.setEliminator(matches.getEliminator());
        matchDto.setWinningMethod(matches.getWinningMethod());
        matchDto.setFirstUmpire(matches.getFirstUmpire());
        matchDto.setSecondUmpire(matches.getSecondUmpire());
        return matchDto;
    }


}
