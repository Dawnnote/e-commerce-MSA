#  E-Commerce Market

<img src="https://github.com/user-attachments/assets/bb072ed0-2b7e-4228-a4e2-dcefffebc68d" width="70%" height="70%"/>

<br/>


# 프로젝트 소개 <img src="https://img.shields.io/badge/2024.06 ~ 2024.07-515151?style=flat"> 

#### 이벤트 상품을 (선착순)구매 할 수 있는MSA기반의 E-commerce 서비스입니다.

<br/>

## ⚒ STACK

<div>
<img src="https://img.shields.io/badge/java-%23ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"><img src="https://img.shields.io/badge/17-515151?style=for-the-badge"><br/>
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white" /><img src="https://img.shields.io/badge/3.3.1-515151?style=for-the-badge">
<img src="https://img.shields.io/badge/Spring Cloud-6DB33F?style=for-the-badge&logo=Spring&logoColor=white">
<img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white" /><br/>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
<img src="https://img.shields.io/badge/Redis-FF4438?style=for-the-badge&logo=redis&logoColor=white"/>
<img src="https://img.shields.io/badge/Kafka-231F20?style=for-the-badge&logo=apachekafka&logoColor=white"/>
<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"/>
</div>

<br/>
<br/>

## Architecture

<img src="https://github.com/user-attachments/assets/015a89ec-6d24-488b-81f3-caa08eb45249" width="85%" height="85%"/>

- **Gateway**:
    - 클라이언트 요청을 받아서 각 마이크로서비스로 라우팅하는 역할
    - 클라이언트와 각 서비스 간의 진입점 역할

- **Netflix Eureka (Eureka Server)**:
    - 서비스 디스커버리 서버로, 각 마이크로서비스의 등록과 조회를 관리
    - 각 마이크로서비스는 이 서버에 자신의 정보를 등록하고, 다른 서비스의 위치를 조회

- **User-Service**:
    - 사용자 관련 데이터와 로직을 처리하는 마이크로서비스

- **Order-Service**:
    - 주문 관련 데이터와 로직을 처리하는 마이크로서비스

- **Product-Service**:
    - 제품 관련 데이터와 로직을 처리하는 마이크로서비스
    - 상품의 재고는 Redis 가 관리
 
- **Kafka (Messaging Channels)**:
    - 각 마이크로서비스 간의 메시징과 이벤트 스트리밍을 처리
    - 비동기 통신을 통해 서비스 간의 느슨한 결합을 유지

<br/>

## API 문서

[Postman API : e-commerce_msa-project](https://documenter.getpostman.com/view/26470751/2sA3kUFgqS)

<br/>

## 구매 데이터 흐름 설계

<img src="https://github.com/user-attachments/assets/eb40b77f-a2fd-4080-b35f-25b158fa72bb" width="80%" height="80%"/>

- **클라이언트**
    - 사용자가 웹사이트나 애플리케이션을 통해 상품 구매 요청
    - 상품 구매 요청은 POST 방식으로 API 서버에 전달

- **API 서버**
    - 클라이언트의 요청을 받아 처리하는 중심 서버
    - 요청을 받아 상품 정보를 조회하고, 재고를 확인한 후 결과를 응답

- **Kafka**
    
    - 메시지 브로커로, 상품 구매 요청을 비동기적으로 처리하기 위해 사용
    - 상품 저장 요청을 처리하고 관련 이벤트를 전송

- **데이터베이스 (DB)**
    - 상품 정보와 관련 데이터를 저장하는 데이터베이스
    - API 서버는 상품 정보를 조회하기 위해 DB와 상호 작용

- **Redis**
    - 캐시 시스템으로, 빠른 재고 확인을 위해 사용
    - API 서버는 Redis를 통해 상품 재고를 조회

<br/>

## ERD

<img src="https://github.com/user-attachments/assets/486f1404-776e-4283-95d4-592ebd9ee8b7" width="80%" height="80%"/>
