<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!doctype html>
<html lang="ko">
<head>
    <link rel="stylesheet" href="css/global.css?ver=1.8" type="text/css"/>
    <link rel="stylesheet" href="css/pretendard-subset.css" type="text/css"/>
    <script src="/js/API_Fetch.js?ver=0.1"></script>
    <script src="/js/public_utils.js?ver=0.1"></script>
    <meta chaarset="UTF-8">
    <title>게시판</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <main>
        <jsp:include page="${requestScope.url}"/>
    </main>
</body>
</html>