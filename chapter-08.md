# 8장. 마이크로서비스 로깅 및 모니터링

마이크로서비스는 분산 환경에서 운영된다는 특징 때문에 각각의 마이크로서비스에 대한 **로깅**과 **모니터링**이 큰 고민거리라고 할 수 있다.

* 로그 관리를 위한 다양한 옵션, 도구 및 기술
* 마이크로서비스의 추적성 확보를 위한 스프링 클라우드 슬루스<sup>`Spring Cloud Sleuth`</sup>
* 마이크로서비스의 전 구간 모니터링에 사용되는 다양한 도구
* 서킷<sup>`circuit`</sup> 모니터링을 위한 스프링 클라우드 히스트릭스<sup>`Spring Cloud Hystrix`</sup>와 터바인<sup>`Turbine`</sup>의 사용법
* 비지니스 데이터 본석을 가능하게 해주는 데이터 호수<sup>`data lake`</sup>의 사용법

## 로그 관리와 관련한 난제

마이크로서비스는 독립적인 물리적 장비 혹은 가상머신에서 운영되므로, 외부화하지 않는 로그 파일은 결국 각 마이크로서비스별로 파편화<sup>`fragmented`</sup>된다.

![로그 파편화](https://i.imgur.com/qLDB69R.png)

* 운영 모드에서는 불필요한 로그를 꺼두는 것이 가장 좋음
* 로그는 중요한 정보를 담고 있기도 하므로 적절히 분석한다면 높은 가치의 정보를 얻을 수 있음
* 12 요소 애플리케이션의 로그 외부화

## 중앙 집중형 로깅

* 모든 로그 메시지를 수집하고 분석
* 트랜잭션의 처음부터 끝가지 연결지어가며 추적
* 로그 정보를 오랜 기간 동안 보관
* 로컬 디스크 시스템에 대한 의존을 제거
* 여러 소스에서 오는 로그 정보를 수집

위 문제들을 해결하려면 로그의 출처와 관계없이 모든 로그를 중앙 집중적으로 저장하고 분석해야 한다.

![중앙 집중형 로깅](https://i.imgur.com/dR9G0GC.png)

중앙 집중형 방식의 장점은 로컬 I/O나 디스크 쓰기 블로킹<sup>`blocking`</sup>이 없으며, 로컬 장비의 디스크 공간을 사용하지 않는다.

각 로그 메시지에 컨텍스트, 메시지, 연관 ID<sup>`correlation ID`</sup>를 포함하는 것은 매우 중요하다.

## 로깅 솔루션

클라우드 서비스<sup>`p397`</sup>는 넘어가고 컴포넌트를 선택해 커스텀 로깅 솔루션을 구축하자.

### 최상의 조합

동급 최강의 컴포넌트를 선택해 커스텀 로깅 솔루션을 구축

#### 로그 스트림 <sup>`log stream`</sup>

로그 생산자가 만들어내는 로그 메시지의 스트림

* [Logstash Reference - Input plugins](https://www.elastic.co/guide/en/logstash/current/input-plugins.html)
* [Logstash Logback Encoder](https://github.com/logstash/logstash-logback-encoder) 

#### 로그 적재기 <sup>`log shipper`</sup>

서로 다른 로그 생산자나 종단점에서 나오는 로그 메시지의 수집을 담당한다.
 
 * [로그스태시<sup>`Logstash`</sup>](https://www.elastic.co/kr/products/logstash)
 * [플루언티드<sup>`Fluentd`</sup>](https://www.fluentd.org/)
 * [로그스파웃<sup>`LogSpout`</sup>](https://github.com/gliderlabs/logspout)
 
#### 로그 스트림 처리기

스트림 처리 기술은 필요에 따라 로그 스트림을 즉시 처리하는 데 사용된다.

* [Spark Streaming](https://spark.apache.org/streaming/)
* [Flink](https://flink.apache.org/)
* [Storm](https://storm.apache.org/)
* [Kafka](https://kafka.apache.org/)
* [Samza](http://samza.apache.org/)
* [Spring Cloud Stream](https://spring.io/projects/spring-cloud-stream)

#### 로그 저장소 <sup>`log store`</sup>

실시간 분석, 트랜드 분석 등을 위해 모든 로그 메시지를 저장하는 책임을 진다.

* [일래스틱서치<sup>`Elasticsearch`</sup>](https://www.elastic.co/kr/)
* [몽고디비<sup>`MongoDB`</sup>](https://www.mongodb.com/)
* [카산드라<sup>`Cassandra`</sup>](http://cassandra.apache.org/)
* [하둡<sup>`Hadoop`</sup>](https://hadoop.apache.org/)

#### 대시보드 <sup>`dashboard`</sup>

로그 분석 결과를 그래프나 차트로 한눈에 파악할 수 있게 해준다.

* [그래파이트<sup>`Graphite`</sup>](http://graphiteapp.org/)
* [그래파나<sup>`Grafana`</sup>](https://grafana.com/)
* [Kibana](https://www.elastic.co/kr/products/kibana)

### 사용자 정의 로깅 구현

사용자 정의 로그 관리에 가장 일반적으로 사용되는 아키텍처는 로그스태시, 일래스틱서치 및 키바나의 조합<sup>`ELK Stack`</sup>이다.

![ELK Stack](https://i.imgur.com/5GcEoUe.png)

도커 기반 설치

* [Get Docker Engine - Community for CentOS](https://docs.docker.com/install/linux/docker-ce/centos/)
* [Install Docker Compose](https://docs.docker.com/compose/install/)
* [Elasticsearch, Logstash, Kibana (ELK) Docker image](https://hub.docker.com/r/sebp/elk/) ← Docker 싱글 이미지
* [Elastic stack (ELK) on Docker](https://github.com/deviantony/docker-elk) ← Docker Compose 사용
* [ELASTICSEARCH 시스템 설정](http://linux.systemv.pe.kr/elasticsearch-%EC%8B%9C%EC%8A%A4%ED%85%9C-%EC%84%A4%EC%A0%95/)

```bash
# sebp/elk 싱글 이미지 사용

# Updating Logstash's configuration (p403 설정)
vi /opt/logstash/99-tcp.conf

# run
docker pull sebp/elk
docker run -d -p 5601:5601 -p 9200:9200 -p 5044:5044 -p 4560:4560 \
  -v /opt/logstash/99-tcp.conf:/etc/logstash/conf.d/99-tcp.conf \
  --name elk sebp/elk
```

독립형<sup>`standalone`</sup> 설치

* [How to Install ELK Stack on CentOS 7](https://computingforgeeks.com/how-to-install-elk-stack-on-centos-fedora/)

### 스프링 클라우드 슬루스로 분산 로그 추적

여러 마이크로 서비스에 걸쳐 있는 트랜잭션의 전 구간을 추적하려면 하나의 연관 ID가 있어야 한다.

* 트위터의 [집킨<sup>`Zipkin`</sup>](https://zipkin.io/)
* 클라우데라<sup>`Cloudera`</sup>의 [HTrace](http://htrace.org/)
* 구글의 [대퍼<sup>`Dapper`</sup>](https://ai.google/research/pubs/pub36356)
* 스프링 클라우드의 [슬루스<sup>`Spring Cloud Sleuth`</sup>](https://spring.io/projects/spring-cloud-sleuth)

분산형 추적은 구간<sup>`span`</sup>과 추적<sup>`trace`</sup> 개념으로 동작한다.

![구간과 추적](https://i.imgur.com/THmGMvV.png)

## 마이크로서비스 모니터링

파편화된 로깅 문제와 마찬가지로 마이크로서비스 모니터링의 핵심은 마이크로 서비스 생태계에 동적인 부분들이 많다는 점이다.

* 통계와 지표가 많은 서비스, 인스턴스, 장비에 걸쳐 파편화돼 있다.
* 마이크로서비스들이 서로 다른 기술들로 구현돼 있을 수 있다.
* 마이크로서비스 배포 토폴로지는 동적이다.

### 마이크로서비스 모니터링 <sup>`p411`</sup>

![마이크로서비스 모니터링](https://i.imgur.com/eiZb05c.png)

### 모니터링 도구

* [프로메테우스<sup>`Prometheus`</sup>](https://prometheus.io/)
* 센수<sup>`Sensu`</sup>
* [Kiali](https://www.kiali.io/)

### 스프링 클라우드 히스트릭스

장애를 견뎌낼 수 있고 응답 지연을 견딜 수 있는 마이크로서비스 구현을 위한 라이브러리인 스프링 클라우드 히스트릭스에 대해 살펴본다.

히스트릭스는 넷플릭스에서 철저하게 테스트된 라이브러리며, 서킷 브레이커<sup>`Circuit Breaker`</sup> 패턴을 바탕으로 한다.

### 터바인을 통한 히스트릭스 스트림 통합

히스트릭스 대시보드는 한 번에 하나의 마이크로서비스만 모니터링할 수 있다.

다수의 /hystrix.stream 인스턴스에서 가져온 데이터를 집계해 하나의 대시보드 화면으로 통합하는 메커니즘이 필요한데, 터바인<sup>`Turbine`</sup>이 바로 그런 일을 한다.

터바인은 내부적으로 모니터링하기 위해 설정된 서비스 ID를 확인하기 위해 유레카를 사용한다.

![Turbine](https://i.imgur.com/ELfW3V6.png)

## 데이터 호수를 사용한 데이터 분석

파편화된 데이터는 마이크로서비스 아키텍처에서 마주치게 되는 또 다른 난관이다.

이벤트 소싱<sup>`Event-sourcing`</sup> 아키텍처 패턴은 일반적으로 이벤트를 통해 상태와 상태 변경을 외부 데이터 저장소와 공유하는 데 사용된다.

![데이터 호수](https://i.imgur.com/htrCcic.png)

* [카프카<sup>`Apache Kafka`</sup>](https://kafka.apache.org/)
* [플룸<sup>`Apache Flume`</sup>](https://flume.apache.org/)
* [스파크 스트리밍<sup>`Apache Spark Streaming`</sup>](https://spark.apache.org/streaming/)
* [스프링 클라우드 스트림<sup>`Spring Cloud Stream`</sup>](https://spring.io/projects/spring-cloud-stream)
* [스프링 데이터 플로우<sup>`Spring Data Flow`</sup>](https://spring.io/projects/spring-cloud-dataflow)