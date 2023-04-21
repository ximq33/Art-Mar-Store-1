

export const getUser = (token) => {
   return fetch(process.env.REACT_APP_API_URL + "users/fromToken", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            'Authorization': 'Bearer ' + token
        }
    })
}

