<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
    <head>
        <link href="https://cdn.jsdelivr.net/npm/quill@2.0.2/dist/quill.snow.css" rel="stylesheet" />
    </head>
<body>
    <div class="container">
        <div class="content">
            <div class="post-detail">
                <div class="post-info">
                    <div>
                        <div class="post-title">
                            <p class="category">${post.category_name}</p>
                            <strong>
                                ${post.post_title}
                            </strong>
                        </div>
                    </div>
                    <div>
                        <p class="nickname">${post.user_nickname}</p>
                        <p class="info">
                            <span class="comment">댓글 ${post.comment_count}</span>
                            <span class="recommend">추천 ${post.rec_count}</span>
                            <span class="view">조회 ${post.post_view}</span>
                            <span class="date" data-date="${post.post_create_date}"></span>
                        </p>
                    </div>
                </div>
                <div class="post-content">
                    <div id="content"></div>
                    <div id="keyword"></div>
                </div>
                <div class="post-comment-write">
                    <strong>댓글 작성</strong>
                    <textarea id="comment-input" spellcheck="false"></textarea>
                    <div class="btn-block">
                        <button class="bg-primary submit-button" onclick="writeComment()">
                            작성
                        </button>
                    </div>
                </div>
                <div class="post-comment">
                    <c:choose>
                        <c:when test="${empty comment}">
                            <p class="not-list">작성된 댓글이 없습니다.</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="item" items="${comment}">
                                <div>
                                    ${item.comment_content}
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <script>
                document.addEventListener("DOMContentLoaded", function() {
                    const dateElements = document.querySelectorAll(".date");
                    dateElements.forEach(function(el) {
                        const date = el.getAttribute("data-date");
                        el.innerText = `작성일 ` + setDate(date)
                    });
                    const content = document.querySelector("#content")
                    content.innerHTML = `${post.post_content}`
                });
            </script>
            <script src="/js/view.js?v=0.1"></script>
        </div>
    </div>
</body>
</html>