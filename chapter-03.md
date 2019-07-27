# 스프링 부트로 만드는 마이크로서비스

강력한 스프링 부트<sup>`Spring Boot`</sup> 프레임워크 덕분에 마이크로서비스를 만드는 일은 더 이상 지겹고 따분한 일이 아니다.

* 최신의 스프링 개발 환경 구축
* 스프링 프레임워크 5와 스프링 부트를 활용한 RESTful 서비스 개발
* 스프링 웹플럭스<sup>`WebFlux`</sup>와 스프링 메시징을 이용한 리액티브 마이크로서비스 개발
* 스프링 시큐리티와 OAuth2를 이용한 마이크로서비스 보안
* 크로스오리진<sup>`Cross-origin`</sup> 마이크로서비스 구현
* 스웨거<sup>`Swagger`</sup>를 이용한 마이크로서비스 문서화
* 스프링 부트 액추에이터

## 개발 환경 구축

툴 & 컴포넌트

* [IntelliJ IDEA](https://www.jetbrains.com/idea/)

    ![Imgur](https://i.imgur.com/ytaTwOH.png)

* [OpenJDK 1.8](https://github.com/ojdkbuild/ojdkbuild)

    ```
    openjdk version "1.8.0_191-1-ojdkbuild"
    OpenJDK Runtime Environment (build 1.8.0_191-1-ojdkbuild-b12)
    OpenJDK 64-Bit Server VM (build 25.191-b12, mixed mode)
    ```

* [Apache Maven 3.6.0](https://github.com/apache/maven)

    ```
    Apache Maven 3.6.0 (97c98ec64a1fdfee7767ce5ffb20918da4f719f3; 2018-10-25T03:41:47+09:00)
    Maven home: D:\dev\tools\apache-maven-3.6.0\bin\..
    Java version: 1.8.0_191-1-ojdkbuild, vendor: Oracle Corporation, runtime: D:\dev\sdk\openjdk-1.8.0.191-1.b12\jre
    Default locale: ko_KR, platform encoding: MS949
    OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
    ```

라이브러리 버전

* [Spring Boot 2.1.6](https://github.com/spring-projects/spring-boot) (or Spring Boot 1.5.21)

## 스프링 부트 RESTful 마이크로서비스 만들기

* 하나하나 직접 지정해야 하는 환경설정보다는 **관례**적으로 미리 정해진 접근 방식을 사용
* 80:20 원칙
    > 소프트웨어의 개발 80%를 20%의 기능을 개발하는데 소비한다. 프로그램 사용자의 20%가 80%의 부하를 발생시킨다. 등으로 사용이 된다. 프로젝트를 설계할 때 이 법칙을 감안해서 설계를 하면 프로젝트를 위험에 빠트리게 하는 위험요소들을 사전에 파악하고 관리가 가능할 것이다.
* 헬스 체크<sup>`health check`</sup>나 서비스 상태 지표 등 실제 서비스 운영에 필요한 부기 기능도 함께 제공
* 전통적으로 사용되던 대부분의 `XML` 설정을 제거
* 실행 시 의존하는 모든 라이브러리를 하나의 실행 가능한 JAR<sup>`Java Archive`</sup> 파일 안에 모두 패키징
    > 12 요소<sup>`12 factor`</sup> 애플리케이션 - 의존성 꾸러미<sup>`bundling dependencies`</sup>

## 스프링 부트 시작
* [스프링 부트 CLI<sup>`Command Line Interface`</sup>](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started-installing-spring-boot.html)
* IDE<sup>`Integrated Development Environment`</sup>
* [스프링 이니셜라이저<sup>`Spring Initializer`</sup>](https://start.spring.io/)
* [SDKMAN<sup>`Software Development Kit Manager`</sup>](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started-installing-spring-boot.html#getting-started-sdkman-cli-installation)

## 스프링 부트 마이크로서비스 개발

* 스프링 부트 CLI 사용
* 스프링 부트가 자동으로 톰캣을 웹서버로 선택하고 애플리케이션에 내장한다.

## 첫 번째 스프링 부트 마이크로서비스 개발 `chapter-03.boot-rest:301`

* spring-boot-starter-parent 패턴은 메이븐<sup>`Maven`</sup>의 의존성 관리에서 사용되는 BOM<sup>`bill of materials`</sup> 패턴이다.
* [starter POM](https://github.com/spring-projects/spring-boot/blob/master/spring-boot-project/spring-boot-dependencies/pom.xml) 파일은 그 자체로는 프로젝트에 JAR로 된 의존 관계를 추가하지 않고, 라이브러리의 버전만 추가한다.
* [Spring Boot Starter](https://github.com/spring-projects/spring-boot/tree/master/spring-boot-project/spring-boot-starters)
* [Using the @SpringBootApplication Annotation](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-using-springbootapplication-annotation.html)
* 자기 완비적이다.
* [회귀 테스트<sup>`regression test`</sup>](https://ko.wikipedia.org/wiki/%ED%9A%8C%EA%B7%80_%ED%85%8C%EC%8A%A4%ED%8A%B8)

## 스프링 부트 마이크로서비스에 HATEOAS 기능 추가 `chapter-03.boot-hateoas:302`

* [Glory of REST](https://jinson.tistory.com/190)

    ![](https://martinfowler.com/articles/images/richardsonMaturityModel/overview.png)

* HATEOAS<sup>`Hypermedia As The Engine Of Application State`</sup> : 내비게이션<sup>`navigation`</sup> 링크가 응답에 포함돼 제공되는 REST 서비스 패턴이다.
    > `Resource`로 `무엇`을 할 수 있는지 알 수 있다. `무엇`을 `어떻게` 해야 하는 지 알려준다.
    
    ```json
    {
      "id": 711,
      "manufacturer": "bmw",
      "model": "X5",
      "seats": 5,
      "drivers": [
        {
          "id": "23",
          "name": "Stefan Jauker",
          "links": [
            {
              "rel": "self",
              "href": "/api/v1/drivers/23"
            }
          ]
        }
      ]
    }
    ```

* HAL<sup>`Hypertext Application Language`</sup> 브라우저 : `hal+json` 데이터를 사용할 수 있는 작고 가벼운 브라우저다.   
    > http://haltalk.herokuapp.com/explorer/browser.html

## 리액티브 스프링 부트 마이크로서비스

리액티브 마이크로서비스는 기본적으로 마이크로서비스 생태계의 **비동기적 통합**이 필요하다고 강조한다.

* 스프링 웹플럭스<sup>`Spring WebFlux`</sup>를 사용
* 래빗엠큐<sup>`RabbitMQ`</sup>같은 메시징 서버를 사용

### 스프링 웹플럭스<sup>`Spring WebFlux`</sup> `chapter-03.boot-webflux:303`

스프링의 웹 리액티브 프레임워크는 [리액터<sup>`Reactor`</sup> 프로젝트](https://projectreactor.io)를 사용해서 리액티브 프로그래밍을 구현한다.

데이터를 받아가는 다운스트림 컴포넌트가 완전한 리액티브 프로그래밍을 지원하는지 반드시 확인해야 한다. 리액티브 프로그래밍의 진정한 힘을 제대로 사용하려면 리액티브 구조체가 클라이언트에서 뒷단의 리파지토리<sup>`repository`</sup>까지 전 구간을 흐를 수 있어야 한다.

스프링 웹플럭스의 구현 방식
* `@Controller` 등 일반적으로 스프링 부트에서 사용하는 애노테이션 방식
* 자바 8 스타일의 함수형 프로그래밍 방식 

리액티브 스트림의 이해
* 발행자<sup>`Publisher`</sup>
* 구독자<sup>`Subscriber`</sup>
* 프로세서<sup>`Processor`</sup>

### 래빗엠큐<sup>`RabbitMQ`</sup>

설치
* [Downloading and Installing RabbitMQ](https://www.rabbitmq.com/download.html)
* https://github.com/antop-dev/msa-example

※ RabbitMQ 연동시 아래와 같이 에러가 난다면 큐 생성시 `Durability`를 `Transient`로 설정하자.

```
Channel shutdown: channel error; protocol method: #method<channel.close>(reply-code=406, reply-text=PRECONDITION_FAILED - inequivalent arg 'durable' for queue 'TestQ' in vhost '/': received 'false' but current is 'true', class-id=50, method-id=10)
```

## 보안 구현

각 마이크로서비스에는 보안 처리가 돼야 하지만, 보안이 오버헤드로 작용해서는 안 된다.

### OAuth2를 사용하는 마이크로서비스 보안 `chapter-03.boot-security:305`

![Imgur](https://i.imgur.com/uI7rvHR.png)

※ 책의 예제를 하려면 1.5.x 버전을 사용해야 한다.

* [Spring Boot 2.x 버전에서 OAuth2가 안될때](https://hue9010.github.io/spring/OAuth2/)
* [Spring Security 5 - OAuth2 Login](https://www.baeldung.com/spring-security-5-oauth2-login)
* [Spring Boot 2 And OAuth 2 - A Complete Guide](https://pattern-match.com/blog/2018/10/17/springboot2-with-oauth2-integration/)

## 다른 도메인에 존재하는 정보 요청 활성화

[CORS<sup>`Cross-Origin Resource Sharing`, `타 도메인과의 자원 공유`</sup>](https://ko.wikipedia.org/wiki/%EA%B5%90%EC%B0%A8_%EC%B6%9C%EC%B2%98_%EB%A6%AC%EC%86%8C%EC%8A%A4_%EA%B3%B5%EC%9C%A0)

마이크로서비스 아키텍처에서는 각 마이크로서비스가 서로 다른 도메인에서 운영될 수 있으므로, 도메인 간 정보 요청 기능을 활성화해야 한다.

```java
@RestConTroller
@CrossOrigin // 클래스에 가능
class GreetingController {
    @CrossOrigin // 메소드에 가능
    @RequestMapping("/")
    Greet greet() {
        return new Greet("Hello World!");
    }
}
```

```java
@Configuration
@EnableWebMvc
class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 애플리케이션 전체에 걸쳐 효력을 미치는 CORS를 적용할 수 있다.
        registry.addMapping("/**");
    }
}
```

## 스프링 부트 액추에이터<sup>`SpringBoot Actuator`</sup> `chapter-03.boot-actuator:306`

스프링 부트 애플리케이션을 운영하고 관리하는 데 필요한 기능들을 아주 쓰기 쉬운 형태로 제공한다.

※ 책의 예제를 하려면 1.5.x 버전을 사용해야 한다.

* 사용자 정의 서버 진단 모듈 추가
* 사용자 정의 지표<sup>`metrics`</sup>

## 마이크로서비스 문서화 `chapter-03.boot-swagger:307`

* [Swagger](https://swagger.io/), [Swagger UI](https://swagger.io/tools/swagger-ui/)
  * [Swagger Annotations](https://github.com/swagger-api/swagger-core/wiki/annotations)
  * [Avoiding default basic-error-controller from swagger api
](https://stackoverflow.com/questions/33431343/avoiding-default-basic-error-controller-from-swagger-api)
  * [@ApiIgnore](http://springfox.github.io/springfox/javadoc/2.6.0/index.html?springfox/documentation/annotations/ApiIgnore.html)
* [DapperDox](https://github.com/DapperDox/dapperdox) : Swagger 기반 문서 생성
* [ReDoc](https://github.com/Redocly/redoc) : Swagger 기반 문서 생성 ([Demo](http://redocly.github.io/redoc/))
* [apiDoc](http://apidocjs.com/) : `javadoc 주석` + `@Annotation` 기반 문서 생성 ([Demo](http://apidocjs.com/example/))
* [Slate](https://github.com/lord/slate)
* [Whiteboard](https://github.com/mpociot/whiteboard)
* [Docbox](https://github.com/tmcw/docbox)

## 고객 등록 마이크로서비스 예제 `chapter-03.boot-customer:8080`

* [@RepositoryRestResource](https://docs.spring.io/spring-data/rest/docs/current/reference/html/#repository-resources.collection-resource)
* [Difference between `path` and `value` in spring boot RequestMapping](https://stackoverflow.com/questions/50351590/difference-between-path-and-value-in-spring-boot-requestmapping)
* `@RepositoryRestResource`를 `Swagger`로 문서화 하려면 디펜던시를 추가하고 약간의 설정을 하면 된다.

    ```xml
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-data-rest</artifactId>
    </dependency>
    ```

    ```java
    @Configuration
    @EnableSwagger2
    @Import(SpringDataRestConfiguration.class) // this
    public class SwaggerConfig {
  
    }
    ```

    > `Swagger 2.9.2`와 `Spring Boot 2.1.6` + `Spring Data 2.1.9` 라이브러리 버전 이슈가 있다.
    >
    > ```java
    > // 두 버전의 메소드 명세가 다르다.
    > public class Repositories {
    >     // 1.3.22
    >     public RepositoryInformation getRepositoryInformationFor(Class<?> domainClass) { }
    >     // 2.1.9
    >     public Optional<RepositoryInformation> getRepositoryInformationFor(Class<?> domainClass) { }
    > }
    > ``` 
    >
    > 아래는 에러 로그
    >
    > ```
    > ***************************
    > APPLICATION FAILED TO START
    > ***************************
    > 
    > Description:
    > 
    > An attempt was made to call a method that does not exist. The attempt was made from the following location:
    > 
    >     springfox.documentation.spring.data.rest.EntityServicesProvider.requestHandlers(EntityServicesProvider.java:81)
    > 
    > The following method did not exist:
    > 
    >     org.springframework.data.repository.support.Repositories.getRepositoryInformationFor(Ljava/lang/Class;)Lorg/springframework/data/repository/core/RepositoryInformation;
    > 
    > The method's class, org.springframework.data.repository.support.Repositories, is available from the following locations:
    > 
    >     jar:file:/C:/Users/antop/.m2/repository/org/springframework/data/spring-data-commons/2.1.9.RELEASE/spring-data-commons-2.1.9.RELEASE.jar!/org/springframework/data/repository/support/Repositories.class
    > 
    > It was loaded from the following location:
    > 
    >     file:/C:/Users/antop/.m2/repository/org/springframework/data/spring-data-commons/2.1.9.RELEASE/spring-data-commons-2.1.9.RELEASE.jar
    > 
    > 
    > Action:
    > 
    > Correct the classpath of your application so that it contains a single, compatible version of org.springframework.data.repository.support.Repositories
    > ```
    > `Spring Boot 1.5.21` + `Spring Data 1.3.22`를 사용하면서 `Swagger` 버전이 업그레이드 되기를 기다리면 된다...ㅠㅠ

* [FakeSMTP](http://nilhcem.com/FakeSMTP/)

## Reference

* [프로그램 관련 3가지 법칙 : 파레토 법칙, 디미터 법칙, 콘웨이 법칙](https://hongjinhyeon.tistory.com/138)