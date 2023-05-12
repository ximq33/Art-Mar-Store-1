import React from "react";
import {useParams} from "react-router-dom";
import "./styles.css"


const Footer = React.lazy(() => import('../Footer/Footer'));
const Navbar = React.lazy(() => import('../Navbar/Navbar'));
const Product = React.lazy(()=> import('./Product'));
const RelatedProducts = React.lazy(()=>import('./RelatedProducts'));


const ProductPageLayout = () => {

    const { productId} = useParams()

    return(
        <>
            <Navbar />
            <Product productId={productId}/>
            <RelatedProducts productId={productId}/>
            <Footer />
        </>
    );
};

ProductPageLayout.propTypes = {};

ProductPageLayout.defaultProps = {};

export default ProductPageLayout;
