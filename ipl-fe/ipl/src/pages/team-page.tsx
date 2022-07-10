import React,{useEffect,useState} from 'react';
import {Link,useParams} from 'react-router-dom';
import {MatchDetailsCard} from '../components/match-details-card';
import {MatchInfoCard} from '../components/match-info-card';
import {AppResponse} from '../models/app-response.modal';

import {PieChart} from 'react-minimal-pie-chart';
import {TeamModal} from '../models/team.modal';

export const TeamPage=() => {
  
  const [ team,setTeam ]=useState<TeamModal>(new TeamModal());
  const [ isLoading,setLoading ]=useState(false);

  const {teamName}=useParams();

    // will execute when something changes
  useEffect(
      () => {
        // fetchMatches();
        setLoading(true)
        fetch(`${process.env.REACT_APP_API_ROOT_URL}/api/team-with-match/`+teamName)
         .then(res => res.json())
         .then((data: AppResponse<TeamModal>) => {
           if(data.status==='OK') {
             setTeam(data.payload); // setting team data to team var using set team state
           } else {
             setTeam(new TeamModal())
           }
           setLoading(false);
         })
         .catch(err => {console.log(err); setLoading(false);})
      }, [teamName] // calling on [] so that it can only load once while page loading
  );
  
  // const fetchMatches= () => {
    
  // };

  if(!team || !teamName)return <h1>Team Not Found</h1>
  
  return (
    <>{isLoading? <div>Data loading</div>
      :(
        <div className="TeamPage">
          <div className="team-name-section">
              <h1 className="team-name">{team.teamName}</h1>
          </div>
          <div className="win-loss-section">
              Wins / Losses
              {<PieChart
                  data={[
                      { title: 'Losses', value: team.totalMatches - team.totalWins, color: '#a34d5d' },
                      { title: 'Wins', value: team.totalWins, color: '#4da375' },
                  ]}
                  /> }
          </div>
          <div className="match-detail-section">
              <h3>Latest Matches</h3>
              <MatchDetailsCard teamName={team.teamName} matchDetails={team.latestMatches[0]}/>
          </div>
          {team.latestMatches.slice(1).map(match => <MatchInfoCard key={match.id} teamName={team.teamName} matchDetails={match} />)}
          <div className="more-link">
          <Link to={`/teams/${teamName}/matches/${process.env.REACT_APP_DATA_END_YEAR}`}>More </Link>
          </div>
        </div>
      )}
    </>
  );
}

