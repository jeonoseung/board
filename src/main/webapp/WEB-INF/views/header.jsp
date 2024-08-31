<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="ko">
<body>
    <header>
        <div class="left">
            <a href="/">

            </a>
        </div>
        <nav class="page-menu">
            <a href="/">
                목록
            </a>
            <a href="/write">
                작성
            </a>
        </nav>
        <div class="right">
            <c:choose>
                <c:when test="${not empty user}">
                    <div class="user-menu">
                        <span>${user.user_nickname}</span>
                        <nav>
                            <button onclick="userLogout()">
                                로그아웃
                            </button>
                        </nav>
                    </div>
                </c:when>
                <c:otherwise>
                     <a href="/login">
                        로그인
                    </a>
                </c:otherwise>
            </c:choose>
        </div>
    </header>
</body>
</html>
