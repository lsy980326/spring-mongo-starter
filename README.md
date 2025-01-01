# Spring Mongo Starter

이 프로젝트는 Spring Boot 애플리케이션과 MongoDB를 통합하기 위한 템플릿입니다.

## 목차
- [소개](#소개)
- [특징](#특징)
- [요구 사항](#요구-사항)
- [설치](#설치)
- [사용법](#사용법)
- [설정](#설정)

## 소개
이 프로젝트는 MongoDB를 데이터베이스로 사용하는 Spring Boot 애플리케이션을 빠르게 시작할 수 있는 기반을 제공합니다. 기본적인 설정과 의존성을 포함하고 있어 신속하게 프로젝트를 구축할 수 있습니다.

## 특징
- Spring Boot 통합
- MongoDB 지원
- 기본적인 CRUD 작업
- Gradle 빌드 시스템

## 요구 사항
- Java 11 이상
- MongoDB
- Gradle

## 설치
1. 레포지토리를 클론합니다:
    ```sh
    git clone https://github.com/yourusername/spring-mongo-starter.git
    cd spring-mongo-starter
    ```

2. 프로젝트를 빌드합니다:
    ```sh
    ./gradlew build
    ```

3. 애플리케이션을 실행합니다:
    ```sh
    ./gradlew bootRun
    ```

## 사용법
애플리케이션 실행 후 `http://localhost:8080`에서 접근 가능합니다. 이 애플리케이션은 MongoDB에서 데이터를 관리하기 위한 기본적인 CRUD 작업을 제공합니다.

## 설정
애플리케이션 설정은 `src/main/resources` 디렉터리에 있는 `application.properties` 파일을 통해 구성할 수 있습니다. 여기에서 MongoDB 연결 정보와 기타 애플리케이션 설정을 지정할 수 있습니다.

예시 `application.properties`:
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/yourdatabase
