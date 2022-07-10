import React from 'react';
import {HashRouter,Route,Routes} from 'react-router-dom';
import './App.scss';
import {Header} from './components/header';
import {HomePage} from './pages/home-page';
import {MatchPage} from './pages/matchPage';
import {TeamPage} from './pages/team-page';

function App() {
  return (
    <div className="App">
      <HashRouter>
          <Header/>
        <Routes>
          <Route path='' element={<HomePage/>}  />
          <Route path='/teams/:teamName' element={<TeamPage/>} />
          <Route path='/teams/:teamName/matches/:year' element={<MatchPage/>} />
        </Routes>
      </HashRouter>
    </div>
  );
}

export default App;
