<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
<body>
    <div class="signup-container">
        <div class="signup-box">
            <h1>
                회원가입
            </h1>
            <div class="signup-input-row">
                <p class="name-tag">아이디</p>
                <div class="signup-input-box">
                    <input
                        type="text"
                        id="user-id-input"
                        class="input-box"
                        placeholder="6자 ~ 20자 영문자,숫자 입력"
                        spellcheck="false"
                    />
                    <button onclick="clickUserIdReCheck()" class="submit-button bg-dark">
                        중복확인
                    </button>
                </div>
            </div>
            <div class="signup-input-row">
                <p class="name-tag">비밀번호</p>
                <div class="signup-input-box">
                    <input
                        type="password"
                        id="user-pass-input"
                        class="input-box"력
                        placeholder="8자 ~ 30자 영문자,숫자,공백을 제외한 특수문자 입력"
                        spellcheck="false"
                    />
                </div>
            </div>
            <div class="signup-input-row">
                <p class="name-tag">비밀번호 확인</p>
                <div class="signup-input-box">
                    <input
                        type="password"
                        id="user-pass-check-input"
                        class="input-box"
                        placeholder="입력한 비밀번호를 다시 입력해 주세요."
                        spellcheck="false"
                    />
                </div>
            </div>
            <div class="signup-input-row">
                <p class="name-tag">닉네임</p>
                <div class="signup-input-box">
                    <input
                        type="text"
                        id="user-nickname-input"
                        class="input-box"
                        placeholder="4자 ~ 10자 영문자,숫자,한글 입력"
                        spellcheck="false"
                    />
                    <button onclick="clickUserNicknameReCheck()" class="submit-button bg-dark">
                        중복확인
                    </button>
                </div>
            </div>
            <div class="btn-block">
                <button onclick="clickSignUp()" class="submit-button bg-primary">
                    회원가입
                </button>
            </div>
        </div>
    </div>
    <script src="/js/signup.js"></script>
</body>
</html>