import React from 'react';
import PropTypes from 'prop-types';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import "./Navbar.css";
import logo from "../../../resources/artmar-logo.webp"
const Navbar = () => (
    <div className="navbar-main">
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className="container px-4 px-lg-5">
                <a href="/">
                   <img src={logo} alt="logo artmar" className="navbar-brand navbar-logo"
                        ></img>
                </a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation"><span
                    className="navbar-toggler-icon"></span></button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li className="nav-item"><a className="nav-link active" aria-current="page" href="src/components/Home/Navbar#!">Home</a>
                        </li>
                        <li className="nav-item"><a className="nav-link" href="src/components/Home/Navbar#!">About</a></li>
                        <li className="nav-item dropdown">
                            <a className="nav-link dropdown-toggle" id="navbarDropdown" href="src/components/Home/Navbar#" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
                            <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a className="dropdown-item" href="src/components/Home/Navbar#!">All Products</a></li>
                                <li>
                                    <hr className="dropdown-divider"/>
                                </li>
                                <li><a className="dropdown-item" href="src/components/Home/Navbar#!">Popular Items</a></li>
                                <li><a className="dropdown-item" href="src/components/Home/Navbar#!">New Arrivals</a></li>
                            </ul>
                        </li>
                    </ul>
                    <form className="d-flex" method="get" action="/koszyk">
                        <button className="btn btn-outline-dark" type="submit" formMethod="get">
                            <i className="bi-cart-fill me-1"></i>
                            Koszyk
                            <span className="badge bg-dark text-white ms-1 rounded-pill">0</span>
                        </button>
                    </form>
                </div>
            </div>
        </nav>
    </div>
);

Navbar.propTypes = {};

Navbar.defaultProps = {};

export default Navbar;
