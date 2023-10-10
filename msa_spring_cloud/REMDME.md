## MSA-Springcloud
</br>
</br>

### Section0. Microservice와 Spring Cloud의 소개

* Antifragile의 네가지 특징
  + Auto scaling : 확장성, 인스턴스 자동 증감이 가능
  + Microservices : 
  + Chaos enginnering : 변동, 불확실성, 카오스 불확실성
  + Continuous deployments : CI/CD(지속적인 통합, 배포), 빌드 배포의 자동화 시스템

* Cloud Native Architecture
  - 확장 가능한 아키텍처
    + 시스템의 유연한 수평확장(부하 분산, 가용성 보장)
    + 시스템 또는 service application 단위의 패키지(컨테이너 기반 패키지), 모니터링
  - 탄력적 아키텍처
    + 분할된 서비스구조
    + 서비스의 추가, 삭제를 자동으로 감지
  - 장애 격리(Fault isolation)
    + 특정 서비스에 오류가 발생해도 타 시스템에 영향을 주지 않음

* Cloud Native Application
  - CI/CD
    + 통합 서버, 소스 관리(SCM), 빌드 도구, 테스트 도구
    + Conrinuous Delivery, Deployment, plpe line
  - DevOps
    + 서비스를 작은 단위로 분할하여, 더 많은 테스트, 빌드, 통합
  - Container 가상화
    + 기존의 하드웨어 가상화 기술보다 더 적은 리소스를 요함

* Cloud Native Application을 구축하며 고려할 12가지 항목(12 Factors, 문제점과 개선점을 바탕으로 한 가이드라인)
  - Base Code : 형상 관리를 위하여 한 곳에서 코드를 관리하여야 한다.
  - Dependency isolation : 각 서비스의 변경 내용은 전체 시스템에 영향을 주지 않아야 한다.
  - configurations : 시스템 코드 외부에서 마이크로 서비스에 필요한 작업들을 제어
  - linkable backing services : dependency를 갖지 않은 상태에서 코드 작업
  - stages of creation : 빌드, 릴리즈, 실행은 완벽히 구분되어 있어야 한다.
  - stateless processes : 하나의 microservice는 독립적으로 운영될 수 있어야 한다.(동기화 필요)
  - port binding : 각각의 ms는 자체 포트에서 노출되는 기능과 함께, 자체에 포함된 기능이 있어야 함.(격리)
  - concurrency : 부하 분산, 서비스가 여러 인스턴스에 나누어서 제공
  - disposabliity : 서비스 삭제가 가능해야 하고, 확장이 가능해야 함
  - development & production parity : 개발, 프로덕션 단계 구분
  - logs : 구성된 로그 출력 로직은 어플리케이션 로직과 분리
  - admin processes for eventual processes : 운영되는 ms들을 파악하기 위한 적절한 관리 도구가 필요

  - api first :
  - telemetry :
  - authentication and authorization :
</br>

#### MSA
다른 프로그래밍 언어와 다른 데이터베이스를 사용하여 각 서비스 마다 최적의성능을 내도록 서비스를 구현
restful한 api로 서로의 서비스에 접근하여 이용

* SOA(Service Oriented Architecture) vs MSA
  + 서비스의 공유 지향점
    - SOA : 재사용을 통한 비용 절감(서비스 공유 최대화)
    - MSA : 서비스 간 결합도를 낮추어 변화에 능동적으로 대응(서비스 공유 최소화)
  + 기술 방식
    - SOA : 공통의 서비스를 ESB에 모아 사업 측면에서 공통 서비스 형식으로 서비스 제공
    - MSA : 각 독립된 서비스가 노출된 REST API사용
   
* Restful Web Service특징
  + level0
    - 웹 서비스 상태로 제공하기 위해 url 만 매핑해둔 상태
  + level1
    - 서비스 형태와 작업에 맞추어 적절한 http url로 처리, 메소드 분할은 아직 이루어지지 않음
  + level2
    - level1+http methods
    - 읽기 형태인 경우 단순히 get을 이용한 요청
    - 새로운 리소스를 추가하는 경우 post method
    - 기존 메소드의 상태변경을 위해선 put method
    - 리소스 삭제를 위해선 delete메소드
    - crud를 http method를 이용하여 작업
    - 비슷한 이름의 uri라도 http 메서드의 형태에 따라 다른 서비스를 제공할 수 있다는 장점
  + level3
    - level2 + HATEOAS
    - DATA + NEXT POSSIBLE ACTIONS

