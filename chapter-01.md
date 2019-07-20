# 1장. 쉽게 알아보는 마이크로 서비스

## 마이크로서비스 (Microservices)

> 마이크로서비스 아키텍처 스타일은 각자 별도의 프로세스에서 실행되며, HTTP 자원 API 같은 가벼운 메커니즘으로 통신하는 작은 서비스를 모아 하나의 애플리케이션을 만든다.
> 이런 작은 서비스들은 각자의 비지니스 기능을 담당하고 완전 자동화된 절차에 따라 독립적으로 배포 가능하다.
> 작은 서비스를 관리하는 데 중앙 집중형 관리 방식은 최소한으로 사용되며, 각 서비스는 서로 다른 프로그래밍 언어나 서로 다른 데이터 저장 기술을  사용할 수도 있다. - 마틴 파울러

> 마이크로서비스는 자율적이고 자기 완비적(self-contained)이고 느슨하게 결합된 비지니스 기능을 모아 IT 시스템을 만드는 가키텍처 스타일 또는 접근 방식이다.

![msa!](http://turnoff.us/image/en/microservices.png)

## 마이크로서비스의 장점

* 폴리글랏 아키텍처 지원
* 실험과 혁신 유도
* 탄력적이고 선택적인 확장
* 대체 가능성
* 유기적 시스템 구축 유도
* 기술 부채 경감
* 다양한 버전의 공존
* 자기 조직화 시스템 구축 지원
* 이벤트 주도 아키텍처 지원
* 데브옵스 지원

## 마이크로서비스의 단점

* 장애추적, 모니터링이 어렵다.
* 여러 서비스에 걸쳐져 있는 `Feature`의 경우, 트랜잭션을 다루기 어렵다.
* 여러 서비스에 걸쳐져 있는 `Feature`의 경우, 테스팅이 복잡하다.
* 서비스 간 `Dependency`가 있는 경우 릴리즈가 까다롭다.
* 서비스 개수가 많고 유동적이기 때문에 CI <sup>`Continuous Integration`</sup> / CD <sup>`Continuous Delivery`</sup> 및 서비스 관리 상의 문제가 발생할 수 있다.
* `Monolith`로 시작한 시스템을 `Microservices`로 전환할 때 큰 고통이 수반될 수 있다.

![mas?](http://turnoff.us/image/en/monolith-retirement.png)

## 기타

#### 일체형 아키텍처<sup>`monolithic architecture`</sup> `39p`

[![Imgur](https://i.imgur.com/mQuTfqc.jpg)](https://rancher.com/blog/2019/microservices-vs-monolithic-architectures/)

#### 서비스 지향 아키텍처<sup>`SOA`, `service oriented architecture`</sup> `40p`

> 서비스 지향 아키텍처란 대규모 컴퓨터 시스템을 구축할 때의 개념으로 업무상의 일 처리에 해당하는 소프트웨어 기능을 서비스로 판단하여 그 서비스를 네트워크상에 연동하여 시스템 전체를 구축해 나가는 방법론이다.

### MSA vs SOA

![Imgur](https://i.imgur.com/18DXwGI.jpg)

* [MSA Vs.SOA](http://blog.naver.com/PostView.nhn?blogId=stmshra&logNo=221446919085&parentCategoryNo=&categoryNo=73&viewDate=&isShowPopularPosts=true&from=search)
* [마이크로서비스와 SOA 비교](https://free-strings.blogspot.com/2016/01/soa.html)

#### 클라이언트 자바스크립트 프레임워크 `44p` - [Google Trends](https://trends.google.com/trends/explore?cat=5&date=2016-01-01%202019-07-13&geo=KR&q=%2Fm%2F0j45p7w,%2Fg%2F11c0vmgx5d,%2Fm%2F012l1vxv)

* [React](http://webframeworks.kr/getstarted/reactjs/)
* [Angular](https://angular.io/)
* [Vue.js](https://vuejs.org/)
* [Polymer](https://www.polymer-project.org/)
* [Backbone.js](https://backbonejs.org/)
* [Ember.js](https://emberjs.com/)
* [Aurelia](https://aurelia.io/)
* [Mithril](https://mithril.js.org/)

#### 반응형<sup>`responsive`</sup> 디자인 & 적응형<sup>`adaptive`</sup> 디자인 `44p`

> 반응형 웹 디자인이란 하나의 웹사이트에서 PC, 스마트폰, 태블릿 PC 등 접속하는 디스플레이의 종류에 따라 화면의 크기가 자동으로 변하도록 만든 웹페이지 접근 기법을 말한다

> 적응형 웹 디자인이란 서버나 클라이언트에서 웹에 접근한 디바이스를 체크하여 그 디바이스에 최적화된 미리 정해 놓은 각 디바이스의 디스플레이에 맞는 웹을 보여주는 디자인을 의미한다

![Imgur](https://i.imgur.com/XEu4rb0.png)

* [반응형 웹 디자인 기본 사항 - Google Developers](https://developers.google.com/web/fundamentals/design-and-ux/responsive/)
* [반응형 웹 디자인 패턴 - Google Developers](https://developers.google.com/web/fundamentals/design-and-ux/responsive/patterns?hl=ko)
* [AWD vs PWD](https://i.imgur.com/BB74fz4.jpg)

#### 클라우드 서비스 `44p` - [Google Trends](https://trends.google.com/trends/explore?date=today%205-y&q=%2Fm%2F05nrgx,%2Fm%2F04y7lrx,%2Fm%2F0j9pqc0,%2Fm%2F0glqhrc)

* [아마존 웹 서비스<sup>`AWS`, `Amazon Web Service`</sup>](https://aws.amazon.com/ko/)
* [마이크로소프트 애저<sup>`Microsoft Azure`</sup>](https://azure.microsoft.com/ko-kr/)
* [피보탈 클라우드 파운드리<sup>`Pivotal CloudFoundry`</sup>](https://pivotal.io/kr/platform)
* [세일즈포스<sup>`Salesforce`</sup>](https://www.salesforce.com/kr/)
* [IBM 블루믹스<sup>`Bluemix`</sup>](https://www.ibm.com/kr-ko/cloud)
* [오픈시프트<sup>`Red Hat OpenShift`</sup>](https://www.openshift.com)

#### 서비스로서의 플랫폼 <sup>`Pass`, `Platform as a Service`</sup> `44p`

> 서비스로서의 플랫폼은 클라우드 컴퓨팅 서비스 분류 중 하나다. 일반적으로 앱을 개발하거나 구현할 때, 관련 인프라를 만들고 유지보수하는 복잡함 없이 애플리케이션을 개발, 실행, 관리할 수 있게 하는 플랫폼을 제공한다.

#### 서비스로서의 통합 플랫폼 <sup>`iPaas`, `Integration Platform as a Service`</sup> `44p`

* [부미<sup>`Boomi`</sup>](https://boomi.com/)
* [인포매티카<sup>`Informatica`</sup>](https://www.informatica.com/kr/)
* [뮬소프트<sup>`MuleSoft`</sup>](https://www.mulesoft.com/)

#### 관계형 데이터베이스<sup>`RDBMS`</sup> `45p`

> 관계형 데이터베이스는 키와 값들의 간단한 관계를 테이블화 시킨 매우 간단한 원칙의 전산정보 데이터베이스이다. 1970년 에드거 F. 커드가 제안한 데이터 관계형 모델에 기초하는 디지털 데이터베이스이다.

#### NoSQL `45p` - [Google Trends](https://trends.google.com/trends/explore?date=today%205-y&q=%2Fm%2F0fdjtq,%2Fm%2F04f32m3,%2Fm%2F03c4_2p,%2Fm%2F0b76t3s,%2Fm%2F05z_r2n)

> NoSQL 데이터베이스는 전통적인 관계형 데이터베이스 보다 덜 제한적인 일관성 모델을 이용하는 데이터의 저장 및 검색을 위한 매커니즘을 제공한다. 이러한 접근에 대한 동기에는 디자인의 단순화, 수평적 확장성, 세세한 통제를 포함한다.

* 하둡 <sup>`Hadoop`</sup>
* 카산드라 <sup>`Cassandra`</sup>
* 카우치디비 <sup>`CouchDB`</sup>
* 네오포제이 <sup>`Neo4J`</sup>
* 뉴오디비 <sup>`NuoDB`</sup>
* 몽고디비 <sup>`MongoDB`</sup>

#### NewSQL `45p`

> RDBMS 과점에서 SQL 지원, ACID 준수, 성능 개선을 가지고 NoSQL의 특징인 확장성과 가용성을 갖춘 DBMS

* [Spanner](https://cloud.google.com/spanner/) ([글로벌 분산 데이터베이스 Spanner - Naver D2](https://d2.naver.com/helloworld/216593))
* [VoltDB](https://www.voltdb.com/)
* [MySQL Cluster](https://www.mysql.com/products/cluster/)
* [ScaleDB](http://www.scaledb.com/)
* [Drizzle](http://www.drizzle.org/)
* [ClustrixDB](https://mariadb.com/products/clustrixdb/)
* [MemSQL](https://www.memsql.com/)
* [NuoDB](https://www.nuodb.com/)

### 일체형 아키텍처 `45p`
* 메인프레임
    > 메인프레임 또는 대형 컴퓨터는 통계 데이터나 금융 관련 전산업무, 전사적 자원 관리와 같이 복잡한 작업을 처리하는 컴퓨터이다. 이 용어는 원래 초기 컴퓨터들의 중앙 처리 장치와 메인 메모리를 갖춘 "메인 프레임"이라 불리는 대형 캐비넷들을 지칭하였다
* 클라이언트-서버
    > 클라이언트 서버 모델은 서비스 요청자인 클라이언트와 서비스 자원의 제공자인 서버 간에 작업을 분리해주는 분산 애플리케이션 구조이자 네트워크 아키텍처를 나타낸다.
    
    ![Imgur](https://i.imgur.com/aX6mWxZ.png)
* 다층 구조 <sup>`N-Tier`</sup>
    > 다층 구조는 비즈니스 로직을 완전히 분리하여 데이터베이스 시스템과 클라이언트의 사이에 배치한 클라이언트 서버 시스템의 일종이다. 예를 들어 사용자와 데이터베이스간의 데이터 요구 서비스에 미들웨어를 이용하는 것을 들 수 있다. 일반적으로는 3층 구조가 널리 쓰인다.
    
    ![Imgur](https://i.imgur.com/CfMqYml.png)
 
#### 분할 정복 기법 `46p`

> 분할 정복 알고리즘은 그대로 해결할 수 없는 문제를 작은 문제로 분할하여 문제를 해결하는 방법이나 알고리즘이다. 빠른 정렬이나 합병 정렬로 대표되는 정렬 알고리즘 문제와 고속 푸리에 변환 문제가 대표적이다.

#### 포트와 어댑터 패턴<sup>`Ports and Adapter pattern`</sup> (육각형 아키텍처<sup>`hexagonal architecture`</sup>) `46p`

![Imgur](https://i.imgur.com/hCdVoCK.png)

* https://softwarecampament.wordpress.com/portsadapters/

#### 마아크로서비스 사이의 통신이나 전송 방식 `50p`
* HTTP / REST
* JMS
* AMQP
* 스리프트 <sup>`Thrift`</sup>
* 제로엡큐 <sup>`ZeroMQ`</sup>

#### 애플리케이션 컨테이너 `53p`
* 제티 <sup>`Jetty`</sup>
* 톰캣 <sup>`Tomcat`</sup>
* 웹로직 <sup>`WebLogic`</sup>
* 웹스피어 <sup>`WebSphere`</sup>
* Red Hat JBoss EAP <sup>`Enterprise Application Platform`</sup>

#### ACID한 트랜잭션

> ACID는 데이터베이스 트랜잭션이 안전하게 수행된다는 것을 보장하기 위한 성질을 가리키는 약어이다. 짐 그레이는 1970년대 말에 신뢰할 수 있는 트랜잭션 시스템의 이러한 특성들을 정의하였으며 자동으로 이들을 수행하는 기술을 개발해 냈다.

* A <sup>`Atomicity`</sup>: 데이터베이스는 전체 또는 일부만 실행하는 것이 아니라 전체 또는 일부 트랜잭션을 실행해야합니다.
* C <sup>`Consistency`</sup>: 데이터베이스는 시스템 규칙을 적용해야합니다. 예를 들어 값이 허용되지 않으면 필드에 값을 입력 할 수 없습니다.
* I <sup>`Isolation`</sup>: 여러 트랜잭션이 동시에 실행되는 경우 트랜잭션이 독립적으로 실행되며 최종 결과는 마치 하나씩 실행되는 것처럼 보입니다.
* D <sup>`Durability`</sup>: 트랜잭션이 완료되면 완료된 상태로 유지됩니다 (나중에 시스템이 중단 되더라도).

#### 서비스 오케스트레이션 <sup>`service orchestration`</sup> `57p`

> 다른 악기를 사용하는 음악가들이 지휘자를 통해 코디네이션 되는 거로 예시될 수 있다. "중재 컴포넌트"(mediator component)는 지휘자에 해당하는데 모든 서비스 호출이 "중재 컴포넌트"를 통해 이루어진다.

#### 서비스 연출 <sup>`service choreography`</sup> `57p`

> 발레 댄서들이 서로 상호 작용하며 움직이지만 지휘자가 없는 것으로 예시될 수 있다. 중앙중재가 없이 여러 서비스가 코디네이션 되며, "서비스 chaining"과 관련 있다.

#### 하이퍼바이저 <sup>`hypervisor`</sup> `58p`

> 하이퍼바이저는 호스트 컴퓨터에서 다수의 운영 체제를 동시에 실행하기 위한 논리적 플랫폼을 말한다. 가상화 머신 모니터 또는 가상화 머신 매니저라고도 부른다.

#### [얼랭](https://www.erlang.org/) <sup>`Erlang`</sup> `60p`

> 얼랭은 범용 병렬 프로그래밍 언어이다. 함수형 언어가 효율적으로 산업 현장등에서 사용되는 유명한 사례이다. 원래는 에릭슨사에서 스위칭 소프트웨어에서 사용하기 위해 개발했지만, 1998년에 오픈 소스로 공개되었다.

#### [일래스틱서치](https://www.elastic.co/kr/products/elasticsearch) <sup>`ElasticSearch`</sup> `60p`

> 일래스틱서치는 루씬 기반의 검색 엔진이다. HTTP 웹 인터페이스와 스키마에서 자유로운 JSON 문서와 함께 분산 멀티테넌트 지원 전문 검색 엔진을 제공한다. 일래스틱서치는 자바로 개발되어 있으며 아파치 라이선스 조항에 의거하여 오픈 소스로 출시되어 있다.

#### 테스트 `61p`

* [셀레늄 <sup>`Selenium`</sup>](https://www.seleniumhq.org/)
    > 여러 언어에서 웹드라이버를 통해 웹 자동화 테스트 혹은 웹 자동화를 도와주는 라이브러리
* [큐컴버 <sup>`Cucumber`</sup>](https://cucumber.io/)
    > BDD 기반의 개발방법론에 입각하여 비지니스 레벨의 테스트 케이스를 생성
* [A/B 테스트 전략](https://ko.wikipedia.org/wiki/A/B_%ED%85%8C%EC%8A%A4%ED%8A%B8)
    > 마케팅과 웹 분석에서, A/B 테스트(버킷 테스트 또는 분할-실행 테스트)는 두 개의 변형 A와 B를 사용하는 종합 대조 실험(controlled experiment)이다.

#### 인프라스트럭처 프로비저닝 <sup>`provisioning`</sup> `61p`

* [도커 <sup>`Docker`</sup>](https://www.docker.com/)
    > 도커는 리눅스의 응용 프로그램들을 소프트웨어 컨테이너 안에 배치시키는 일을 자동화하는 오픈 소스 프로젝트이다. 도커 웹 페이지의 기능을 인용하면 다음과 같다: 도커 컨테이너는 일종의 소프트웨어를 소프트웨어의 실행에 필요한 모든 것을 포함하는 완전한 파일 시스템 안에 감싼다.
* [세프 <sup>`Chef`</sup>](https://www.chef.io/)
    > 서버나 애플리케이션을 쉽게 배포하기 위한 시스템이나 클라우드 인프라 자동화 프레임워크
* [퍼펫 <sup>`Puppet`</sup>](https://puppet.com/)
    > 컴퓨팅에서 퍼핏은 오픈 소스 소프트웨어 형상 관리 도구의 하나이다. 수많은 유닉스 계열 시스템들뿐 아니라 마이크로소프트 윈도우에서도 실행되며 시스템 구성을 기술하기 위한 자체 선언 언어를 포함하고 있다.
* [앤시블 <sup>`Ansible`</sup>](https://www.ansible.com/)
    > Ansible은 오픈 소스 소프트웨어 프로비저닝, 구성 관리, 애플리케이션 전개 도구이다. 수많은 유닉스 계열 시스템에서 실행되며 유닉스 계열 운영 체제 및 마이크로소프트 윈도우의 구성이 가능하다. 시스템 구성을 기술하기 위해 자체 선언형 언어를 포함하고 있다.
* [스프링 클라우드](https://spring.io/projects/spring-cloud) <sup>`Spring Cloud`</sup>
* [쿠버네티스 <sup>`Kubernetes`</sup>](https://kubernetes.io/ko/)
    > 쿠버네티스는 디플로이 자동화, 스케일링, 컨테이너화된 애플리케이션의 관리를 위한 오픈 소스 시스템으로서 원래 구글에 의해 설계되었고 현재 리눅스 재단에 의해 관리되고 있다.
* [메소스 <sup>`Mesos`</sup>](http://mesos.apache.org/)
    > 아파치 메소스는 컴퓨터 클러스터를 관리하기 위한 오픈 소스 프로젝트이다. 캘리포니아 대학교 버클리에서 개발되었다.
* [마라톤 <sup>`Marathon`</sup>](http://mesosphere.github.io/marathon/)
    > A container orchestration platform for Mesos and DC/OS <sup>`Distributed Cloud Operating System`</sup>

#### 엔터프라이즈 서비스 버스 <sup>`ESB` `Enterprise Service Bus`</sup> `63p`

> 엔터프라이즈 서비스 버스 는 서비스들을 컴포넌트화된 논리적 집합으로 묶는 핵심 미들웨어이며, 비즈니스 프로세스 환경에 맞게 설계 및 전개할 수 있는 아키텍처 패턴이다.

#### 붕괴 저항성 <sup>`antifragility`</sup> `64p`
  
#### 평균 무고장 시간 <sup>`MTBF` `Mean Time Between Failure`</sup> `65p`

> 평균 무고장 시간는 시스템의 고장 발생 평균 시간을 나타내는 것이다. 평균무고장시간은 밀도 함수 ƒ의 기댓값을 사용하여 정의될 수 있다.

#### [평균 복구 시간](https://ko.wikipedia.org/wiki/%ED%8F%89%EA%B7%A0_%EB%AC%B4%EA%B3%A0%EC%9E%A5_%EC%8B%9C%EA%B0%84) <sup>`Mean Time To Recovery`</sup> `65p`

> 장치가 장애로부터 복구하는 데 걸리는 평균 시간입니다. 이러한 장치의 예로는 자체 리셋 퓨즈 (MTTR이 매우 짧고, 초 단위 일 수 있음)부터 수리 또는 교체해야하는 전체 시스템까지 있습니다.

#### 자체 치유 <sup>`self-healing`</sup> `65p`

#### 스케일 큐브 <sup>`Scale Cube`</sup> `75p`

* [[번역] 스케일 큐브](http://chanwookpark.github.io/microservice/scalecube/%EB%B2%88%EC%97%AD/chris/2014/04/12/scale-cube/)

#### 카나리아 출시 <sup>`canary release`</sup> `82p`

> 새로운 버전의 소프트웨어를 운영 환경에 배포할 때, 전체 사용자들이 사용하도록 모든 인프라에 배포하기 전에 소규모의 사용자들에게만 먼저 배포함으로써 리스크를 줄이는 기법이다.

* [canary release](https://blog.naver.com/muchine98/220262491992)
* [A Comprehensive Guide to Canary Releases](https://blog.getambassador.io/cloud-native-patterns-canary-release-1cb8f82d371a)

### Reference

* [DevOps : Blue-Green 배포, A/B Testing 그리고 Canary Releases](https://jason-lim.tistory.com/3)
