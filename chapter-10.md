# 10장. 메소스와 마라톤을 이용한 도커화된 마이크로서비스 확장

클라우드 환경<sup>`cloud-like evironment`</sup>이 가진 장점을 최대한 활용하려면 도커화된 마이크로서비스 인스턴스가 **트래픽 패턴에 따라 자동으로 늘어날 수 있어야 한다**.

메소스<sup>`Mesos`</sup>를 인프라스트럭처 추상화 계층으로, 마라톤을<sup>`Marathon`</sup>을 클러스터 제어 시스템으로 사용하는 방법을 알아본다.

* 컨테이너화된 스프링 부트 마이크로서비스의 확장 방법
* 추상화 계층과 클러스터 컨테이너 오케스트레이션<sup>`orchestration`</sup> 소프트웨어의 필요성
* 마이크로서비스 관정에서의 메소스와 마라톤 이해
* 도커화된 프라운필드 항공사 PSS 마이크로서비스를 메소스와 마라톤으로 관리


## DC/OS 설치

[DC/OS<sup>`Data Center Operating System`</sup>](https://dcos.io/) 환경을 구성한다.

* [DC/OS 1.2 On-Premise Installation](https://docs.d2iq.com/mesosphere/dcos/1.12/installing/production/)

### 서버 구성

| 호스트 | IP | OS | 구분 |
|---|---|---|---|
| mesos-0.antop.org | 192.168.20.80 | CentOS 7 | Bootstrap Node | 
| mesos-1.antop.org | 192.168.20.81 | CentOS 7 | Master Node |
| mesos-2.antop.org | 192.168.20.82 | CentOS 7 | Slave Node |
| mesos-3.antop.org | 192.168.20.83 | CentOS 7 | Slave Node |

### 호스트 등록

모든 서버의 `/etc/hosts` 파일을 수정하여 호스트를 맞춘다.

```
192.168.20.81   mesos-0 mesos-0.antop.org
192.168.20.81   mesos-1 mesos-1.antop.org
192.168.20.82   mesos-2 mesos-2.antop.org
192.168.20.83   mesos-3 mesos-3.antop.org
```

호스트네임<sup>`hostname`</sup>도 FQDN<sup>`Full Qualified Domain Name`</sup>으로 맞춘다.

```bash
# vi /etc/hosts
[root@mesos-1 ~]#
[root@mesos-1 ~]# hostname mesos-2.antop.org
[root@mesos-1 ~]# hostname -f
[root@mesos-1 ~]# hostname
mesos-1.antop.org
```

### CentOS 7 기본 설정

모든 노드에 방화벽 해제, 기타 프로그램 설치

```bash
systemctl stop firewalld
systemctl disable firewalld

yum update -y
yum install -y net-tools zip unzip
```

### Install Docker

모든 노드에 도커<sup>`Docker`</sup>를 설치한다.

```bash
yum install -y yum-utils device-mapper-persistent-data lvm2
yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
yum install -y docker-ce docker-ce-cli containerd.io
systemctl enable --now docker
systemctl status docker
```

```bash
echo "net.bridge.bridge-nf-call-ip6tables = 1" >> /etc/sysctl.conf
echo "net.bridge.bridge-nf-call-iptables = 1" >> /etc/sysctl.conf
sysctl -p
```

### Configure bootstrap node

※ 시작 경로를 `/root`, 계정은 `root`로 하였다.

1. genconf 디렉토리 생성

    ```bash
    mkdir genconf
    ```

2. genconf/config.yaml 파일 생성

    ```yaml
    bootstrap_url: 'http://mesos-0.antop.org:80'
    cluster_name: antop.org
    exhibitor_storage_backend: static
    master_discovery: static
    master_list:
    - 192.168.20.81
    resolvers:
    - 168.126.63.1
    - 168.126.63.2
    ```

3. genconf/ip-detect 파일 생성 (다른 환경에서는 다른 스크립트 사용해야 한다)

    아래 `eth0`를 자신의 네크워크 장치에 맞게 바꾸자.

    ```bash
    #!/usr/bin/env bash
    set -o nounset -o errexit
    export PATH=/usr/sbin:/usr/bin:$PATH
    echo $(ip addr show eth0 | grep -Eo '[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}' | head -1)
    ```

4. 실행
   
   ```bash
   curl -O https://downloads.dcos.io/dcos/stable/dcos_generate_config.sh
   bash dcos_generate_config.sh
   ```
   
5. nginx 실행: 다른 노드에서 생성된 설치파일을 받을 수 있게 웹서버를 하나 띄운다.

   ```bash
   # echo $PWD
   /root
   
   docker run -d -p 80:80 -v $PWD/genconf/serve:/usr/share/nginx/html:ro nginx 
   ```

### Install DC/OS on master node

```bash
mkdir /tmp/dcos && cd /tmp/dcos
curl -O http://mesos-0.antop.org:80/dcos_install.sh
bash dcos_install.sh master
```

### Install DC/OS on agent node

```bash
mkdir /tmp/dcos && cd /tmp/dcos
curl -O http://mesos-0.antop.org:80/dcos_install.sh
bash dcos_install.sh slave
```

### Uninstall

```bash
curl -O http://downloads.mesosphere.com/dcos-uninstall/uninstall.sh
chmod a+x uninstall.sh
./uninstall.sh
```

## 결론

※ 책에 나오는 맛보리고 서버 한대에 하는 상황과는 온도 차이가 심하다.

단순히 Mesos + Marathon 설정만으로 할 수 없고, 네트워크 설정이 같이 되어야 할 것 같다...

그래서.. 여기까지 한다 ㅠㅠ

### DC/OS

![DC/OS](https://i.imgur.com/oE2eiyG.png)

### Mesos

![Mesos](https://i.imgur.com/j1YfgAR.png)

### Marathon

![Marathon](https://i.imgur.com/oEWxDwA.png)
