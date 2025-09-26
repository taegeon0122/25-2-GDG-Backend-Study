1. 학습 내용 정리
# 웹

인터넷 : 전 세계 컴퓨터와 기기를 연결하는 거대한 **글로벌 네트워크**

웹 : 인터넷 위에서 동작하는 **서비스** 중 하나. 인터넷에 연결된 전 세계 사용자들이 **서로의 정보를 공유할 수 있는 장소**

Client-Server Model : 웹에서 컴퓨터들이 서로 정보를 주고받는 방식

URL : Client가 서버에 요청을 보낼 때의 주소

c.f. http://www.example.com:5883/category/food.html?topic=pizza

> [www.example.com](http://www.example.com) : Host(리소스가 위치한 서버의 IP주소 or 도메인)

> 5883 : 서버의 특정 **Network Port 번호**(생략 가능)

> /category/food.html : 서버 내에서 원하는 **리소스의 Path**

> ?topic=pizza : **서버에 보내는 추가적인 정보**로 ? 뒤에 key-value 형식으로 나열

> http: : Scheme(Protocol) {뒤에 자세히 나옴}

# HTTP

HTTP(HyperText Transfer Protocol) : 웹에서 데이터를 주고받는 Server-Client 모델의 **프로토콜**

- 두 가지 특징 존재
    1. 무상태성(Stateless) : Server는 Client의 **요청을 저장X**
    2. 비연결성(Connectionless) : Client는 Server와의 **연결을 유지X**

## Client 요청

![image.png](attachment:ea70f39d-2e3a-4d6c-8d5b-85ef64c39897:image.png)

- HTTP 요청 구조
    1. Start Line : **요청 메서드**, 요청할 경로, HTTP 버전 정보 포함
    2. Headers : 요청에 대한 **부가 정보**
    3. Body : 실제 전송할 **데이터**(c.f. 회원가입 시 Id, Password)
- HTTP 주요 메서드(in Start Line)
    1. GET : 리소스 **조회**
    2. POST : 리소스 **추가, 등록**
    3. PUT : 리소스 **교체**(없으면 새로 생성)
    4. PATCH : 리소스 **일부 수정**
    5. DELETE : 리소스 **삭제**

## Server 응답

![IMG_F85509C7F87C-1.jpeg](attachment:baa1e84b-e440-4640-b840-75145333a4ad:IMG_F85509C7F87C-1.jpeg)

- HTTP 응답 구조
    1. Status Line : **HTTP 버전, HTTP 상태 코드, 상태 메시지**
    2. Headers : 응답에 대한 부가 정보
    3. Body : 실제 응답 **데이터**
- HTTP 주요 상태 코드(in Status Line)
    1. 200 OK : 요청이 성공적으로 처리됨
    2. 201 Created : 요청이 성공적으로 처리됨 + 새로운 리소스 생성됨
    3. 400 Bad Request : 클라이언트의 요청 잘못으로 서버가 이해 못함
    4. 404 Not Found : 지정된 리소스 찾을 수 없음
    5. 500 Internal Servel Error : 서버 내부 오류로 요청 처리 못함

### 전체 HTTP 과정(예시 : 홍익대학교 메인 페이지에 접속)

1. Client(Me)가 GET 요청 메시지로 Server(Hongik)에게 **요청**
2. Server가 요청 받고, 메인 페이지 찾음
3. Server는 메인 페이지 찾은 후, (200 OK)상태 코드와 함께 Body에 html 코드를 담아서 Client에게 **응답**
4. Client는 응답 메세지 속 Body 해석 후, 홈페이지를 화면에 그려줌

But, 기술 발전으로 인해 웹에서 제공되는 정보량이 많아짐 > 화면의 뼈대는 재사용, 필요한 데이터만 서버에서 받기

## 프론트엔드와 백엔드

- 프론트엔드 : 사용자가 직접 보고 **상호작용하는 화면**, **UI(사용자 인터페이스) 개발**
- 백엔드 : 사용자의 요청을 받아 실제 동작 처리 및 **데이터 저장&관리**
    - 데이터베이스(DataBase, DB) : 데이터를 체계적으로 모아둔 저장소, DBMS로 관리, 조작

### 전체 과정(예시 : 게시글을 조회)

1. Client가 GET 요청 메시지(c.f. 100번 게시글의 데이터 달라)로 Server에게 요청
2. Server는 요청 받고, DB에서 100번 게시글을 찾은 뒤, Body에 담아서 Client에게 응답(BackEnd 역할)
3. Client는 CS기술로 화면에 렌더링(FrontEnd 역할)

## API & REST API

- API(Application Programming Interface) : Client와 Server가 소통할 때 **어떻게 요청을 보내고, 어떻게 응답할지** 등을 미리 정해놓은 **규칙과 기능의 목록**
- REST(Representational State Transfer) : 네트워크 아키텍처 스타일(HTTP의 장점을 최대 활용)
    - REST 구성요소
        - 자원(Resource) - URI : 모든 자원은 고유한 ID를 가지며, ID는 /student/1 같은 HTTP URI이다.
        - 행위(Verb) - Method : 자원을 조작하기 위해 HTTP Method 사용
        - 표현(Representation) : 데이터 주고받는 형식(주로 JSON 사용)
            - JSON(JavaScript Object Notation)의 형태 : ”name” : “taegeon”(key : value)

### 실습 : RESTful하게 API 설계하기 - API 명세서 작성

- 요구사항 - 온라인 쇼핑몰 프로젝트
    1. 이름, 전번, 주소를 바탕으로 **회원 등록** 가능 > **HTTP Method** : POST & **URI** : /members
    2. **회원 리스트 조회** > **HTTP Method** : GET & **URI** : /members
    3. **회원 정보 상세 조회** > **HTTP Method** : GET & **URI** : /members/{memberId}
    4. **회원 정보 수정** > **HTTP Method** : PATCH & **URI** : /members/{memberId}
    5. **회원 삭제** > **HTTP Method** : DELETE & **URI** : /members/{memberId}

~ path variable : URI 일부를 변수처럼 사용해서 특정 자원을 식별(c.f. /members/1)

# Spring Boot

Spring : Java 엔터프라이즈(대규모&복잡) 애플리케이션 개발에 사용되는 오픈소스 경량급(가벼움&편리) 애플리케이션 프레임워크(객체지향을 잘 살림)

- 프레임워크 : 애플리케이션 개발을 쉽게 할 수 있게 만든 뼈대(기본 구조와 공통 기능 제공)

Spring Boot : 복잡한 초기설정 없이, Spring을 쉽게 사용할 수 있게 해주는 도구

2. 스크린 샷
<img width="1792" height="1048" alt="스크린샷 2025-09-25 17 11 43" src="https://github.com/user-attachments/assets/f7f1c4b4-e8c1-4058-89fc-0ade1587c685" />

3. 작성한 온라인 쇼핑몰 프로젝트 API 명세서
    - **회원 기능**
        - 회원 등록 : POST, /members
        - 회원 목록 조회 : GET, /members
        - 개별 회원 정보 상세 조회 : GET, /members/{memberId}
        - 회원 정보 수정 : PATCH, /members/{memberId}
        - 회원 삭제 : DELETE, /members/{memberId}
    - **상품 기능**
        - 상품 정보 등록 : POST, /items
        - 상품 목록 조회 : GET, /items
        - 개별 상품 정보 상세 조회 : GET, /items/{item}
        - 상품 정보 수정 : PATCH, /items/{item}
        - 상품 삭제 : DELETE, /items/{item}
    - **주문 기능**
        - 주문 정보 생성 : POST, /orders
        - 주문 목록 조회 : GET, /orders
        - 개별 주문 정보 상세 조회 : GET, /orders/{order}
        - 주문 취소 : PATCH, /orders/{order}
