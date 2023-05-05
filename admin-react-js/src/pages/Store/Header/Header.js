import React from 'react';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';


const Header = () => {
    return(
     <header className="bg-dark py-5">
         <div className="container px-4 px-lg-5 my-5">
             <div className="text-center text-white">
                 <h1 className="display-4 fw-bolder">Drzwi, drzwi</h1>
                 <p className="lead fw-normal text-white-50 mb-0">...i jeszcze raz okna</p>
             </div>
         </div>
     </header>
);
};

Header.propTypes = {};

Header.defaultProps = {};

export default Header;
