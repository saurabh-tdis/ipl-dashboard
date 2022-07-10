import React,{useEffect,useState} from 'react';
import {useParams} from 'react-router-dom';
import {MatchDetailsCard} from '../components/match-details-card';
import {YearSelector} from '../components/year-selector';
import {AppResponse} from '../models/app-response.modal';
import {Match,MatchModal} from '../models/match.modal';


export const MatchPage=() => {
  
  const [ match,setMatch ]=useState<Match[]>([]);
  const [ isLoading,setLoading ]=useState(false);

  const {teamName,year}=useParams();

    // will execute when something changes
  useEffect(
      () => {
        // fetchMatches();
        setLoading(true)
        fetch(`${process.env.REACT_APP_API_ROOT_URL}/api/team/`+teamName+'/match/?year='+year)
         .then(res => res.json())
         .then((data: AppResponse<MatchModal[]>) => {
           if(data.status==='OK') {
             setMatch(data.payload); // setting team data to team var using set team state
           } else {
             setMatch([])
           }
           setLoading(false);
         })
         .catch(err => {console.log(err); setLoading(false);})
      }, [teamName,year] // calling on [] so that it can only load once while page loading
  );
  
  // const fetchMatches= () => {
   
  // };

  if(!match || !year)return <h1>Match Not Found</h1>
  
  return (
    <>{isLoading? <div>Data loading</div>
      :(
        <div className="MatchPage">
          <div className="year-selector">
            <h3> Select Year </h3>
            <YearSelector teamName={teamName} />
          </div>
          <div>
            <h1 className="page-heading">{teamName} matches in {year}</h1>
            {match.map(m => 
              // <h2>{m.firstTeam} vs {m.secondTeam}</h2>
            <MatchDetailsCard teamName={m.firstTeam} matchDetails={m} key={m.id} />)
            }
          </div>
        </div>
      )}
    </>
  );
}

