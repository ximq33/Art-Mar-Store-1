import React from 'react';
import Navbar from "../Navbar/Navbar";
import Header from "../Header/Header";
import Products from "../Products/Products";
import Footer from "../Footer/Footer";

const HomePage = () => (
    <div>
        <Navbar />
        <Header />
        <Products />
        <Footer />
    </div>
);

HomePage.propTypes = {};

HomePage.defaultProps = {};

export default HomePage;
