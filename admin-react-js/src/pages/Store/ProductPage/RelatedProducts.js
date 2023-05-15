import React, {useEffect, useState} from 'react';
import PropTypes from "prop-types";
import {Link} from "react-router-dom";

const RelatedProducts = ({productId}): React$Element<any> => {
    const [relatedProducts, setRelatedProducts] = useState([]);


    const fetchData = async () => {
        try {
            const APIURL = process.env.REACT_APP_API_URL + "products/relatedOrRandom/" + productId;
            const productResponse = await fetch(APIURL);
            const productsJson = await productResponse.json();

            const productsWithoutImages = productsJson.map(product => ({
                id: product.productId,
                name: product.name,
                manufacturer: product.manufacturer,
                price: product.price,
                variantImageId: product.variantImageId,
                image: null
            }))

            const imageIds = productsWithoutImages.map(product => product.variantImageId).join("&imageId=");
            const imagesURL = process.env.REACT_APP_API_URL + `files/ByImageIds?imageId=${imageIds}`;
            const imagesResponse = await fetch(imagesURL);
            const images = await imagesResponse.json();


            const productsWithImages = productsWithoutImages.map(product => {
                const image = images.find(image => image.imageId === product.variantImageId);
                if (image) {
                    return {
                        ...product,
                        image: image.image
                    }
                } else return product;
            })
            await setRelatedProducts(productsWithImages);
        } catch (error) {
            console.log(error);
        }
    }

    useEffect(() => {
        fetchData();
    }, [])


    return (
        <section className="py-1 bg-light">
            <div className="container px-4 px-lg-5 mt-5">
                <h2 className="fw-bolder mb-4">Related products</h2>
                <div className="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                    {relatedProducts &&
                        relatedProducts.map((item, index) => (
                            <div className="col mb-5" key={`${index}-${item.id}`}>
                                <div className="card h-100">
                                    <img
                                        className="card-img-top"
                                        src={
                                            "data:image/webp;base64," + item.image
                                        }
                                        alt="..."
                                    />
                                    <div className="card-body p-4">
                                        <div className="text-center">
                                            <h5 className="fw-bolder">{item.name}</h5>
                                            od {item.price} pln
                                        </div>
                                    </div>
                                    <div className="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                        <div className="text-center">
                                            <Link
                                                to={`/store/${item.id}`}
                                                className="btn btn-outline-dark mt-auto"
                                                href="#">View options
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

RelatedProducts.propTypes = {
    productId: PropTypes.string.isRequired
};

RelatedProducts.defaultProps = {};

export default RelatedProducts;
