## 렌터카 대여시스템 프로젝트
![예약조회](https://user-images.githubusercontent.com/96568009/174780808-c9c43e86-145d-45cf-8daf-925ede70f7b5.jpg)

## 개발목적
- 지금까지 학습한 자바와 데이터베이스의 이론을 활용하여 프로젝트를 진행함으로써 실력향상의 필요성을 느낌
- 효율적인 데이터베이스의 활용을 통해 렌터카 관리 시스템을 구현해보고자 함

## 개발인원 및 개발 기간
2022.06.14~2022.06.20

최지훈(본인): 프로젝트 총괄, 소스코드관리 및 유저페이지 기능 개발

이원걸: DB데이터 추가 및 수정, 관리자메뉴에서 조회기능 구현

오용택: DB데이터 설계 및 수정, 관리자메뉴의 수정기능 구현, 프로그램 테스트진행

강명범: 예약가능한 차량조회 및 차량예약기능 구현

## ERD   
논리ERD
![erd논리](https://user-images.githubusercontent.com/96568009/174785222-02376f7c-b30f-47f3-b4bb-9c40c2804e7c.jpg)

물리ERD
![erd물리](https://user-images.githubusercontent.com/96568009/174785370-78b91eef-6ae8-48ad-822c-19024a1ba02b.jpg)

## 메뉴 구성도
![메뉴구성도](https://user-images.githubusercontent.com/96568009/174785806-3a1996fc-427c-41aa-af7d-0a53e0cbe10b.jpg)

   
## 주요기능 설명 및 시연사진
- ### 회원가입
![회원가입](https://user-images.githubusercontent.com/96568009/174786211-34483ce6-9ac1-461b-8002-7eab42f141b6.jpg)
<br>
회원가입은 아이디, 비밀번호, 이름, 주민번호 앞자리, 휴대폰번호는 반드시 입력해야하고, 주소는 선택사항으로 하고 해당 정보를 바탕으로 회원가입을 진행한다.
아이디는 데이터베이스 조회를 통해 중복체크를 하고, 아이디와 비밀번호는 최대10글자로 공백을 허용하지 않는다.
- ### 로그인
![로그인](https://user-images.githubusercontent.com/96568009/174786363-1158dec7-c0e2-438a-8eb8-f1df0d0dc4df.jpg) 
<br>
데이터베이스 테이블에서 아이디와 비밀번호를 체크하여 일치하면 로그인이 이루어지고, 일치하지 않는다면 메인화면으로 돌아간다.
- ### 예약
![예약1](https://user-images.githubusercontent.com/96568009/174789488-3847f889-24e9-43fa-82e7-75b890531cc3.jpg)
![예약2](https://user-images.githubusercontent.com/96568009/174789500-d5868dfa-7334-4e01-9b19-4dfa93cdc1ed.jpg)
<br>
예약날짜와 반납일을 입력하게되면 해당날짜에 예약이 가능한 차량이 조회되고, 차량번호를 입력하면 예약이 이루어지게 된다.
<br>
단 예약은 2022년 한 해 기간중에만 가능하며, 현재시점보다 과거엔 예약을 할 수 없도록 개발
- ### 예약조회
![예약조회](https://user-images.githubusercontent.com/96568009/174789631-a64d77ff-993d-48a6-b0e0-8168d0cadc6f.jpg)
<br>
이너조인을 사용하여 예약테이블에 없는 지점명, 회원이름, 차 종도 함께 나오도록 함
- ### 회원정보수정
![회원정보수정](https://user-images.githubusercontent.com/96568009/174789727-b6944149-34ad-4b00-adde-580bb5bee7fd.jpg)
- ### 회원탈퇴
![회원탈퇴](https://user-images.githubusercontent.com/96568009/174791066-acc7d575-47c8-479f-9904-15827fbcbc03.jpg)
<br>
로그인 시점에서 아이디는 userDTO에 저장되어 있으므로 비밀번호만을 입력받아 확인한 후,
<br>
다시한번 확인을 거쳐 회원탈퇴가 이루어지도록 하였다.
- ### 관리자 로그인
![관리자로그인](https://user-images.githubusercontent.com/96568009/174791478-680b3d58-11b9-4fc1-be33-58f759694473.jpg)
<br>
admin계정을 관리자계정으로 하여 이 계정을 통해 로그인이 이루어지면 관리자 전용페이지로 이동하게 된다.
- ### 관리자 조회 및 수정기능
![관리자회원조회](https://user-images.githubusercontent.com/96568009/174791724-2ff6e949-78ba-48db-b286-c1f7632ed7b8.jpg)
![관리자회원정보수정](https://user-images.githubusercontent.com/96568009/174791970-f9eb9fac-bd2a-47de-81a2-b5db409c2092.jpg)
<br>
관리자는 회원, 예약, 지점, 차량 항목에 대하여 수정 및 전체조회 권한을 가지고 있다.
<br>
여기선 기능이 대부분 겹치기에 한가지씩 시연하여 기재하였다.
