export function setCookie(name, value, days) {
    const expires = new Date();
    expires.setTime(expires.getTime() + days * 24 * 60 * 60 * 1000);
    const jsonString = JSON.stringify(value);
    document.cookie = `${name}=${encodeURIComponent(jsonString)};expires=${expires.toUTCString()};path=/`;
}

export function getCookie(name) {
    const cookieString = document.cookie;
    const cookies = cookieString.split(';');

    for (let i = 0; i < cookies.length; i++) {
        const cookie = cookies[i].trim();
        if (cookie.startsWith(name + '=')) {
            const jsonString = decodeURIComponent(cookie.substring(name.length + 1));
            return JSON.parse(jsonString);
        }
    }

    return null;
}

export function setLocalStorageItem(key, value) {
    localStorage.setItem(key, JSON.stringify(value));
}

export function getLocalStorageItem(key) {
    const value = localStorage.getItem(key);
    return value ? JSON.parse(value) : null;
}