* Restful한 Web Service를 만들기 위한 고려사항
  + Consumer first : 해당 API를 사용하는 소비자 관점(중간 개발자가 될 수도 있음)에서 직관적인 표현
  + Make best use of HTTP : http의 장점을 최대한 살려서 개발할 것
  + request methods : 최소한 level2의 장점을 살려서 개발할 것
    - 기존 method의 uri표현값에 적절한 http method가 연동된 케이스
  + response status : 적절한 상태코드를 전달할 것
    - 200
    - 404
    - 400
    - 201
    - 401
  + No secure info in URI : uri에는 중요한 정보가 들어가선 안됨
  + Use plurals
    - 제공하려는 데이터에 대해 복수형태로 제공하는 것이 일반적
  + User nouns for resources : 명사형으로 제공할 것
  + For exceptions : 일관적인 endpoint를 사용할 것

* MSA 기반 기술
  ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/d88e3097-0b6e-4525-8bf6-dac56ff934b4)



### Section1. Service Discovery
- Spring Cloud Netflix Eureka
- Eureka Service Discovery - 프로젝트 생성
- User Service-프로젝트 생성
- User Service-등록
- User Service - Load Balancer

#### Eureka Server 프로젝트 생성
* 프로젝트 생성
  + Group : 폴더 구조(회사 도메인이 역순으로 생성하는 것이 일반적)
  + artifact : 프로젝트 이름
  + package : group, artifact가 조합되어 생성됨
  + dependency : EurekaServer 추가 SpringBootApplication에 @EnableEurekaServer 어노테이션 추가

* application.yml
  ```xml
  server:
    port: 8761 //Eureka서버가 사용할 포트번호

  spring:
    application:
      name: discoveryservice // Spring framework의 아이디

  eureka:
    client:  // 해당 설정을 하지 않으면 default가 true로 설정됨
      register-with-eureka: false // eureka서버는 직접적으로 등록하여 사용할 서비스가 없음
      fetch-registry: false       // 따라서 false로 지정하여 해당 서버는 등록하지 않음
                                  // eureka 서버는 서버로서 기동만 하고 있으면 되기 때문
  ```

* http://[사용자의 ip주소]:8761/로 접근 시 해당 화면 출력
  ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/51863de0-c59c-4ac1-bcd8-56e8272492c8)
  - uptime : 서버가 언제 가동되었는지
  - instances currently registered with Eureka : ms로 개발되어 등록된 application 인스턴스 목록

#### Eureka Service Discovery 프로젝트 생성
* 프로젝트 생성
  + group: com.example
  + artifact : discoveryservice
  + maven, java, jar, java11,
  + version, name, desctription, package는 바꾸지 않고 진행
  + dependency에 Eureka Server추가 후 생성 진행
  + application.properties를 yml파일로 변경
  ```yml
  server:
    port: 8761

  spring:
    application:
      name: discoveryservice

  eureka:
    client:
      register-with-eureka: false
      fetch-registry: false
  ```

#### User Service 프로젝트 생성
* 프로젝트 생성
  + artifact : user-service
  + type : maven, language : java, packaging : jar ,version: java 11, name : user-service
  + dependency
    - spring cloud discovery : eureka discovery client
    - spring boot devtools
    - lombok
    - spring web
  + 프로젝트 생성 후 MAINAPP에 @EnableDiscoveryClient추가
  + application.yml 생성
    ```yml
    server:
      port: 9001

    spring:
      application:
        name: user-service

    eureka:
      client:
        register-with-eureka: true
        fetch-registry: true
        service-url:
          defaultZone: http://127.0.0.1:8761/eureka
    ```
  + 이후 프로젝트 실행하여 테스트

* 같은 프로젝트 복사하여 두개 이상의 service 실행하기
  + 우측 상단의 클래스 실행 버튼 드롭다운 클릭(Edit Configurations 클릭)
  + 왼쪽 UserServiceAPplication을 선택한 후 위의 세번째 버튼(copy)클릭, -2을 붙여 편집
  + 기존 1번을 먼저 실행 (2번은 포트 중복으로 사용 불가)
  + Edit Configurations클릭 후 Environment 클릭(VM options: -Dserver.port=9002)
  + 이후 두 클래스파일 모두 실행

* user-service 프로젝트에서 터미널 열기
  + 프로젝트 src폴더 내부로 이동 user-service/src에서 작업(pom.xml 과 같은 선상)
  + mvn --version, java, javac 설치 확인
  + mvn spring-boot:run -Dspring-boot-run.jvmArguments='-Dserver.port=9003'
 
* random포트번호를 사용하여 instance생성하기
  + 기존에 사용하던 instance를 모두 삭제
  + yml의 포트번호를 0으로 등록 후 실행
  + 이후 추가하여 실행하여도 실행 가능, (eureka url로 접속하여 확인하면 설정한 포트번호 0번만 보여짐)
  + 여러개를 실행하여도 하나로만 보이기 때문에 id를 지정해 줄것
  + eureka.instance:
  +   instance-id: ${spring.cloud.client.hostname}:${spring.application.instance_id:${random.value}}


