export const getUser = () => {
    return fetch(process.env.REACT_APP_API_URL + "users/fromToken", {
        method: "GET",
        credentials: 'include',
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + getJwtToken()
        }
    })
}

export const getAllVariants = async () => {
    try {
        // make a GET request to the /variants endpoint
        const variantsResponse = await fetch(process.env.REACT_APP_API_URL + 'variants');
        const variantsJson = await variantsResponse.json();

        // map the variants array to an array of objects with the desired fields
        const variantsWithoutImages = variantsJson.map(variant => ({
            id: variant.variantId,
            name: variant.productName,
            color: variant.colorName,
            side: variant.side,
            pattern: variant.pattern,
            image: null,
            added_date: variant.addedDate,
            price: variant.price,
            quantity: variant.quantity,
            status: variant.enabled
        }));

        // construct a comma-separated list of product IDs
        const variantIds = variantsWithoutImages.map(variant => variant.id).join("&variantId=");

        // make a GET request to the /images endpoint with the list of product IDs
        const imagesURL = process.env.REACT_APP_API_URL + `files/ByVariantIds?variantId=${variantIds}`;
        const imagesResponse = await fetch(imagesURL);
        const images = await imagesResponse.json();

        // update the variants array with the image data
        return variantsWithoutImages.map(variant => {
            const image = images.find(image => image.productId === variant.id);
            if (image) {
                return {
                    ...variant,
                    image: image.image
                };
            } else return variant;
        });
        // update the state with the updated variants array

    } catch (error) {
        console.error(error);
    }
}

export const getVariantsByProductId = async (productId) => {
    try {
        // make a GET request to the /variants endpoint
        const variantsResponse = await fetch(process.env.REACT_APP_API_URL + 'variants/byProductId/' + productId);
        const variantsJson = await variantsResponse.json();

        // map the variants array to an array of objects with the desired fields
        const variantsWithoutImages = variantsJson.map(variant => ({
            id: variant.variantId,
            name: variant.productName,
            color: variant.colorName,
            side: variant.side,
            pattern: variant.pattern,
            image: null,
            added_date: variant.addedDate,
            price: variant.price,
            quantity: variant.quantity,
            status: variant.enabled
        }));

        // construct a comma-separated list of product IDs
        const variantIds = variantsWithoutImages.map(variant => variant.id).join("&variantId=");

        // make a GET request to the /images endpoint with the list of product IDs
        const imagesURL = process.env.REACT_APP_API_URL + `files/ByVariantIds?variantId=${variantIds}`;
        const imagesResponse = await fetch(imagesURL);
        const images = await imagesResponse.json();

        // update the variants array with the image data
        return variantsWithoutImages.map(variant => {
            const image = images.find(image => image.productId === variant.id);
            if (image) {
                return {
                    ...variant,
                    image: image.image
                };
            } else {
                return variant;
            }
        });
    } catch (error) {
        console.error(error);
    }
}

export const getProducts = async () => {
    try {
        const APIURL = process.env.REACT_APP_API_URL + "products";
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


        return productsWithoutImages.map(product => {
            const image = images.find(image => image.imageId === product.variantImageId);
            if (image) {
                return {
                    ...product,
                    image: image.image
                }
            } else return product;
        });
    } catch (error) {
        console.log(error);
    }
}

export function getJwtToken() {
    return sessionStorage.getItem("jwt")
}

export function setJwtToken(token) {
    sessionStorage.removeItem("jwt")
    sessionStorage.setItem("jwt", token)
}

export function logout(){
    sessionStorage.removeItem("jwt")
    sessionStorage.removeItem("refreshToken")
}

// Longer duration refresh token (30-60 min)
export function getRefreshToken() {
    return sessionStorage.getItem("refreshToken")
}

export function setRefreshToken(token) {
    sessionStorage.setItem("refreshToken", token)
}

