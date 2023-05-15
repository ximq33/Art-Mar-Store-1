import React, {useEffect, useState} from 'react';
import "./Products.css";
import {Link} from "react-router-dom";
import {getProducts} from "../../../utils/ApiCalls";


const Products = () => {

    const [products, setProducts] = useState([]);

    useEffect(() => {
        getProducts().then(result => setProducts(result));
    }, [])

    return (
        <section className="py-5">
            <div className="container px-4 px-lg-5 mt-5">
                <div className="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-left">
                    {products &&
                        products.map((item, index) => (
                            <div className="col mb-5" key={`${index}-${item.id}`}>
                                <div className=" card h-100 product-hover">
                                    <img
                                        className="card-img-top product-image"
                                        src={"data:image/webp;base64," + item.image}
                                        alt="produkt"
                                    />
                                    <div className="card-body p-4">
                                        <div className="text-center">
                                            <h5 className="fw-bolder">{item.name}</h5>
                                            {item.price} zł
                                        </div>
                                    </div>
                                    <div className="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                        <div className="text-center">
                                            <Link
                                                to={`${item.id}`}
                                                className="btn btn-outline-dark mt-auto">
                                                Zobacz opcje
                                            </Link>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        ))}
                </div>
            </div>
        </section>
    );
};


Products.propTypes = {};

Products.defaultProps = {};

export default Products;
