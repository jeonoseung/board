<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
<body>
    <div class="login-container">
        <div class="login-box">
            <h1>
                로그인
            </h1>
            <input
                type="text"
                id="user-id-input"
                class="input-box"
                placeholder="아이디를 입력해 주세요."
                spellcheck="false"
            />
            <input
                type="password"
                id="user-pass-input"
                class="input-box"
                placeholder="비밀번호를 입력해 주세요."
                spellcheck="false"
            />
            <div class="btn-block">
                <a href="/signup">
                    <button onclick="" class="submit-button bg-dark">
                        회원가입
                    </button>
                </a>
                <button onclick="clickLogin()" class="submit-button bg-primary">
                    로그인
                </button>
            </div>
        </div>
    </div>
    <script src="/js/login.js"></script>
</body>
</html>