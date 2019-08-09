*기획*
내가 원하는 사이트에 정보 가져와서 새로운 정보들을 타임라인 형식으로 보여준다. (요청에 따라 추가 예정)

## 채용공고
* 당근마켓
* 배달의민족
* 카카오톡
* VCNC
* 마이리얼트립


## 테크 
* 우아한형제들
* spring tech
* zoom
* VCNC


## 시스템
* sh공사
*배치 주기*
* 매일 저녁 12시와 오후 00시 10분에 스케쥴링 되며, 이미 존재하는 데이터는 덮어쓰고 없는 데이터는 새로 추가한다.


*캐시저장*
* 정보는 mariadb에 저장하고 저장된 정보는 12시간 주기로 redis에 저장하고 그 저장된 데이터를 가져온다.
* redis는 cluster모드가 아닌 단일 모드로 동작한다. (jedis대신 lecttuce 사용)


*kafka*
* 배치에서 크롤링된 데이터는 kafka에 저장하고 저장된 데이터를 꺼내서 mariadb에 저장


## 기술스택
* java 1.8 (11은 서버 문제로 불가)
* spring boot 2.1.5
* spring batch
* mariadb
* redis
* gradle
* kafka


## 화면 구성
* front는 nginx로 정적 페이지 접근하는 방식으로 사용한다.
* api 호출 방식으로 데이터를 가져오며 구성은 vue.js를 사용한다.
https://github.com/weduls/wedul_timeline_m
