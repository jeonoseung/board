
const user_id_input = document.getElementById("user-id-input")
const user_pass_input = document.getElementById("user-pass-input")
const user_pass_check_input = document.getElementById("user-pass-check-input")
const user_nickname_input = document.getElementById("user-nickname-input")
const recheck = {
    user_id:false,
    user_nickname:false
}

const user_id_reg = new RegExp("^[a-zA-Z0-9]*$")
const user_pass_reg = new RegExp("^[a-zA-Z0-9!@#$%^&*()_+={}\\[\\]:;\"'|\\\\<>,.?/~`-]*$")
const user_nickname_reg = new RegExp("^[가-힣a-zA-Z0-9]*$")

user_id_input.addEventListener("keyup",()=>{
    recheck.user_id = false
})
user_nickname_input.addEventListener("keyup",()=>{
    recheck.user_nickname = false
})

const clickUserIdReCheck = () =>{
    const user_id = user_id_input.value
    if(user_id.length === 0){
        alert("아이디를 입력해 주세요.")
    }
    else if(!user_id_reg.exec(user_id)){
        alert("영문자와 숫자만 입력 가능합니다.")
    }    
    else {
        Fetch.GET({
                url:"/user/check/id",
                params:{
                    user_id
                }
            }).then(()=>{
                    alert("사용 가능한 아이디입니다.")
                    recheck.user_id = true
                })
            .catch((error)=>{
                alert(error.message)
            })
    }
}

const clickUserNicknameReCheck = () =>{
    const user_nickname = user_nickname_input.value
    if(user_nickname.length === 0){
        alert("닉네임을 입력해 주세요.")
    }
    else if(!user_nickname_reg.exec(user_nickname)){
        alert("영문자,한글,숫자만 입력 가능합니다.")
    }  
    else {
        Fetch.GET({
                url:"/user/check/nickname",
                params:{
                    user_nickname
                }
            }).then(()=>{
                    alert("사용 가능한 닉네입니다.")
                    recheck.user_nickname = true
                })
            .catch((error)=>{
                alert(error.message)
            })
    }
}

const clickSignUp = () =>{
    const body = {
        user_id:user_id_input.value,
        user_pass:user_pass_input.value,
        user_nickname:user_nickname_input.value
    }
    const pass_check = user_pass_check_input.value
    if(!recheck.user_id){
        alert("아이디 중복 체크가 필요합니다.")
    }
    else if(!recheck.user_nickname){
        alert("닉네임 중복 체크가 필요합니다.")
    }
    else if(body.user_pass !== pass_check){
        alert("입력한 비밀번호와 비밀번호 확인이 일치하지 않습니다.")
    }
    else if(body.user_id.length === 0){
        alert("아이디를 입력해 주세요.")
    }
    else if(body.user_pass.length === 0){
        alert("비밀번호를 입력해 주세요.")
    }
    else if(!user_pass_reg.exec(body.user_pass)){
        alert("영문자와 숫자, 공백을 제외한 특수 문자만 입력 가능합니다.")
    }
    else if(body.user_nickname.length === 0){
        alert("닉네임을 입력해 주세요.")
    }
    else {
        Fetch.POST({
            url:"/user/signup",
            body
        })
        .then(()=>{
            alert("회원가입이 완료되었습니다!\n로그인 페이지로 이동합니다.")
            goPage("/login")
        })
        .catch((error)=>{
            alert(error.message)
        })
    }
}