
class Fetch {
    constructor() {

    }
    static async GET({ url,params }){
        let query = ""
        if(params){
            query = new URLSearchParams(params).toString()
        }
        const res = await fetch(`${url}?${query}`,{
            method:"GET",
            headers:{
                'Content-Type': 'application/json'
            },
            cache:"no-store",
            credentials:"include",
        })
        const content_type = res.headers.get('Content-Type')
        if(res.ok){
            if(content_type === null){
                return ""
            }
            else {
                return await res.json()
            }
        }
        else {
            const json = await res.json()
            throw {
                message:json.errorMessage,
                status:res.status
            }
        }
    }
    static async POST({ url,body }){
        const res = await fetch(url,{
            method:"POST",
            headers:{
                'Content-Type': 'application/json'
            },
            cache:"no-store",
            credentials:"include",
            body:JSON.stringify(body)
        })
        const content_type = res.headers.get('Content-Type')
        if(res.ok){
            if(content_type === null){
                return ""
            }
            else {
                return await res.json()
            }
        }
        else {
            const json = await res.json()
            throw {
                message:json.errorMessage,
                status:res.status
            }
        }
    }
    static async DELETE({ url,body }){
        const res = await fetch(url,{
            method:"DELETE",
            headers:{
                'Content-Type': 'application/json'
            },
            cache:"no-store",
            credentials:"include",
            body:JSON.stringify(body)
        })
        const content_type = res.headers.get('Content-Type')
        if(res.ok){
            if(content_type === null){
                return ""
            }
            else {
                return await res.json()
            }
        }
        else {
            const json = await res.json()
            throw {
                message:json.errorMessage,
                status:res.status
            }
        }
    }
}