<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
<body>
    <script>
        let d = null;
    </script>
    <div class="container">
        <div class="content">
            <c:choose>
                <c:when test="${empty post}">
                    <p class="not-list">조회할 수 있는 게시글이 없습니다.</p>
                </c:when>
                <c:otherwise>
                    <div class="post-table">
                        <c:forEach var="item" items="${post}">
                            <a href="/post/${item.post_pid}" class="post-row">
                                <div class="top-area">
                                    <p class="title">
                                        <span class="category">${item.category_name}</span>
                                        <strong>
                                            ${item.post_title}
                                        </strong>
                                    </p>
                                    <p class="nickname">${item.user_nickname}</p>
                                </div>
                                <div class="middle-content" id="middle-${item.post_pid}">
                                </div>
                                <script>
                                    d = document.createElement("div")
                                    d.innerHTML = `${item.post_content}`;
                                    document.getElementById("middle-${item.post_pid}").innerText = d.innerText;
                                </script>
                                <div class="bottom-area">
                                    <p class="">
                                        <span class="comment">댓글 ${item.comment_count}</span>
                                        <span class="recommend">추천 ${item.rec_count}</span>
                                        <span class="view">조회 ${item.post_view}</span>
                                    </p>
                                    <p class="date" data-date="${item.post_create_date}"></p>
                                </div>
                            </a>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
            <div class="pagination-box" id="post-pagination">
            </div>
            <script>
                viewPagination("post-pagination",${post_length})
                        document.addEventListener("DOMContentLoaded", function() {
                            const dateElements = document.querySelectorAll(".date");
                            dateElements.forEach(function(el) {
                                const date = el.getAttribute("data-date");
                                el.innerText = setAfterDate(date);
                            });

                        });
            </script>
        </div>
    </div>
</body>
</html>