# 7장. 스프링 클라우드 컴포넌트를 활용한 마이크로서비스 확장

* 환경설정 외부화를 담당하는 스프링 컨피그 서버<sup>`Config Server`</sup>
* 서비스 등록 및 탐색<sup>`discovery`</sup>
* 프록시 및 게이트웨이 역할을 담당하는 주울<sup>`Zuul`</sup>
* 마이크로서비스 자동 등록 및 서비스 탐색의 구현
* 비동기 리액티브 마이크로서비스 구성에 필요한 스프링 클라우드 메시징

※ 스프링 부트는 `2.1.8`, 스프링 클라우드는 `Greenwich.RELEASE` 버전을 사용한다. 책의 내용과 약간 다르다.

![https://i.imgur.com/hpWMMlZ.png](https://i.imgur.com/hpWMMlZ.png)

* [Appendix A. Common application properties](https://docs.spring.io/spring-boot/docs/2.1.8.RELEASE/reference/html/common-application-properties.html) 

## 스프링 클라우드란

[Spring Cloud](https://github.com/spring-cloud)

* 12 요소 원칙을 준수하면서 클라우드에 배포될 시스템을 개발할 때 필수적인 기능을 제공한다.
* AWS, Azure와 같은 클라우드 밴더의 제품에 종속적이지 않아서 여러 클라우드 서비스에 무리 없이 이관될 수 있다.
* 스프링의 '설정에 우선하는 관례' 접근 방식으로 만들어져서 기본 값으로 자동 구성돼 있기 때문에 개발자가 아주 금방 개발을 시작할 수 있게 도와준다.
* 다양한 요구 사항을 만족시킬 수 있는 많은 옵션을 제공한다. 여러 가지 기술 중에 **선택**해서 구현할 수 있다.

## 스프링 클라우드 컨피그

스프링 클라우드 컨피그<sup>`Spring Cloud Config`</sup> 서버는 애플리케이션과 서비스의 모든 환경설정 속성 정보를 저장하고, 조회하고 관리할 수 있게 해주는 **외부화된 환경설정 서버**다.

![https://i.imgur.com/nBx55rO.png](https://i.imgur.com/nBx55rO.png)

※ 자바 애플리케이션 실행 시 환경설정 파일 위치 저정을 통한 외부화

```bash
java -jar myproject.jar --spring.config.location=<file location>
```

스프링 부트와는 다르게 스프링 클라우드는 부트스트랩 컨텍스트<sup>`bootstrap.properties`</sup>를 이용한다.

※ Windows 환경에서 `jgit` 에러가 나서 [GitHub](https://github.com/antop-dev/spring5-msa-2e-config)를 사용했다.

```
java.lang.NullPointerException: null
	at org.eclipse.jgit.api.MergeCommand.call(MergeCommand.java:255) ~[org.eclipse.jgit-5.1.3.201810200350-r.jar:5.1.3.201810200350-r]
```

```
git clone https://github.com/antop-dev/spring5-msa-2e-config.git
echo message : helloworld > application.properties
git add -A
git commit -m "Added sample application.properties"
git push -u origin master
```

### 컨피그 서버 URL의 이해

```
http://localhost:8888/${app-name}/{profile}/{label}
```

1. 첫 번째 부분은 애플리케이션의 이름
2. 두 번째 부분은 프로파일
3. 세 번째 부분은 레이블<sup>`label`</sup>. 깃의 브렌치 이름이 레이블로 사용됨.

### 환경설정 정보 변경 전파 및 반영

`POST /{management.endpoints.web.base-path}/refresh` 종단점은 설정 정보를 컨피그 서버에서 가져온 값으로 갱신한다. 종단점에 아무 내용 없는 `POST` 요청을 보낸다.

### 환경설정 변경을 전파하는 스프링 클라우드 버스

![](https://i.imgur.com/DV8JqIi.png)

스프링 클라우드 버스<sup>`Spring Cloud Bus`</sup>는 현재 실행되고 있는 서비스 인스턴스의 수나 위치에 관계없이 환경설정 변경 내용이 모든 인스턴스에 적용되게 할 수 있다.

`POST/{management.endpoints.web.base-path}/bus-refresh` 종단점으로 요청을 보내면 된다. (스프링 클라우드 2.1.4 기준)

* [Spring Cloud Bus](https://cloud.spring.io/spring-cloud-bus/reference/html/)
* [spring cloud config server 및 bus rabbitMQ 동기화](https://handcoding.tistory.com/186)
* [spring cloud config client 및 bus rabbitMQ 동기화](https://handcoding.tistory.com/187)

### 컨피그 서버에 고가용성 적용

![](https://i.imgur.com/EKyjz6d.png)

컨피그 서버는 아키텍처 관점에서 보면 단일 장애 지점<sup>`single point of failure`</sup>이기 때문에 고가용성이 필요하다.

* 무정지 장애 대응<sup>`failover`</sup>
* 무정지 원상 복구<sup>`failback`</sup>
* [GitLab High Availability](https://about.gitlab.com/solutions/high-availability/)
* [Highly Available (Mirrored) Queues](https://www.rabbitmq.com/ha.html)

### 컨피그 서버 상태 모니터링

[Spring Boot Admin Reference Guide](https://codecentric.github.io/spring-boot-admin/current/)

![https://i.imgur.com/CcBUACO.png](https://i.imgur.com/CcBUACO.png)

### 컨피그 서버 환경설정

[How can I integrate Spring Cloud with logback?](https://stackoverflow.com/questions/42492763/how-can-i-integrate-spring-cloud-with-logback)

```yaml
logging:
  config: http://localhost:888/application/default/master/logback.xml
```

## 유레카를 이용한 서비스 등록 및 탐색

* 서비스 인스턴스의 개수와 관련된 서버의 개수를 동적으로 조절해야한다.
* 클라우드 배포 시나리오에서 IP 주소는 예측할 수 없으며, 파일에서 정적으로 관리하기 어렵다.

마이크로서비스가 자신의 서비스를 동적으로 등록함으로써 마이크로서비스 스스로 자신의 라이프사이클을 관리하게 해야 하며, 서비스가 등록되면 자동으로 사용자의 서비스 탐색 대상에 포함돼 발견되게 해야한다.

### 유레카 서버 구성

1. 클라이언트 디펜던시 추가

    ```xml
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    ```

2. `@EnableDiscoveryClient` 또는 `@EnableEurekaClient` 에노테이션 추가

    ```java
    @EnableDiscoveryClient
    @SpringBootApplication
    public class Application implements CommandLineRunner {
       
    }
    ```

3. `RestTemplate` 빈<sup>`bean`</sup> 생성 시 `@LoadBalanced` 에노테이션 추가

    ```java
    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    ```

4. 사용하는 부분에서 `hostname`에 서비스명을 사용

    ```java
    Flight[] flights = restTemplate.postForObject("http://search-service/search/get", searchQuery, Flight[].class);
    ```

※ 만약 `RestTemplate`을 사용하는 데에 유레카 기반과 일반 URL을 동시에 사용하려면 다른 `RestTemplate`를 사용해야 한다.

```java
@LoadBalanced
@Bean
RestTemplate restTemplate() { // hostname(application.name)으로 유레카로부터 주소를 찾는다
    return new RestTemplate();
}

@Bean(name = "rawRestTemplate")
RestTemplate rawRestTemplate() { // hostname을 그대로 사용
    return new RestTemplate();
}
```

* [Introduction to Spring Cloud Netflix – Eureka](https://www.baeldung.com/spring-cloud-netflix-eureka)
* [Service Discovery: Eureka Clients](https://cloud.spring.io/spring-cloud-netflix/multi/multi__service_discovery_eureka_clients.html)

### 고가용성 유레카 서버

![https://i.imgur.com/bmV6OxE.png](https://i.imgur.com/bmV6OxE.png)

1. 유레카 클라이언트는 주기적으로 서버의 정보를 체크해서 변경 사항을 로컬 캐시에 반영한다.
2. 유레카 서버가 장애로 인해 접근할 수 없는 상태가 돼도 유레카 클라이언트는 **기존 로컬 캐시에 저장돼 있는 내용을 기반으로 오류 없이 동작**한다.
3. 클라이언트가 최신 정보를 반영하지 않으므로 일관성 문제가 생길 수 있다.

유레카 서버는 P2P<sup>`peer-to-peer`</sup> 방식의 데이터 동기화 메커니즘을 바탕으로 만들어졌다. 하나 이상의 유레카 서버가 있다면 각 유레카 서버는 피어 관계에 있는 서버 중 최소한 하나의 서버와 연결돼야 한다.

이 둘은 서버인 동시에 서로 상태를 동기화하기 위해 서로를 바라보는 클라이언트이기도 하다.

![https://i.imgur.com/hmzXeTX.png](https://i.imgur.com/hmzXeTX.png)

## 주울 프록시 API 게이트웨이

주울 프록시는 내부적으로 서비스 탐색을 위해 유레카 서버를 사용하고, 서비스 인스턴스 사이의 부하 분산을 위해 리본<sup>`Ribbon`</sup>을 사용한다.

* 라우팅
* 모니터링
* 장애 복구 관리
* 보안
* API 계층에서 서비스의 기능을 재정의<sup>override`</sup>해서 뒤에 있는 서비스의 동작을 바꿈

### 주울 설정

리버스 프록시<sup>`reverse proxy`</sup>

* 인증이나 보안을 모든 마이크로서비스 종단점에서 각각 저용하는 대신 게이트웨이 한 곳에 적용한다.
* 비니지스 통찰<sup>`insights`</sup> 및 모니터링 기능도 게이트웨이 수준에서 구현될 수 있다.
* API 게이트웨이는 세밀한 제어를 필요로 하는 **동적 라우팅**에서도 매우 유용하다.
* 부하 슈레딩<sup>`shredding`</sup>이나 부하 스로틀링<sup>`throttling`</sup>이 필요한 사황에서도 유용하다.
* 데이터 집계가 필요한 상황에서도 유용하다.

### 고가용성 주울

주울은 단순히 HTTP 종단점을 갖고 있는 무상태 서비스이므로 원하는 만큼 많은 주울 인스턴스를 생성할 수도 있다.

여러 개의 주울 인스턴스에 대해 어떤 선호<sup>`affinity`</sup>나 접착성<sup>`stickiness`</sup>도 필요하지 않다.

* 클라이언트가 유레카 클라이언트이기도 할 때의 고가용성 주울

    ![https://i.imgur.com/oVSczXQ.png](https://i.imgur.com/oVSczXQ.png)

    유레카 클라이언트는 서비스 ID를 통해 주울 인스턴스를 식별해낼 수 있다.
    
    ```java
    Flight[] flights = restTemplate.postForObject("http://search-api-gateway/api/search/get", searchQuery, Flight[].class);
    ```

* 클라이언트가 유레카 클라이언트가 아닐 때의 고가용성 주울

    ![https://i.imgur.com/OKzH4Bn.png](https://i.imgur.com/OKzH4Bn.png)

    클라이언트가 유레카 클라이언트가 아니면 유레카 서버에 의한 부하 분산 처리를 쓸 수 없다.

* 다른 모든 브라운필드 마이크로서비스에 주울 적용

    ![https://i.imgur.com/xocagpt.png](https://i.imgur.com/xocagpt.png)
    
## 리액티브 마이크로서비스를 위한 스트림

[스프링 클라우드 스트림](https://docs.spring.io/spring-cloud-stream/docs/current/reference/htmlsingle/)<sup>`Spring Cloud Stream`</sup>은 메시징 인프라스트럭처 추상화 계층을 제공한다.

스프링 클라우드는 카프카<sup>`Kafka`</sup>에서 나오는 입력 스트림을 레디스<sup>`Redis`</sup>로 된 출력 스트림으로 보내는 것과 같이 하나의 애플리케이션에서 다수의 메시징 솔루션을 쉽게 사용할 수 있게 해주는 유연성을 제공한다.

![https://i.imgur.com/bFoobTG.png](https://i.imgur.com/bFoobTG.png)

## 스프링 클라우드 시큐리티를 활용한 마이크로서비스 보호

마이크로서비스에서는 많은 서비스가 배포되고 원격으로 연결하므로 인가되지 않은 접근으로부터 시스템을 보호하기가 더 어렵다.

* 게이트웨이를 경비병<sup>`watchdog`</sup>으로 두고 경계를 넘지 못하게 보안을 구현

    ![https://i.imgur.com/WtHg64N.png](https://i.imgur.com/WtHg64N.png)
    
* 네트워크를 격리하고 안전지대를 만들어서 서비스가 게이트웨이에만 열려 있게 하는 것.

    접근을 담당하는 소비자 주도<sup>`consumer-driven`</sup> 게이트웨이를 둔다.

    ![https://i.imgur.com/9Px2LZI.png](https://i.imgur.com/9Px2LZI.png)
    
* 토큰 릴레이<sup>`token relay`</sup>를 활용하는 방식

## 브라운필드 PSS 시스템 아키텍처 정리

아래와 같이 4개의 VM 서버에 구성을 해봤다.

![https://i.imgur.com/ectbhKr.png](https://i.imgur.com/ectbhKr.png)

포트 구성

| 서비스 | pss-1 | pss-2 | pss-3 | pss-4 |
|---|---|---|---|---|
| spring-boot-admin | - | -| - | 9000 | 
| nginx → config-server | 8880 | - | - | - |
| config-server | 8888 | 8889 | - | - |
| nginx → eureka-server | - | 8760 | - | - |
| rabbitmq-server | - | - | 5672 | - |
| eureka-server | - | 8761 | 8762 | - |
| book-api-gateway | 8065 | - | - | - |
| fares-api-gateway | - | 8075 | - | - |
| checkin-api-gateway | - | - | 8085 | - |
| search-api-gateway | - | - | - | 8095 |
| book-service | 8060 | 8060 | 8060 | 8060 |
| fares-service | 8070 | 8070 | 8070 | 8070 |
| checkin-service | 8080 | 8080 | 8080 | 8080 |
| search-service | 8090 | 8090 | 8090 | 8090 |
| test-client (website) | - | -| - | 8001 |

후기

* 컨피그 서버와 유레카 서버의 상태(?)가 매우 중요하다.
* 설정을 컨피그 서버 저장소/로컬 설정 파일 중 어디에 위치해야 하는가?
* 로그 관리(추적)가 안된다.
* CI/CD의 필요가 절실하다.

### Issue

* [RabbitMQ 외부에서 guest 계정 접속 가능하게 하기](https://stackoverflow.com/questions/26811924/spring-amqp-rabbitmq-3-3-5-access-refused-login-was-refused-using-authentica)

### History

모든 VM에서 수동으로 실행시켰다... 좀 힘들더라.. ㅎㅎ

```
# pss-1

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod config-server.jar \
  --spring.rabbitmq.host=pss-3 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-1:8888 \
  > config-server.log &

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod book-api-gateway.jar \
  --spring.cloud.config.uri=http://pss-1:8880 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-1:8065 \
  > book-api-gateway.log &

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod fares-service.jar \
  --spring.cloud.config.uri=http://pss-1:8880 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-1:8080 \
  > fares-service.log &

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod search-service.jar \
  --spring.cloud.config.uri=http://pss-1:8880 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-1:8090 \
  > search-service.log &

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod checkin-service.jar \
  --spring.cloud.config.uri=http://pss-1:8880 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-1:8070 \
  > checkin-service.log &

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod book-service.jar \
  --spring.cloud.config.uri=http://pss-1:8880 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-1:8060 \
  > book-service.log &

# pss-2

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod config-server.jar \
  --spring.rabbitmq.host=pss-3 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-2:8888 \
  > config-server.log &

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod eureka-server.jar \
  --spring.cloud.config.uri=http://pss-1:8880 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-2:8761 \
  --eureka.client.service-url.defaultZone=http://pss-3:8761/eureka/ \
  > eureka-server.log &

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod fares-api-gateway.jar \
  --spring.cloud.config.uri=http://pss-1:8880 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-2:8075 \
  > fares-api-gateway.log &

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod fares-service.jar \
  --spring.cloud.config.uri=http://pss-1:8880 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-2:8080 \
  > fares-service.log &

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod search-service.jar \
  --spring.cloud.config.uri=http://pss-1:8880 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-2:8090 \
  > search-service.log &

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod checkin-service.jar \
  --spring.cloud.config.uri=http://pss-1:8880 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-2:8070 \
  > checkin-service.log &

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod book-service.jar \
  --spring.cloud.config.uri=http://pss-1:8880 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-2:8060 \
  > book-service.log &

# pss-3

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod eureka-server.jar \
  --spring.cloud.config.uri=http://pss-1:8880 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-3:8761 \
  --eureka.client.service-url.defaultZone=http://pss-2:8761/eureka/ \
  > eureka-server.log &

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod checkin-api-gateway.jar \
  --spring.cloud.config.uri=http://pss-1:8880 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-3:8085 \
  > checkin-api-gateway.log &

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod fares-service.jar \
  --spring.cloud.config.uri=http://pss-1:8880 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-3:8080 \
  > fares-service.log &

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod search-service.jar \
  --spring.cloud.config.uri=http://pss-1:8880 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-3:8090 \
  > search-service.log &

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod checkin-service.jar \
  --spring.cloud.config.uri=http://pss-1:8880 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-3:8070 \
  > checkin-service.log &

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod book-service.jar \
  --spring.cloud.config.uri=http://pss-1:8880 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-3:8060 \
  > book-service.log &

# pss-4

nohup java -jar -Xms128M -Xmx128M -Drun.profiles=prod spring-boot-admin.jar > spring-boot-admin.log &

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod search-api-gateway.jar \
  --spring.cloud.config.uri=http://pss-1:8880 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-4:8095 \
  > search-api-gateway.log &

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod fares-service.jar \
  --spring.cloud.config.uri=http://pss-1:8880 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-4:8080 \
  > fares-service.log &

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod search-service.jar \
  --spring.cloud.config.uri=http://pss-1:8880 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-4:8090 \
  > search-service.log &

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod checkin-service.jar \
  --spring.cloud.config.uri=http://pss-1:8880 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-4:8070 \
  > checkin-service.log &

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod book-service.jar \
  --spring.cloud.config.uri=http://pss-1:8880 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-4:8060 \
  > book-service.log &

nohup java -jar -Xms128M -Xmx128M -Dspring.profiles.active=prod test-client.jar \
  --spring.cloud.config.uri=http://pss-1:8880 \
  --spring.boot.admin.client.url=http://pss-4:9000 \
  --spring.boot.admin.client.instance.service-base-url=http://pss-4:8001 \
  > test-client.log &


```