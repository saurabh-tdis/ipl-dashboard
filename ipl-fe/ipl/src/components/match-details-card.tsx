import React from "react";
import {Link} from "react-router-dom";
import {Match} from "../models/match.modal";

export const MatchDetailsCard=({teamName,matchDetails}) => {
  const match: Match=matchDetails;
  if(match==null) return null;
  const otherTeam=teamName===match.firstTeam? match.secondTeam:match.firstTeam;
  const isWon=teamName===match.winnerTeam;

  return (
    <div className={isWon? 'MatchDetailsCard won-card':'MatchDetailsCard lost-card'}>
      <div>
        <span className="vs">vs</span>
        <h1><Link to={'/teams/'+otherTeam}>{otherTeam}</Link></h1>
        <h2 className="match-date">{match.matchDate}</h2>
        <h3 className="match-venue">at {match.venue}</h3>
        <h3 className="match-result">{match.winnerTeam} won by {match.resultMargin} {match.result} </h3>
      </div>

      <div className="additional-detail">
        <h3>First Innings</h3>
        <p>{match.firstTeam}</p>
        <h3>Second Innings</h3>
        <p>{match.secondTeam}</p>
        <h3>Man of the match</h3>
        <p>{match.manOfTheMatch}</p>
        <h3>Umpires</h3>
        <p>{match.firstUmpire}, {match.secondUmpire}</p>
      </div>
        
    </div>
  );
}
