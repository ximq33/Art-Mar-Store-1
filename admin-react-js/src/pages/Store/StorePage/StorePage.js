import React, {useEffect} from 'react';
import Navbar from "../Navbar/Navbar";
import Header from "../Header/Header";
import Products from "../Products/Products";
import Footer from "../Footer/Footer";
import "../../../styles.css";


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
