# 9장. 도커 컨테이너와 마이크로서비스

마이크로서비스를 컨테이너화하면 하부의 인프라스트럭처를 포함하므로 마이크로서비스가 **자기 완비적**이고 더 자율적이게 되며, 그래서 마이크로서비삭 특정 클라우드 환경에 종속되지 않고 중립성을 유지할 수 있다.

* 컨테이너화의 개념과 마이크로서비스 관점에서 컨테이너화의 타당성
* 마이크로서비스를 도커 이미지 및 도커 컨터에니로 만들고 배포하는 방법
* AWS<sup>`Amazon Web Service`</sup>를 활용한 클라우드 기반의 도커 배포

## 마이크로서비스를 도커에 배포

### Install RabbitMQ Server on CentOS 7

* [Install Erlang and Elixir on CentOS 7 Minimal](http://www.jeramysingleton.com/install-erlang-and-elixir-on-centos-7-minimal/)
* [Installing on RPM-based Linux](https://www.rabbitmq.com/install-rpm.html)
    * [Management Plugin](https://www.rabbitmq.com/management.html)
    * [Authentication, Authorisation, Access Control](https://www.rabbitmq.com/access-control.html)
* [rabbitmq - Docker Hub](https://hub.docker.com/_/rabbitmq)

### Install Docker CE on CentOS 7

* [Get Docker Engine - Community for CentOS](https://docs.docker.com/install/linux/docker-ce/centos/)
    * [docker iptables 오류](https://blog.nuti.pe.kr/2016/12/25/dockeriptablestrouble/)

포트를 맞춘다.

| 서비스 | 포트 |
|---|---|
| search | 8090 |
| fares | 8080 |
| book | 8060 |
| checkin | 8070 |
| website | 8001 |

```bash
docker ps

CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                    NAMES
a86111b254cf        website:1.0         "java -jar /website.…"   5 minutes ago       Up 5 minutes        0.0.0.0:8001->8001/tcp   compassionate_bouman
0c776ba7edb3        checkin:1.0         "java -jar /checkin.…"   6 minutes ago       Up 6 minutes        0.0.0.0:8070->8070/tcp   upbeat_diffie
e231ae494db6        search:1.0          "java -jar /search.j…"   8 minutes ago       Up 8 minutes        0.0.0.0:8090->8090/tcp   priceless_knuth
2f9b3c117f3b        book:1.0            "java -jar /book.jar"    10 minutes ago      Up 10 minutes       0.0.0.0:8060->8060/tcp   vibrant_newton
2bc0dbe0dab9        fares:1.0           "java -jar /fares.jar"   11 minutes ago      Up 11 minutes       0.0.0.0:8080->8080/tcp   naughty_cartwright
```

## Intellij with Docker

원격으로 도커를 사용할 수 있도록 설정 변경.

* [How do I enable the remote API for dockerd](https://success.docker.com/article/how-do-i-enable-the-remote-api-for-dockerd)

`IntelliJ`에서 도커 등록.

![](https://i.imgur.com/Gmm5ar6.png)

아래와 같이` IntelliJ`에서 사용 가능하다.

![](https://i.imgur.com/gyLgZI6.png)


