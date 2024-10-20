# Backend
> - 각 업체에서 나오는 데이터를 서버에 보내고 그 데이터를 저장하는 서버
<br/>

## Summary
> - 애플리케이션 이름: godata
> - 개발 환경: IntelliJ IDEA
> - API 테스트 도구: Postman
> - 빌드 도구: Gradle
> - 협업 도구: GitHub
> - 자바 버전: 17


## 🛠️ Backend Tech Stack
<img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=white"><img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white"><img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"><img src="https://img.shields.io/badge/apachekafka-231F20?style=for-the-badge&logo=apachekafka&logoColor=white"><img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white"><img src="https://img.shields.io/badge/mongodb-47A248?style=for-the-badge&logo=mongodb&logoColor=white">
<br/>

## 주요기능
### 업체 데이터 저장
![gogogogogo](https://github.com/user-attachments/assets/9725a82b-6e15-4750-9d6d-e426b9fc9c95)
> 1. 기업에서 서버로 데이터를 전송
> 2. 들어온 데이터의 토큰을 통해 기업 검증
>    - cache hit: Redis에서 데이터 조회
>    - cache miss: MySQL에서 조회
> 3. 검증된 데이터를 Kafka 토픽으로 전송 
> 4. Kafka에서 데이터를 컨슘하여 MongoDB에 원본 데이터 저장, RDB에는 채널 설정 및 조회에 필요한 메타데이터 저장
# 전달 데이터 예시
데이터 전송 json
```json
{
  "type": "사용자 활동",
  "subtype": "클릭",
  "data": {
    "user_id": 12345,
    "timestamp": "2024-09-20T10:15:30Z",
    "page": "n번 게시글"

    },
"token":"12391293123"
}
```

## DBMS
### - Mysql
> - 각 업체의 데이터의 메타데이터를 저장하기위해서 mysql로 저장함 채널을 생성할때 저장된 메타데이터들로 채널을 생성할 수 있음 <br>
> - ex) type: 사용자 활동이고 subtype이 클릭이면 사용자활동과 클릭 그리고 dataset이 mysql에 저장되고 그 안에 다른 dataset이 존재하면 채널을 생성할때 type과 subtype dataset을 차례대로 맵핑시켜서 채널을 생성함 
### - Redis
> - 토큰으로 해당 회사를 조회 시 캐시에 데이터가 있으면 반환, 없을 경우 MySQL에서 조회 후 Redis에 저장

### - MongoDB
> - 각 업체의 데이터는 다 다를거라고 생각했기때문에 비정형데이터를 처리할수 있는 mogodb를 사용하기로 함 
<br/>

### kafka 
> - 동기로 처리하면 한번에 많은 요청이 들어왔을때 데이터를 저장하는 시스템이 너무 많은 부하가 일어날거라고 판단하였기때문에 비동기 처리를 할 수 있는 카프카를 사용하기로 결정하였음
> - 데이터를 서버에서 받을때 토큰으로 해당 회사가 존재하는지 검증한 후 kafka 토픽에 데이터를 보냄
> - 토픽에 들어있는 데이터를 컨슘해서 컨슘서버에서 데이터를 처리
> - 처리과정 </br>
>     1.데이터 안에 들어있는 type이 없으면 type , subtype , dataset을 rdb에 저장함 </br>
>     2.type이 있으면 subtype을 확인 없으면 subtype , dataset을 rdb에 저장함 </br>
>     3.type과 subtype이 이미 존재하면 dataset을 확인하고 없다면 저장하고 있으면 저장하지않음 </br>
>     4.mongodb에 데이터 저장 </br>

