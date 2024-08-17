const quill = new Quill('#editor', {
    theme: 'snow'
});

quill.root.setAttribute('spellcheck', false)
const ClickButton = () =>{
    const title = document.getElementById("title-input").value;
    const html = quill.root.innerHTML;
    const text = quill.root.innerText;
    if(title.length < 5){
        alert("게시글 제목은 5자 이상 입력이 필요합니다.")
    }
    else if(text.length < 5){
        alert("게시글 내용은 5자 이상 입력이 필요합니다.")
    }
    else {
        const body = {
            title,
            content:html
        }
    }
}