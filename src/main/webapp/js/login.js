
const userIdInput = document.getElementById("user-id-input")
const userPassInput = document.getElementById("user-pass-input")

const clickLogin = async  () =>{
    
    const userId = userIdInput.value
    const userPass = userPassInput.value
    if(userId.length === 0){
        alert("아이디를 입력해 주세요.")
    }
    else if(userPass.length === 0){
        alert("비밀번호를 입력해 주세요.")
    }
    else {
        const body = {
            user_id:userId,
            user_pass:userPass
        }
        Fetch.POST({
                url:"/user/login",
                body
        }).then((res)=>{
                const { access_token,refresh_token } = res
                setCookie({
                    name:"access_token",
                    value:access_token,
                    age:60 * 60 * 24
                })
                setCookie({
                    name:"refresh_token",
                    value:refresh_token,
                    age:60 * 60 * 24 * 7
                })
                const url = new URLSearchParams(location.search);
                const redirect = url.get("redirect_url")
                if(redirect){
                    window.location.href=redirect
                }
                else {
                    window.location.href="/"
                }
            }).catch((error)=>{
                alert(error.message)
            })
    }
}

const keyUpEnter = (e) =>{
    if(e.key === "Enter"){
        clickLogin()
    }
}

userPassInput.addEventListener("keyup",keyUpEnter)