import React from "react";
import {Link} from "react-router-dom";
import {Match} from "../models/match.modal";

export const MatchInfoCard=({teamName,matchDetails}) => {
  if(matchDetails==null) return null;
  const match:Match =matchDetails;
  const otherTeam=teamName===match.firstTeam? match.secondTeam:match.firstTeam;
  const isWon=teamName===match.winnerTeam;

  return (
    <div className={isWon ? 'MatchInfoCard won-card' : 'MatchInfoCard lost-card'}>
      <span className="vs">vs</span>
      <h1> <Link to={'/teams/'+otherTeam}>{otherTeam}</Link></h1>
      <p className="match-result">{match.winnerTeam} won by {match.resultMargin} {match.result} </p>
    </div>
  );
}