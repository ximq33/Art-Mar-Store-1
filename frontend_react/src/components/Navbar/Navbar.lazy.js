import React, { lazy, Suspense } from 'react';

const LazyHomePage = lazy(() => import('./Navbar'));

const HomePage = props => (
  <Suspense fallback={null}>
    <LazyHomePage {...props} />
  </Suspense>
);

export default HomePage;
