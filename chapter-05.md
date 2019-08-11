# 5장. 마이크로서비스 역량 모델<sup>`Microservice Capability`</sup>

마이크로서비스를 성공적으로 구축하려면 생태계 차원에서의 역량이 필요하며, 이런 역량을 전제 조건으로 갖출 수 있게 보장하는 것이 중요하다.

* 마이크로서비스 생태계 역량 모델
* 각 역량에 대한 개요 및 마이크로서비스 생태계에서 해당 역량의 중요성
* 각 역량을 지원하는 도구와 기술에 대한 개요
* 마이크로서비스 성숙도 모델

## 마이크로서비스 역량 모델

오픈 그룹<sup>`Open Group`</sup>이 정의한 포괄적인 [SOA 참조 아키텍처](http://www.opengroup.org/soa/source-book/soa_refarch/index.htm)가 공개돼 있다

역량 모델은 크게 네 개의 영역로 분류할 수 있다.

![Imgur](https://i.imgur.com/FEPcczy.png)

* 핵심 역량<sup>`Core capabilities`</sup>
* 지원 역량<sup>`Supporting capabilities`</sup>
* 인프라스트럭처 역량<sup>`Infrastructure capabilities`</sup>
* 프로세스 및 통제 역량<sup>`Process & Governance capabilities`</sup>

## 핵심 역량

핵심 역량<sup>`Core capabilities`</sup>은 하나의 마이크로서비스 안에 패키징되는 컴포넌트다.

### 서비스 리스너와 라이브러리

서비스 리스너는 마이크로서비스로 들어오는 서비스 요청을 접수하는 종단점<sup>`end-point`</sup> 리스너다.

* HTTP
* 카프카<sup>`Kafka`</sup>
* 래빗엠큐<sup>`RabbitMQ`</sup>

### 저장 기능

마이크로서비스는 상태나 트랜잭션 데이터를 적절한 비지니스 범위에 맞게 저장하는 일종의 저장 메커니즘을 갖고 있다.

* RDBMS: MySQL, MariDB, Oracle, PostgresSQL
* NoSQL: [하둡<sup>`Hadoop`</sup>](https://hadoop.apache.org/), [카산드라<sup>`Cassandra`</sup>](http://cassandra.apache.org/), [네오포제이<sup>`Neo4J`</sup>](https://neo4j.com/), [일래스틱서치<sup>`ElasticSearch`</sup>](https://www.elastic.co/kr/products/elasticsearch)
* 인메모리 저장 캐시: [이에이치캐시<sup>`Ehcache`</sup>](https://www.ehcache.org/), [헤이즐캐스트<sup>`Hazelcast`</sup>](https://hazelcast.com/), [인피니스팬<sup>`Infinispan`</sup>](https://infinispan.org/)
* 인메모리 테이터베이스: [솔리드DB<sup>`solidDB`</sup>](https://www.teamblue.unicomsi.com/products/soliddb/), [오라클 타임스텐<sup>`TimesTen`</sup>](https://www.oracle.com/database/technologies/related/timesten.html)

### 서비스 구현

서비스 구현<sup>`Service implementation`</sup>은 마이크로서비스의 핵심으로, 비지니스 로직이 구현되는 곳이다. 어떤 언어로도 구현될 수 있다.

### 서비스 종단점

서비스 종단점<sup>`end-point`</sup>은 외부의 서비스 소비자가 서비스에게 요청을 전송할 수 있게 외부에 공개한 API를 말한다.

* 동기 방식: REST/JSON, 아브로<sup>`Avro`</sup>, 스프리트<sup>`Thrift`</sup>, 프로토콜 버퍼<sup>`Protocol Buffers`</sup>
* 바동기 방식: AMQP, Stream

## 인프라스트럭처 역량

적절한 인프라스트럭처 역량을 갖추지 못한 채로 대규모의 마이크로서비스를 배포하면 여러 문제에 직면할 수 있으며, 실패로 이어질 수도 있다.

### 클라우드

마이크로서비스에는 가상머신이나 컨테이너를 자동으로 프로비저닝할 수 있는 탄력적인 클라우드 방식의 인프라스트럭처가 적합하다.

* [AWS<sup>`Amazon Web Services`</sup>](https://aws.amazon.com/ko/)
* [애저<sup>`Azure`</sup>](https://azure.microsoft.com/ko-kr/)
* [IBM 블루믹스<sup>`Bluemix`</sup>](https://www.ibm.com/kr-ko/cloud/info/bluemix-now-ibm-cloud)

### 컨테이너 오케스트레이션

컨테이너 오케스트레이션 도구는 컨테이너 런타임 위에서 일관성 있는 운영 환경을 제공하며, 가용한 자원을 여러 컨테이너에게 분배한다.

* [아파치 메소스<sup>`Mesos`</sup>](http://mesos.apache.org/)
* [랜처<sup>`Rancher`</sup>](https://rancher.com/)
* [코어OS<sup>`CoreOS`</sup>](https://coreos.com/)
* [**쿠버네티스**<sup>`Kubernetes`</sup>](https://kubernetes.io/ko/)

## 지원 역량

지원 역량<sup>`Supporting Capabilities`</sup>은 마이크로서비스와 직접적으로 연결되지는 않지만, 대규모 마이크로서비스 배포에 필수적이다. **마이크로서비스의 실제 운영 런타임에서는 지원 역량에 대한 의존 관계가 발생한다.**

### 서비스 게이트웨이

서비스 게이트웨이(API 게이트웨이)는 서비스 종단점에 대한 프록시 역할이나 여러개의 종단점을 조합하는 역할을 담당하면서 간접화 계층을 제공한다. API 게이트웨이는 **정책의 강제 적용**이나 **라우팅**에도 자주 사용되며, 상황에 따라 **실시간 로드밸런싱**에도 사용될 수 있다.

* [**스프링 클라우드 주울**<sup>`Zuul`</sup>](https://cloud.spring.io/spring-cloud-netflix/reference/html/#_router_and_filter_zuul)
* [Kong](https://konghq.com/kong/)

### 소프트웨어 정의 로드 밸런서

새로운 서버가 운영 환경에 추가되면 자동으로 감지해서 수작업 없이 논리적인 클러스터에 추가돼야 한다. 마찬가지로 서비스 인스턴스가 서비스 불가 상태가 되면 그 인스턴스는 로드 밸런서의 부하 분산 대상에서 제외돼야 한다.

* 스프트웨어 정의 로드 밸런서<sup>`Software defined load balancer`</sup>: 스프링 클라우드 (리본<sup>`Ribbon`</sup>, 유레카<sup>`Eureka`</sup>, 주울<sup>`Zuul`</sup>)
* 컨테이너 오케스트레이션 도구도 로드 밸런싱 기능을 제공

### 중앙 집중형 로그 관리

마이크로서비스를 구현할 때는 각 서비스에서 생성되는 로그를 중앙의 로그 저장수에 적재할 수 있어야 한다.

1. 로그가 로컬 디스크나 로컬 I/O로 분산되지 않고 한곳에 모인다.
2. 로그 파일이 중앙의 한곳에서 관리되므로 이력, 실시간, 트랜드 등 다양한 분석을 수행수 있다.
3. 연관 관계 ID<sup>`correlation ID`</sup>를 사용하면 트랜잭션의 전 구간을 쉽게 추척할 수 있다.

로그 적재기
* [Logstash](https://www.elastic.co/kr/products/logstash)
* [Fluentd](https://www.fluentd.org/)
* [LogSpout](https://github.com/gliderlabs/logspout)

로그 스트림 처리기
* [Spring Cloud Stream](https://spring.io/projects/spring-cloud-stream)
* [Spring Cloud Data Flow](https://spring.io/projects/spring-cloud-dataflow)
* [Flume](https://flume.apache.org/)
* [Kafka](https://kafka.apache.org/)
* [Storm](https://storm.apache.org/) 
* [Spark Streaming](https://spark.apache.org/streaming/)

로그 저장소
* [ElasticSearch](https://www.elastic.co/kr/products/elasticsearch)

대시보드
* [Kibana](https://www.elastic.co/kr/products/kibana)
* [Graphite](https://graphiteapp.org/)
* [Grafana](https://grafana.com/)


### 서비스 탐색

대규모 마이크로서비스에는 서비스가 실행되는 위치를 자동으로 찾을 수 있는 메커니즘이 필요하다.

서비스 레지스트리는 서비스가 요청을 처리할 준비가 됐음을 운영 환경에 알려줄 수있다. 레지스트리는 어디에서나 서비스 토폴로지를 이해하는 데 필요한 정보를 제공해주며, 서비스 소비자는 레지스트리에서 서비스를 탐색하고 찾을 수 있다.

* [스프링 클라우드 유레카<sup>`Eureka`</sup>](https://github.com/Netflix/eureka)
* [주키퍼<sup>`ZooKeeper`</sup>](https://zookeeper.apache.org/)
* [Etcd](https://github.com/etcd-io/etcd)

### 보안 서비스

분산 마이크로서비스 생태계에서는 서비스 인증이나 토큰 서비스 같은 서비스 보안을 담당할 중앙의 서버를 필요로 한다.

* [스프링 시큐리티<sup>`Spring Security`</sup>](https://spring.io/projects/spring-security)
* [핑<sup>`Ping`</sup>](https://www.pingidentity.com/en/platform/single-sign-on/sso-overview.html)
* [옥타<sup>`Okta`</sup>](https://www.okta.com/)

### 서비스 환경설정

마이크로서비스에서는 12 요소 애플리케이션에서 살펴봤던 것처럼 **서비스 환경설정 정보가 모두 외부화**돼야 한다. 중앙의 서비스를 두고 한곳에서 모든 환경설정 정보를 관리하는 것이 좋다.

* [스프링 클라우드 컨피그<sup>`Config`</sup> 서버](https://cloud.spring.io/spring-cloud-config/reference/html/)
* [아카이우스<sup>`Archaius`</sup>](https://github.com/Netflix/archaius)

### 운영 모니터링

마이크로서비스를 위한 인프라스트럭처를 관리하는 데는 **강력한 모니터링 역량**이 필요하다.

* 스프링 클라우드 넷플릭스: [터바인<sup>`Turbine`</sup>](https://github.com/Netflix/Turbine/wiki), [히스트릭스 대시보드<sup>`Hystrix Dashboard`</sup>](https://cloud.spring.io/spring-cloud-netflix/reference/html/#_circuit_breaker_hystrix_dashboard)
* [앱 다이내믹<sup>`AppDynamic`</sup>](https://www.appdynamics.com/)
* [뉴 렐릭<sup>`New Relic`</sup>](https://newrelic.com/)
* [다이나트레이스<sup>`Dynatrace`</sup>](https://www.dynatrace.com/ko/)
* [Datadog](https://www.datadoghq.com/)
* [센수<sup>`Sensu`</sup>](https://sensu.io/)

### 의존 관계 관리

지나치게 많은 의존 관계는 마이크로서비스에서 문제가 될 가능성이 높다.

* 서비스 경계를 적절히 설계해서 의존 관계를 낮춘다.
* 가능한 한 느슨한 결합을 사용하게 설계해서 영향 여파를 줄인다.
* 서킷 브레이커<sup>`circuit breaker`</sup> 같은 패턴을 사용해서 의존 관계로 인한 문제가 확산되는 것을 막는다.
* 의존 관계를 시각화해서 모니터링한다.

운영 모니터링 도구

* [앱 다이내믹<sup>`AppDynamic`</sup>](https://www.appdynamics.com/)
* [클라우드 크래프트<sup>`Cloud Craft`</sup>](https://cloudcraft.co/) : Create smart AWS diagrams

### 데이터 호수

데이터가 파편화되면 각 데이터가 서로 동떨어져 연결될 수 없는 이질적인 데이터 섬<sup>`data island`</sup>이 돼 버린다.

* [스프링 클라우드 데이터 플로우<sup>`dataflow`</sup>](https://spring.io/projects/spring-cloud-dataflow)
* [아파치 카프카<sup>`Kafka`</sup>](https://kafka.apache.org/)
* [플링크<sup>`Flink`</sup>](https://flink.apache.org/)
* [플룸<sup>`Flume`</sup>](https://flume.apache.org/)

### 신뢰성 메시징

마이크로서비스에는 리액티브 스타일을 사용하는 것이 좋다. 리액티브 시스템에서는 신뢰성 있는 고가용성 메시징 인프라스트럭처 서비스가 필요하다.

* [**래빗엠큐**<sup>`RabbitMQ`</sup>](https://www.rabbitmq.com/)
* [아파치 액티브엠큐<sup>`ActiveMQ`</sup>](https://activemq.apache.org/)
* [아파치 카프카<sup>`Kafka`</sup>](https://kafka.apache.org/)
* [IBM MQ](https://www.ibm.com/support/knowledgecenter/ko/SSFKSJ/com.ibm.mq.helphome.doc/product_welcome_wmq.htm)
* [TIBCO EMS<sup>`Enterprise Message Service`</sup>](https://www.tibco.com/ko/products/tibco-enterprise-message-service)

## 프로세스 및 통제 역량

프로세스 및 통제 역량은 마이크로서비스 구현에 필요한 프로세스, 두고, 가이드라인을 의미한다.

### 데브옵스

마이크로서비스 구현에서 가장 풀기 어려운 문제는 조직 문화라고 할 수 있다.

* 애자일 개발 프로세스
* 지속적 통합
* 자동화된 QA<sup>`Quality Assurance`</sup>
* 자동화된 전달 파이프라인
* 자동화된 배포
* 자동화된 인프라스트럭처 프로비저닝

### 자동화 도구

애자일 개발, 지속적 통합, 지속적 전달, 지속적 배포를 통해 마이크로서비스를 성공적으로 전달하려면 자동화 도구가 필수적이다.

테스트 자동화
* 서비스 가상화<sup>`service virtualization`</sup>와 모조 서비스<sup>`service mocking`</sup> 기법
* 실제 사용자 테스트
* 종합 테스트
* 통합 테스트
* 출시 테스트
* 성능 테스트
* 붕괴 테스트<sup>`destructive`</sup>

지속적 전달
* A/B 테스트
* 기능 플래그<sup>`Feature flag`</sup>
* 카나리아 테스트,
* 블루-그린 배포
* 레드-블랙 배포

### 컨테이너 레지스트리

버전 관리되는 마이크로서비스의 바이너리를 마이크로서비스 저장소에 저장한다.

1. 아티팩토리<sup>`artifactory`</sup> 저장소: [JFrog Artifactory](https://jfrog.com/artifactory/)
2. 컨테이너 저장소: [도커 허브<sup>`Docker Hub`</sup>](https://hub.docker.com/), [구글 컨테이너 저장소<sup>`Google Container Repository`</sup>](https://cloud.google.com/container-registry/), [코어OS 키이<sup>`CoreOS Quay`</sup>](https://quay.io/), [아마존 EC2 컨테이너 레지스트리](https://aws.amazon.com/ko/ecr/)

### 마이크로서비스 문서화

마이크로서비스는 전통적인 SOA 통제와는 다르게 탈중화된 통제 방식을 취한다. 반드시 고려해야 할 가장 중요한 사항 중 하나는 모든 이해 관계자가 모든 서비스와 문서, 계약, 서비스 수준 협약을 볼 수 있어야 한다는 점이다.

* [스웨거<sup>`Swagger`</sup>](https://swagger.io/)
* [RAML<sup>`RESTful API Modeling Language`</sup>](https://raml.org/)
* [API 블루프린트<sup>`Blueprint`</sup>](https://apiblueprint.org/)

### 참조 아키텍처 및 라이브러리

탈중화된 통제 방식으로 개발되는 마이크로서비스는 각자 서로 다른 패턴, 도구, 기술을 사용할 수 있는데, 이런 점이 지나치면 비용 효율성이 떨어지고 소비스의 재사용도 어려워질 수 있다.

* 참조 아키텍처: 여러 개의 **재사용 가능한 라이브러리**로 나눠질 수 있다.
* 표준화된 도구 사용: 서로 다른 방식으로 개발돼 서로 상호작용이 되지 않는 불상사를 막아준다.

## 마이크로서비스 성숙도 모델

![Imgur](https://i.imgur.com/KDvrvoE.png)

## 마이크로서비스 도입을 위한 진입점

1. 그린 필드 접근 방식: 새로운 기능 개발을 위해 마이크로서비스를 사용한다.
    
    ![Imgur](https://i.imgur.com/mAAQYWy.png)
    
2. 브라운 필드 접근법: 일체형 애플리케이션을 마이크로서비스로 전환하는 데 사용하는 방식이다.

    ![Imgur](https://i.imgur.com/9sjTYbd.png)