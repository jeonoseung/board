<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
<body>
    <div class="container">
        <div class="content">
            <c:forEach var="item" items="${post}">
                <div>
                    <p>${item.post_pid}</p>
                    <p>${item.post_title}</p>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>