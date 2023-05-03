import { Navigate, useLocation } from 'react-router-dom';
import { getUser } from '../utils/ApiCalls';
import { useEffect, useState } from 'react';

type PrivateRouteProps = {
    component: React.ComponentType;
    roles?: string;
};

/**
 * Private Route forces the authorization before the route can be accessed
 * @param {*} param0
 * @returns
 */
const PrivateRoute = ({ component: RouteComponent, roles, ...rest }: PrivateRouteProps) => {
    const location = useLocation();
    const [authenticated, setAuthenticated] = useState(true);
    const [userRole, setUserRole] = useState('');

    const loadUserData = async () => {
        try {
            const response = await getUser();
            if (response.status >= 400) {
                setAuthenticated(false);
            }
            const data = await response.json();
            setAuthenticated(response.ok);
            setUserRole(data.authorities[0].authority);
        } catch (error) {
            setAuthenticated(false);
            console.error(error);
            throw error;
        }
    }

    useEffect(() => {
        const fetchData = async () => {
            try {
                await loadUserData();
            } catch (error) {
                console.error(error);
                throw error;
            }
        };
        return fetchData();
    }, []);

    if (!authenticated) {
        return <Navigate to={'/account/login'} state={{ from: location }} replace />;
    }

    if (roles && roles.indexOf(userRole) === -1 || userRole === null) {
        return <Navigate to={{ pathname: '/account/login' }} />;
    }

    return <RouteComponent />;
};

export default PrivateRoute;
