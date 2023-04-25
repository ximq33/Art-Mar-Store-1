export const getUser = () => {
    return fetch(process.env.REACT_APP_API_URL + "users/fromToken", {
        method: "GET",
        credentials: 'include',
        headers: {
            "Content-Type": "application/json",
        }
    })
}

export const getCookie = (name) => {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) {
        return parts.pop().split(';').shift();
    }
}

