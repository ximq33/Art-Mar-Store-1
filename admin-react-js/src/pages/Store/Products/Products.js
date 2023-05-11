import React, {useState, useEffect, useCallback} from 'react';
import "./Products.css";


const Products = () => {
    const [postData, setPostData] = useState([]);
    const [imageData, setImageData] = useState([]);

    useEffect(() => {
        const APIURL = process.env.REACT_APP_API_URL + "products";
        fetch(APIURL)
            .then((res) => res.json())
            .then((data) => setPostData(data));
    }, []);

    useEffect(() => {
        const productIds = postData.map((item) => item.variantImageId).join("&imageId=");
        const APIURL = process.env.REACT_APP_API_URL + "files?imageId=" + productIds;
        console.log(APIURL);
        fetch(APIURL)
            .then((res) => res.json())
            .then((data) => setImageData(data));
    }, [postData]);

    return (
        <div>
            <section className="py-5">
                <div className="container px-4 px-lg-5 mt-5">
                    <div className="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-left">
                        {postData &&
                            postData.map((item) => (
                                <div className="col mb-5">
                                    <div className=" card h-100 product-hover">
                                        <img
                                            className="card-img-top product-image"
                                            src={"data:image/webp;base64," + imageData.find((image) => image.imageId.value === item.imageId)?.image}
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
                                                <a className="btn btn-outline-dark mt-auto"
                                                   href={`/products/${item.id}`}>
                                                    Zobacz opcje
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            ))}
                    </div>
                </div>
            </section>
        </div>
    );
};




Products.propTypes = {};

Products.defaultProps = {};

export default Products;
