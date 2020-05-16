import React from 'react';
import '../App/App.css';
import ManageApplicance from './ManageApplicance';
import CreateApplicance from './CreateApplicance';
import UpdateApplicance from './UpdateApplicance';
import DeleteApplicance from './DeleteApplicance';
import { Route, Switch } from 'react-router-dom';

function App() {
  return (
    <div className="App">
        <Switch>
        <Route path="/delete/:id" component={DeleteApplicance}></Route>
        <Route path="/update/:id" component={UpdateApplicance}></Route>
          <Route path="/create" component={CreateApplicance}></Route>
          <Route path="/" component={ManageApplicance}></Route>
        </Switch>
    </div>
  );
}

export default App;
