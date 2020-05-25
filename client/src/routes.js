import React from 'react';

import {BrowserRouter, Route, Switch, Redirect} from 'react-router-dom';

import Login   from './views/Login';

import Pedidos from './views/Pedidos';


const Routes = () =>(
    <BrowserRouter>
        <Switch>
            <Route exact path="/" component={() =><Login />} />
            <Route exact path="/produtos" component={() =><h1>Produtos</h1>} />
            <Route exact path="/pedidos" component={() =><Pedidos />} />
        </Switch>
    </BrowserRouter>
);

export default Routes;