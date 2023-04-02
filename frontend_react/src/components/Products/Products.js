import React, {useState, useEffect, useCallback} from 'react';
import PropTypes from 'prop-types';
import "./Products.css";
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import img from "../../resources/img.png"

function Products(props) {
    const [postData, setPostData] = useState([]);
    useEffect(() => {
        const APIURL = "http://localhost:8080/products"
        fetch(APIURL)
            .then((res) => res.json())
            .then((data) => setPostData(data));
    }, []);
    return (
        <div>
            <section className="py-5">
                <div className="container px-4 px-lg-5 mt-5">
                    <div className="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-left">
                        {postData &&
                            postData.map((item) => (
                                    <div className="col mb-5">
                                        <div className=" card h-100 product-hover">
                                            <img className="card-img-top product-image"
                                                 src={img} alt="produkt"/>
                                            <div className="card-body p-4">
                                                <div className="text-center">
                                                    <h5 className="fw-bolder">{item.name}</h5>
                                                    {item.price} zł
                                                </div>
                                            </div>
                                            <div className="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                                <div className="text-center"><a className="btn btn-outline-dark mt-auto"
                                                                                href="/produkt/${title.id}">Zobacz opcje</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                )
                            )}
                    </div>
                </div>
            </section>
        </div>
    )
}

// function Products() {
//     const [products, setProducts] = useState([]);
//
//     const fetchProducts = useCallback(() => {
//         fetch('http://localhost:8080/products')
//             .then(response => {
//                 console.log("fetching data from /products")
//                 return response.json()
//             })
//             .then(setProducts)
//             .catch(r => r.log)
//     }, [])
//
//     return ( <section className="py-5">
//         <div className="container px-4 px-lg-5 mt-5">
//             <div className="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-left">
//                 <ul>
//                 {products.map(item =>
//                     <li key={item.productId}>
//                 <div className="col mb-5">
//                     <div className=" card h-100">
//
//                         <img className="card-img-top product-image"
//
//                              src={img} alt="produkt"/>
//
//                         <div className="card-body p-4">
//                             <div className="text-center">
//
//                                 <h5 className="fw-bolder">{item.name}</h5>
//
//                                 {item.price} zł
//                             </div>
//                         </div>
//
//                         <div className="card-footer p-4 pt-0 border-top-0 bg-transparent">
//                             <div className="text-center"><a className="btn btn-outline-dark mt-auto"
//                                                             href="/produkt/${title.id}">Zobacz opcje</a></div>
//                         </div>
//                     </div>
//                 </div>
//                     </li>
//                 )}
//                 </ul>
//
//
//             </div>
//         </div>
//     </section>
//                     )
// }


Products.propTypes = {};

Products.defaultProps = {};

export default Products;
