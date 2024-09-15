<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
<head>
    <link href="https://cdn.jsdelivr.net/npm/quill@2.0.2/dist/quill.snow.css" rel="stylesheet" />
</head>
<body>
    <div class="write-container">
        <div class="write-box">
            <div class="flex gap-4 item-center">
                <select id="category-select" class="category-select-box">
                    <c:forEach var="item" items="${category}">
                        <option
                            value="${item.category_pid}"
                            <c:if test="${post.category_name == item.category_name}">
                                selected
                            </c:if>
                        >
                            ${item.category_name}
                            </option>
                    </c:forEach>
                </select>
                <input tpye="text" id="title-input" class="input-box" placeholder="제목을 입력해 주세요." value="${post.post_title}" spellcheck="false"/>
            </div>
            <div>
                <div id="editor"></div>
            </div>
            <div class="btn-block">
                <button onclick="clickUpdate(${post.post_pid})" class="submit-button bg-primary">
                    게시글 수정
                </button>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/quill@2.0.2/dist/quill.js"></script>
    <script>
        const quill = new Quill('#editor', {
            theme: 'snow'
        });
        quill.root.setAttribute('spellcheck', false)
        quill.root.innerHTML = `${post.post_content}`
    </script>
    <script src="/js/update.js?v=0.2"></script>
</body>
</html>