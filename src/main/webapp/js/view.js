const comment = document.getElementById("comment-input")
const target_id = null
function writeComment(){
    const post_pid = getLastRouteParameter()
    const parse_post_pid = Number(post_pid)
    const value = comment.value;
    if(value.length === 0){
        alert("댓글을 작성해 주세요.")
    }
    else if(isNaN(parse_post_pid)){
        alert("올바르지 않은 접근입니다.")
        goPage("/")
    }
    else {
        Fetch.POST({
            url:"/comment",
            body:{
                target_id,
                comment_content: value,
                post_pid: parse_post_pid
            }
        })
            .then(()=>{
                alert("댓글이 작성되었습니다.")
            })
            .catch(RequestCatch)
    }
}