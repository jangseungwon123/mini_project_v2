# Jobpotal (취업포털 프로젝트)

이 프로젝트는 Spring Boot를 사용하여 개발된 구인구직 포털 웹 애플리케이션입니다. 사용자(구직자)와 기업이 회원가입하고, 구인 공고를 게시하고, 이력서를 관리하며, 입사 지원을 할 수 있는 기능을 제공합니다.

## ✨ 주요 기능

*   **사용자 관리:**
    *   일반 사용자 및 기업 회원가입/로그인
    *   JWT 기반 인증 및 인가
*   **이력서 관리:**
    *   이력서 작성, 조회, 수정, 삭제
    *   기술 스택 관리
*   **채용 공고:**
    *   채용 공고 등록, 조회, 수정, 삭제
    *   채용 공고 스크랩
*   **지원 관리:**
    *   채용 공고 지원 및 지원 현황 조회
*   **커뮤니티:**
    *   FAQ 게시판
    *   댓글 기능
*   **기타:**
    *   알림 기능
    *   신고 기능

## 🛠️ 기술 스택

*   **Backend:**
    *   Java 21
    *   Spring Boot 3.3.12
    *   Spring Data JPA (Hibernate)
    *   Spring Web
*   **Database:**
    *   MySQL
    *   H2 (for testing)
*   **Authentication:**
    *   JWT (JSON Web Token)
*   **API Documentation:**
    *   SpringDoc (Swagger UI)
*   **Build Tool:**
    *   Gradle
*   **Utilities:**
    *   Lombok
    *   Apache Commons Lang 3

## 🚀 시작하기

### Prerequisites

*   Java 21
*   Gradle
*   MySQL

### 설치 및 실행

1.  **프로젝트 클론:**
    ```bash
    git clone https://github.com/your-username/jobpotal.git
    cd jobpotal
    ```

2.  **`application.yml` 설정:**
    `src/main/resources/application.yml` 파일을 열어 데이터베이스 연결 정보 및 JWT 시크릿 키를 설정합니다.

    ```yaml
    spring:
      datasource:
        url: jdbc:mysql://localhost:3306/jobpotal_db?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
        username: your-db-username
        password: your-db-password
        driver-class-name: com.mysql.cj.jdbc.Driver
      jpa:
        hibernate:
          ddl-auto: update # 또는 create
        show-sql: true
        properties:
          hibernate:
            format_sql: true

    jwt:
      secret: your-secret-key # 여기에 실제 시크릿 키를 입력하세요
    ```

3.  **애플리케이션 실행:**
    ```bash
    ./gradlew bootRun
    ```
    또는 IDE에서 `JobpotalApplication.java` 파일을 직접 실행합니다.

## 📝 API 문서

애플리케이션 실행 후, 아래 URL에서 API 문서를 확인할 수 있습니다.

*   **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## 📁 프로젝트 구조

```
src
└── main
    └── java
        └── com
            └── tenco
                └── jobpotal
                    ├── JobpotalApplication.java  # 메인 애플리케이션
                    ├── _core                     # 공통 설정 및 유틸리티
                    │   ├── aop                   # AOP 관련 클래스
                    │   ├── common                # 공통 응답 DTO
                    │   ├── config                # Security, WebMvc 설정
                    │   ├── errors                # 예외 처리
                    │   ├── interceptor           # 인터셉터
                    │   └── utils                 # JWT, 날짜 등 유틸리티
                    ├── alarm                     # 알림
                    ├── faq                       # FAQ
                    ├── mypage                    # 마이페이지
                    ├── reply                     # 댓글
                    ├── report                    # 신고
                    ├── resume                    # 이력서
                    ├── scrap                     # 스크랩
                    ├── skill                     # 기술 스택
                    └── user                      # 사용자 (관리자, 기업, 일반)
                        ├── adminInfo
                        ├── applInfo
                        ├── comp
                        └── normal
```