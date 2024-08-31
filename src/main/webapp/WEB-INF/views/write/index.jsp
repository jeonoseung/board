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
            <input tpye="text" id="title-input" class="input-box" placeholder="제목을 입력해 주세요." spellcheck="false"/>
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