# MSA 테스트 구성도

![image](https://user-images.githubusercontent.com/57785267/168537209-d65ca27d-94e2-4e6e-b3c0-6c9d81636487.png)



#### Api 게이트웨이 service 역할

- 인증 및 권한 부여

- 서비스 검색 통합

- 응답 캐싱

- 정책 및 회로 차단기

- 속도 제한

- 부하 분산

- 로깅, 추적, 상관 관계

- 헤더, 쿼리 문자열 및 청구 변혼

- ip 허용 목록에 추가



#### 리버스 프록시 역할

다수의 클라이언트 요청을 서버로 전달할때, 부하를 분산하거나 보안을 강화하기 위한 목적으로 사용

#### spring-zuul 사용하려면

스프링 2.3x 버전 사용해야함



### Spring Cloud Gateway 필터 순서

> Gateway Client -> Gateway Handler -> Global Filter -> Custom Filter -> Logging Filter -> Proxied Service

#### 필터 사용이유?

> 클라이언트의 요청에 대한 로깅 작업, 부하를 분산하기 위한 라우팅, 인증에 대한 용도

> 도메인 종속적인 서비스들에 대한 처리를 일괄적으로 처리도 가능
