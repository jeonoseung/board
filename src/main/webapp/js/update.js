

const clickUpdate = async  (post_pid) =>{
    const title = document.getElementById("title-input").value;
    const category = document.getElementById("category-select")
    const html = quill.root.innerHTML;
    const text = quill.root.innerText;
    const pid = Number(post_pid)

    if(title.length < 5){
        alert("게시글 제목은 5자 이상 입력이 필요합니다.")
    }
    else if(text.length < 5){
        alert("게시글 내용은 5자 이상 입력이 필요합니다.")
    }
    else {
        const body = {
            post_title:title,
            post_content:html,
            post_category:category.value
        }
        Fetch.PUT({
            url:`/update/${post_pid}`,
            body
        })
            .then(()=>{
                alert("게시글이 작성되었습니다.\n게시글 목록으로 이동합니다.")
                goPage("/")
            })
            .catch((error)=>{
                alert(error.message)
            })
    }
}