import React, {useEffect, useState} from "react";
import PropTypes from "prop-types";
import "./Product.css";
import {Col, Row} from "react-bootstrap";
import {Link} from "react-router-dom";
import productImg1 from "../../../assets/images/products/product-5.jpg";
import productImg2 from "../../../assets/images/products/product-1.jpg";
import productImg3 from "../../../assets/images/products/product-6.jpg";
import productImg4 from "../../../assets/images/products/product-3.jpg";
import {getVariantsByProductId} from "../../../utils/ApiCalls";

const Product = ({productId}) => {

    const [selectedProductImg, setSelectedProductImg] = useState(productImg1);
    const [variants, setVariants] = useState([]);


    const handleImgChange = (e, selectedImg) => {
        e.preventDefault();
        setSelectedProductImg(selectedImg);
        return false;
    };


    useEffect(()=>{
        getVariantsByProductId(productId).then(result => setVariants(result));
    }, [])

    return (
        <section className="py-5">
            <div className="container px-4 px-lg-5 my-5">
                <Row gx={4} gx-lg={5} className={"align-items-center"}>
                    <Col md={6}>
                        <Col lg={5}>
                            <img
                                src={selectedProductImg}
                                className="img-fluid"
                                style={{maxWidth: '280px'}}
                                alt="Product-img"
                            />

                            <div className="mt-3 ml-5 d-lg-flex d-none justify-content-center">
                                <Link
                                    to="#"
                                    className={"ms-4"}
                                    onMouseOver={(e) => {
                                        handleImgChange(e, productImg1);
                                    }}
                                    onClick={(e) => {
                                        handleImgChange(e, productImg1);
                                    }}>
                                    <img
                                        src={productImg1}
                                        className="img-fluid img-thumbnail p-2"
                                        style={{maxWidth: '75px'}}
                                        alt="Product-img"
                                    />
                                </Link>
                            </div>
                        </Col>
                    </Col>
                    <div className="col-md-6">
                        <div className="small mb-1">{productId}</div>
                        <h1 className="display-5 fw-bolder">Shop item template</h1>
                        <div className="fs-5 mb-5">
                            <span className="text-decoration-line-through">$45.00</span>
                            <span>$40.00</span>
                        </div>
                        <p className="lead">Lorem ipsum dolor sit amet consectetur adipisicing elit. Praesentium at
                            dolorem
                            quidem modi. Nam sequi consequatur obcaecati excepturi alias magni, accusamus eius
                            blanditiis delectus ipsam minima ea iste laborum vero?</p>
                        <div className="d-flex">
                            <input className="form-control text-center me-3" id="inputQuantity" type="num" value="1"/>
                            <button className="btn btn-outline-dark flex-shrink-0" type="button">
                                <i className="bi-cart-fill me-1"></i>
                                Add to cart
                            </button>
                        </div>
                    </div>
                </Row>
            </div>
        </section>
    );
};

Product.propTypes = {
    productId: PropTypes.string.isRequired
};

Product.defaultProps = {};

export default Product;
