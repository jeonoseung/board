


const comment = document.getElementById("comment-input")
const target_id = null
function deletePost(){
    const post_pid = getLastRouteParameter()
    const parse_post_pid = Number(post_pid)
    if(isNaN(parse_post_pid)){
        alert("올바르지 않은 접근입니다.")
        goPage("/")
    }
    else {
        Fetch.DELETE({
            url:`/post/${post_pid}`,
        }).then(()=>{
                alert("게시글이 삭제되었습니다.\n게시글 목록으로 이동합니다.")
                goPage("/")
            }).catch(RequestCatch)
    }
}
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
                location.reload()
            })
            .catch(RequestCatch)
    }
}
function deleteComment(comment_pid){
    Fetch.DELETE({
        url:`/comment/${comment_pid}`,
    }).then(()=>{
            alert("댓글이 삭제 되었습니다.")
            location.reload()
        }).catch(RequestCatch)
}
function recommendPost(el,post_pid){
    Fetch.POST({
        url:`/post/rec/${post_pid}`
    })
    .then(()=>{
        location.reload()
    })
    .catch(RequestCatch)
}