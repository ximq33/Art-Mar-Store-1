import React from 'react';
import Navbar from "../Navbar/Navbar";
import Header from "../Header/Header";
import Products from "../Products/Products";
import Footer from "../Footer/Footer";

const StorePage = () => (
    <div>
        <Navbar />
        <Header />
        <Products />
        <Footer />
    </div>
);

StorePage.propTypes = {};

StorePage.defaultProps = {};

export default StorePage;
