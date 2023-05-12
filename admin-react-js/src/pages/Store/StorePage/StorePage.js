import React, {useEffect} from 'react';
const Navbar = React.lazy(() => import('../Navbar/Navbar'));
const Header = React.lazy(() => import('../Header/Header'));
const Products = React.lazy(() => import('../Products/Products'));
const Footer = React.lazy(() => import('../Footer/Footer'));

import "../styles.css";


const StorePage = () => {

    return(
    <div>
        <Navbar />
        <Header />
        <Products />
        <Footer />
    </div>
);
};

StorePage.propTypes = {};

StorePage.defaultProps = {};

export default StorePage;
