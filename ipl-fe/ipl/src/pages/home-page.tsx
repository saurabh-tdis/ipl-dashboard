import React,{useEffect,useState} from 'react';
import {TeamTile} from '../components/team-tiles';
import {AppResponse} from '../models/app-response.modal';

import {TeamModal} from '../models/team.modal';

export const HomePage=() => {
  
  const [ teams,setTeams ]=useState<TeamModal[]>([]);
  const [ isLoading,setLoading ]=useState(false);
  // const url= process.env.REACT_APP_API_ROOT_URL;

    // will execute when something changes
  useEffect(
      () => {
        fetchTeams();
      }, [] // calling on [] so that it can only load once while page loading
  );
  
  const fetchTeams= () => {
    setLoading(true)
    fetch(`${process.env.REACT_APP_API_ROOT_URL}/api/all-teams`)
      .then(res => res.json())
      .then((data: AppResponse<TeamModal[]>) => {
        if(data.status==='OK') {
          setTeams(data.payload); // setting team data to team var using set team state
        } else {
          setTeams([])
        }
        setLoading(false);
      })
      .catch(err => {console.log(err); setLoading(false);})
  };

  
  return (
    <>{isLoading? <div>Data loading</div>
      :(

        <div className="HomePage">
            <div className="team-grid">
                { teams.map(team => <TeamTile key={team.id} teamName={team.teamName} />)}
            </div>
        </div>
      )}
    </>
  );
}

