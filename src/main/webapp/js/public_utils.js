
class DetailError extends Error {
    constructor(message, details) {
        super(message)
        this.details = details;
    }
}

const setCookie = ({ name,value,age }) =>{
    const date = new Date()
    date.setTime(date.getTime() + age)
    document.cookie = `${name}=${value}; max-age=${age}`
}

const deleteCookie = ({ name }) =>{
    document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01;';
}

const goPage = (url) =>{
    window.location.href=url;
}

const userLogout = () =>{
    deleteCookie({
        name:"access_token"
    })
    deleteCookie({
        name:"refresh_token"
    })
    goPage("/")
}