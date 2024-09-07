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
                            <option value="${item.category_pid}">${item.category_name}</option>
                        </c:forEach>
                    </select>
                <input tpye="text" id="title-input" class="input-box" placeholder="제목을 입력해 주세요." spellcheck="false"/>
            </div>
            <div>
                <div id="editor"></div>
            </div>
            <div class="btn-block">
                <button onclick="ClickButton()" class="submit-button bg-primary">
                    작성
                </button>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/quill@2.0.2/dist/quill.js"></script>
    <script src="/js/write.js?v=0.1"></script>
</body>
</html>