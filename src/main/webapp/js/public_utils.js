const setCookie = ({ name,value,age }) =>{
    const date = new Date()
    date.setTime(date.getTime() + age)
    document.cookie = `${name}=${value}; max-age=${age}`
}

const deleteCookie = async  ({ name }) =>{
    document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01; path=/;';
}

const goPage = (url) =>{
    window.location.href=url;
}

const needLogin = () =>{
    const current = location.pathname
    goPage(`/login?redirect_url=${encodeURIComponent(current)}`)
}

const userLogout = async () =>{
    await deleteCookie({
        name:"access_token"
    })
    await deleteCookie({
        name:"refresh_token"
    })
    goPage("/")
}

function makeFirstButton(c,target){
    const button = document.createElement("button")
    button.innerHTML = `
            <img src="/img/arrow_left.svg" width="20" height="20"/>
        `
    if(c === 1){
        button.className = `page-btn-disabled`
        button.disabled = true
    }
    else {
        button.className = `page-btn`
    }
    button.onclick = () =>{
        window.location.href = `/`
    }
    target.appendChild(button)
}

function makeLastButton(c,last,target){
    const button = document.createElement("button")
    button.innerHTML = `
            <img src="/img/arrow_right.svg" width="20" height="20"/>
        `
    if(c === last){
        button.className = `page-btn-disabled`
        button.disabled = true
    }
    else {
        button.className = `page-btn`
    }
    button.onclick = () =>{
        window.location.href = `/?page=${last}`
    }
    target.appendChild(button)
}

function makeButton(c,i,target){
    const button = document.createElement("button")
    button.innerText = i;
    if(c === i){
        button.className = `page-btn-active`
    }
    else {
        button.className = `page-btn`
    }
    button.onclick = () =>{
        window.location.href = `?page=${i}`
    }
    target.appendChild(button)
}

function setAfterDate(date){
    const current = new Date().getTime();
    const writeTime = new Date(date).getTime();
    const difference = current - writeTime;
    if (current - writeTime < 0) return `~ 전`;

    const timeUnits = [
      { unit: "년", divisor: 365 * 24 * 60 * 60 * 1000 },
      { unit: "달", divisor: 31 * 24 * 60 * 60 * 1000 },
      { unit: "주", divisor: 7 * 24 * 60 * 60 * 1000 },
      { unit: "일", divisor: 24 * 60 * 60 * 1000 },
      { unit: "시간", divisor: 60 * 60 * 1000 },
      { unit: "분", divisor: 60 * 1000 },
      { unit: "초", divisor: 1000 },
    ];



    for (const unit of timeUnits) {
      const value = Math.floor(difference / unit.divisor);
      if (value > 0) return `${value}${unit.unit} 전`;
    }

    return `~ 전`;
}

const setDate = (date) => {
    const [ymd, hms] = date.split("T");
    const [y, m, d] = ymd.split("-");
    const [h, min, s] = hms.split(":");
    return `${y}-${m}-${d} ${h}:${min}:${s}`;
  };

function viewPagination (element_id,length){
    const box = document.getElementById(element_id)
    box.replaceChildren()
    const url = new URLSearchParams(location.search)
    const page = url.get("page")

    let current = 1;
    let p = Math.floor(length / 10)
    const p_arr = []
    for(let i=0;i<p;i++){
        p_arr.push(i+1)
    }
    const n = length % 10

    if(page && !isNaN(Number(page)) && (Number(page) > 0 || Number(page <= p_arr.length))){
        current = Number(page)
    }
    if(current > p_arr.length){
        return
    }
    let max = 5;
    if(n !== 0){
        p = Math.ceil(p)
    }
    if(p < max){
        max = p
    }

    const max_f = Math.floor(max / 2)
    const index = p_arr.indexOf(current)
    makeFirstButton(current,box)
    if(index < max_f){
        for(let i = 0;i<max;i++){
            makeButton(current,i+1,box)
        }
    }
    else if(index > (p_arr.length - 1 - max_f)){
        const splice_arr = p_arr.splice(p_arr.length-max,max)
        for(let i=0;i<splice_arr.length;i++){
            makeButton(current,splice_arr[i],box)
        }
    }
    else {
        const splice_arr = p_arr.splice(index-2,max)
        for(let i=0;i<splice_arr.length;i++){
            makeButton(current,splice_arr[i],box)
        }
    }
    makeLastButton(current,p,box)
}

function getLastRouteParameter(){
    const path = location.pathname
    const split = path.split("/")
    return split[split.length-1]
}

function RequestCatch(error){
    if(error.message){
        alert(error.message)
    }
    else {
        alert("알 수 없는 오류가 발생했습니다.")
    }
}