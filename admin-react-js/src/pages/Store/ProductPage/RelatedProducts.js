import React, {useEffect, useState} from "react";
import PropTypes from "prop-types";

const RelatedProducts = ({productId}) => {

    const [productsData, setProductsData] = useState([]);

    useEffect(()=>{

    })

    return (
        <section className="py-1 bg-light">
            <div className="container px-4 px-lg-5 mt-5">
                <h2 className="fw-bolder mb-4">Related products</h2>
                <div className="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

                    <div className="col mb-5">
                        <div className="card h-100">
                            <img className="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg"
                                 alt="..."/>
                            <div className="card-body p-4">
                                <div className="text-center">
                                    <h5 className="fw-bolder">Fancy Product</h5>
                                    $40.00 - $80.00
                                </div>
                            </div>
                            <div className="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div className="text-center"><a className="btn btn-outline-dark mt-auto" href="#">View
                                    options</a></div>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </section>
    );
};

RelatedProducts.propTypes = {
    productId: PropTypes.string.isRequired
};

RelatedProducts.defaultProps = {};

export default RelatedProducts;
