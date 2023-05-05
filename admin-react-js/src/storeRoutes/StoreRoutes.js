import { BrowserRouter } from 'react-router-dom';
import { AllRoutes } from './StoreIndex';


const Routes = () => {
    return (
        <BrowserRouter>
            <AllRoutes />
        </BrowserRouter>
    );
};

export default Routes;
