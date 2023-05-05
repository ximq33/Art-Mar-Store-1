// @flow
import React from 'react';
import StoreRoutes from './storeRoutes/StoreRoutes';


// setup fake backend
import { configureFakeBackend } from './helpers';

// Themes

// For Saas import Saas.scss
// import './assets/scss/Saas.scss';
// import 'bootstrap/dist/js/bootstrap.bundle.min.js';
// import './styles.css'
// For Modern demo import Modern.scss
// import './assets/scss/Modern.scss';

// For Creative demo import Creative.scss
// import './assets/scss/Creative.scss';

// configure fake backend
configureFakeBackend();

type AppProps = {};

/**
 * Main app component
 */
const AppStore = (props: AppProps): React$Element<any> => {
    return <StoreRoutes></StoreRoutes>;
};

export default AppStore;
