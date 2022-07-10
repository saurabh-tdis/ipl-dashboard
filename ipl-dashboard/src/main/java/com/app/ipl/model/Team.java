package com.app.ipl.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Author saurabh vaish
 * @Date 04-07-2022
 */


@ToString
@Entity
@Table(name = "team", indexes = {
        @Index(name = "idx_team_id_teamname_unq", columnList = "id, teamName", unique = true)
})
@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String teamName;

    private Long totalMatches;

    private Long totalWins;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return getId().equals(team.getId()) && getTeamName().equals(team.getTeamName()) && getTotalMatches().equals(team.getTotalMatches()) && getTotalWins().equals(team.getTotalWins());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTeamName(), getTotalMatches(), getTotalWins());
    }
}
