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

