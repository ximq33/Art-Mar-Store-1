import { Navigate } from 'react-router-dom';

const StoreRoot = () => {
    const getRootUrl = () => {
        let url = 'store';
        return url;
    };

    const url = getRootUrl();

    return <Navigate to={`/${url}`} />;
};

export default StoreRoot;