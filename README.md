<h1>게시판</h1>

Spring, Java, JSP, JPA 학습을 위한 개인 학습 프로젝트입니다.
게시글 조회, 작성, 삭제, 수정, 회원가입, 로그인 기능으로 이루어져 있습니다.
로그인 방식은 JWT토큰을 사용했습니다.

<div align="center">
    <div align="center">
        <h4>사용 기술 스택</h4>
    </div>
    <div align="center">
        <img src="https://img.shields.io/badge/java-007396?style=flat-square&logo=java&logoColor=white"/>
        <img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=Spring&logoColor=white"/>
<img src="https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=css3&logoColor=white"/>
<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=javascript&logoColor=black"/>
    </div>
</div>

### 프로젝트 구조
1. [DTO](https://github.com/jeonoseung/board/tree/main/src/main/java/com/project/board/DTO) - Query,Request,Reponse 등 객체형 타입 지정 시 작성 코드
2. [Entity](https://github.com/jeonoseung/board/tree/main/src/main/java/com/project/board/Entity) - JPA DB 형상 관리 코드
3. [Enum](https://github.com/jeonoseung/board/tree/main/src/main/java/com/project/board/Enum) - 단순 게시글 상태값, JWT 토큰 타입 지정
4. [Repository](https://github.com/jeonoseung/board/tree/main/src/main/java/com/project/board/Repo) - Query 작성
5. [RestApiController](https://github.com/jeonoseung/board/tree/main/src/main/java/com/project/board/RestController) - mvc가 아닌 비동기 API 요청이 필요한 작업 코드
6. [Service](https://github.com/jeonoseung/board/tree/main/src/main/java/com/project/board/Service) - 주요 처리 로직 코드
7. [Utils](https://github.com/jeonoseung/board/tree/main/src/main/java/com/project/board/Utils) - JWT 토큰 관련(검증,유효기간 등) 기능 및 공용 기능 코드
8. [MVC](https://github.com/jeonoseung/board/blob/main/src/main/java/com/project/board/PageController.java) - MVC 페이지 컨트롤러
9. [JSP](https://github.com/jeonoseung/board/tree/main/src/main/webapp/WEB-INF/views) - JSP 작성 코드
