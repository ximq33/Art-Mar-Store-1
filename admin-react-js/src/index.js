import React from 'react';
import ReactDOM from 'react-dom';


import './i18n';

import App from './App';
import AppStore from "./AppStore";

import { Provider } from 'react-redux';
import { configureStore } from './redux/store';
import "./reset.css";

ReactDOM.render(
    <Provider store={configureStore({})}>
      <div className={"my-reset"}>
          <AppStore />
      </div>
        <App />
    </Provider>,
    document.getElementById('root')
);
