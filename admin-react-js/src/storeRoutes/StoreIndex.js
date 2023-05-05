import React, {Suspense} from 'react';
import {useRoutes} from 'react-router-dom';
import {useSelector} from 'react-redux';
import PrivateRoute from './StorePrivateRoute';
import StoreRoot from './StoreRoot';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';


const StorePage = React.lazy(() => import('../pages/Store/StorePage/StorePage'));

const loading = () => <div className=""></div>;

type LoadComponentProps = {
    component: React.LazyExoticComponent<() => JSX.Element>,
};

const LoadComponent = ({component: Component}: LoadComponentProps) => (
    <Suspense fallback={loading()}>
        <Component/>
    </Suspense>
);

const AllRoutes = () => {
    const {layout} = useSelector((state) => ({
        layout: state.Layout,
    }));

    return useRoutes([
        { path: '/', element: <StoreRoot /> },

        {
            path: 'store',
            element: <LoadComponent component={StorePage}/>
        }])
}

export {AllRoutes};
