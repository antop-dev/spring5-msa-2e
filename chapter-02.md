# 2장. 마이크로서비스 관련 아키텍처 스타일 및 사례

* 서비스 지향 아키텍처 <sup>`SOA`, `Service Oriented Architecture`</sup>
* 12 요소<sup>`12 factor`</sup> 애플리케이션
* 서버리스<sup>`serverless`</sup> 컴퓨팅
* 람다<sup>`lambda`</sup> 아키텍처
* 데브옵스, 클라우드, 컨테이너
* 리액티브 마이크로 서비스
* 마이크로서비스 사용 사례

## 12 요소<sup>`12 factor`</sup> 애플리케이션과의 관계

허로쿠<sup>`Heroku`</sup>가 제시한 클라우드 기반 서비스를 목표로 하는 애플리케이션 개발에 적용할 수 있는 몇가지 소프트웨어 엔지니어링 원칙

### `1` 단일 코드 베이스

각 애플리케이션이 하나의 코드 베이스만을 가져야 한다고 권장한다.

[`Git`](https://git-scm.com/)
[`Spring Profiles`](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-profiles.html)

### `2` 의존성 꾸러미<sup>`bundling dependencies`</sup>

모든 애플리케이션은 필요한 모든 의존성을 애플리케이션과 함께 하나의 꾸러미에 담아야 한다.

[`Maven`](https://maven.apache.org/)
[`Gradle`](https://gradle.org/)

### `3` 환경설정 외부화<sup>`externalizing configurations`</sup>

![Imgur](https://i.imgur.com/kqgZfGA.png)

모든 환경설정 파라미터를 코드와 분리해서 외부화하라고 권고한다.

[`Spring Cloud Config Server`](https://cloud.spring.io/spring-cloud-config/multi/multi__spring_cloud_config_server.html)

### `4` 후방 지원 서비스 접근성

![Imgur](https://i.imgur.com/tqToPSz.png)

모든 후방 지원 서비스<sup>`backing service`</sup>는 URL을 통해 접근 가능해야 한다.

### `5` 빌드, 출시, 운영의 격리

![Imgur](https://i.imgur.com/bgo6j1Q.png)

빌드, 출시, 운영 단계를 뚜렷하게 격리하는 것이 좋다고 한다. 빌드, 출시, 운영 단계로 이뤄진 파이프라인은 **일방향**이다.

### `6` 무상태<sup>`stateless`</sup>, 비공유 프로세스<sup>`shared nothing processes`</sup>

프로세스들이 상태가 없어야 하고 아무것도 공유하지 않는 것이 좋다고 한다.

상태를 저장해야 하는 요구 사항이 있다면 데이터베이스나 인메모리 캐시 같은 후방 지원 서비스에서 처리돼야 한다.

[`Spring Session`](https://spring.io/projects/spring-session)
[`Redis`](https://redis.io/)
[`MariaDB`](https://mariadb.org/)

### `7` 서비스를 포트에 바인딩해서 노출

![Imgur](https://i.imgur.com/GQCuUZk.png)

12 요소 애플리케이션은 자기 완비적이거나 독립 설치형<sup>`standalone`</sup>이어야 한다. 톰캣<sup>`tomcat`</sup>이나 제티<sup>`jetty`</sup>같은 HTTP 리스너는 서비스나 애플리케이션 자체에 **내장**돼야 한다.

포트 바인딩<sup>`port binding`</sup>은 마이크로 서비스가 자율적이고 자기 완비적인 특성을 유지하는데 필요한 기본적인 요구 사항 중 하나다. 

### `8` 확장을 위한 동시성

복제<sup>`replicating`</sup>를 통해 프로세스가 확장될 수 있게 설계해야 한다고 권고한다.

마이크로서비스 세상에서는 서비스가 서버의 자원을 늘리는 수직적 확장<sup>`scale up`</sup>이 아니라 서버의 수를 늘리는 수평적 확장<sup>scale out</sup> 방식으로 확장된다.

### `9` 폐기 영향 최소화<sup>`disposability with minimal overhead`</sup>

애플리케이션의 시동과 종료에 필요한 시간을 최소화 하고, 서버가 조욜될 때에는 종료에 필요한 작업이 모두 수행되는 우아한 방식으로 종료되게 만들어야 한다고 권고한다.

`Lazy loading`

### `10` 개발과 운영의 짝 맞춤<sup>`Development and production parity`</sup>

개발 환경과 운영 환경을 가능한 한 동일하게 유지하는 것이 중요하다고 강조한다.

> 개발 환경과 운영 환경을 다르게 가져가는 것은 주로 인프라스트럭처에 드는 비용을 절감하는 데 목적이 잇지만, 운영 환경에서 장애가 발생할 경우 장애 해결을 위해 동일한 문제를 제연할 수 있는 동일한 환경이 없다는 맹정이 있다.

### `11` 로그 외부화

로그 파일을 절대로 자기 자신 안에 담지 않는다. 중앙 집중식 로깅 프레임워크를 사용하는 것이다.

[`ELK Stack`](https://www.elastic.co/kr/what-is/elk-stack)

### `12` 관리자 프로세스 패키징

애플리케이션 본연의 서비스와는 별개로 대부분의 애플리케이션에는 관리용 태스트도 포함된다.

[`Spring Boot Actuator`](https://www.baeldung.com/spring-boot-actuators)

## 마이크로서비스 사용 사례

모든 기업이 **일체형 애플리케이션으로 시작**했고, 일체형 애플리케이션 운영에서 맞닥뜨리는 한계에서 발견한 시사점을 토대로 마이크로서비스로 전환했다는 것이 공통점이다.

## 마이크로서비스 프레임워크

[`Spring Cloud`](https://spring.io/projects/spring-cloud)

![Imgur](https://i.imgur.com/sDCVlPS.png)

## References

* [12 FACTOR APP WITH DOCKER](https://www.slideshare.net/SASTREEPTIK/12-factor-app-with-docker)