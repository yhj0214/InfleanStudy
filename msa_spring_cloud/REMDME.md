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


### Section2. API Gateway Service ==============================
* 효과
  + 인증 및 권한 부여
  + 서비스 검색 통합
  + 응답 캐싱
  + 정책, 회로차단기 및 QoS 다시 시도
  + 속도제한, 부하분산
  + 로깅, 추적, 상관관계
  + 헤더, 쿼리 문자열 및 청구 변환
  + IP 허용 목록에 추가

#### Netflix Ribbon
* Spring Cloud에서의 MSA간 통신
  + RestTemplate
  + Feign Client
 
* Ribbon: Client side Load Balancer
  + 서비스 이름으로 호출
  + Health Check
    - 클라이언트 내부에 구축하여 사용(api gateway를 client내부에 구현)

* Netflix Zuul
  + spring boot 2.4이상부터 지원하지 않음
  + spring cloud gateway를 사용할 것

#### Spring Cloud Gateway - 기본
* 테스트 프로젝트 생성
  + 프로젝트명 : first-service, second-service
  + dependency : lombok, spring web, eureka discovery client 종속성 추가
  + controller 생성 : FistService Contoller
    - @RestContoller, @RequestMapping("/")
    - @GetMapping("/welcome") public String welcome(){return ""}
  + yml 파일 생성
    ``` application.yml
    server:
      port: 8081
    spring:
      application:
        name: my-first-service
    eureka:
      client:
        fetch-registry: false
        register-with-eureka: false

* gateway 프로젝트 생성
  + dependencies : devTools, Eureka Discovery Client, Gateway
  + application.yml 생성
  ``` application.yml
   server:
     port: 8000
   eureka:
     client:
       register-with-eureka: false
       fetch-registry: false
       service-url:
         defaultZone: http://localhost: 8761/eureka
   spring:
     application:
       name: gateway-service
     cloud:
       gateway:
         routes:
           - id: first-service
             uri: http://localhost:8081/
             predicates:
               - Path=/first-service/**
           - id: second-service
             uri: http://localhost:8082/
             predicates:
               - Path=/second-service/**
   ```
   
* spring cloud gateway - filter 적용
  + ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/d704413d-8882-4ca4-9ae0-f703206f9661)
  + FilterConfig.java클래스 생성(Application과같은 선상에 config파일 생성, 그 안에 생성)
    - @Configuration 어노테이션 추가 -> 스프링부트 실행 시 해당 어노테이션이 있는 것들을 모아 메모리에 등록
    - 해당 클래스에 RouteLocator 빈을 등록할 것이고, yml에서 지정해준 경로를 해당 파일에서 지정해줌
    - 빈 등록 메서드 등 내용 구현
      ``` java
      @Configuration
      public class FilterConfig{
        @Bean
        public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
          return builder.routes()
                  .route(r -> r.path("/first-service/**")
                              .filter(f -> f.addRequestHeader("first-request", "first-request-header")
                                            .addResponseHeader("first-response", "first-response-header"))
                              .uri("http://localhost:8081"))
                  .route(r -> r.path("/second-service/**")
                              .filter(f -> f.addRequestHeader("second-request", "second-request-header")
                                            .addResponseHeader("second-response", "second-response-header"))
                              .uri("http://localhost:8082"))
                  .build();
        }
      }
      // 해당 코드에서 작성한 request는 spring의 로그를 찍어 확인할 수 있고
      // response는 개발자도구 network의 header를 통해 확인할 수 있음
      ```
      
    - yml의 필터부분 주석처리
  + First-service, Second-service api추가,
    ``` java
    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header) {
      log.info(header);
      return "Hello World in First Service.";
    }
    ```
  + 필터 추가하기(apigatway프로젝트의 applicatiom.yml, 해당 코드 추가)
    - apigateway의 filterConfig의 @Configuation, @Bean 주석처리후 진행
    ``` yml
    cloud.gateway.routes.(id).filters:
      - AddRequestHeader=first-request, first-request-header2
      - AddResponseHeader=first-response, first-response-header2
    ```

* Spring Cloud Gateway - Custom Filter적용하기
  + apigateway-service프로젝트에 (application과 동인선상에)filter폴더 추가, filter폴더에 CustomFilter.java 생성, 내부에 public static class Config 선언
    > ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/556677ab-c74c-4978-88f0-80e58bfb97d3)
  + application.yml 파일 수정
    > ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/e541aab8-be69-4b7b-b7e2-5f4ab4ee8f23)
  + 메서드 추가
    > ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/a45da902-5029-41e3-8236-b4aedcb50a73)

* Spring Cloud Gateway - Global Filter 적용
  + 라우팅 정보가 있을때 필요한 필터가 있을때마다 각기 적용해야함 -> 한번에 모든 곳에 글로벌 필터 적용 가능
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/80144b24-04b9-4bdf-a8f8-0db178aa0e15)
  + global-filter 적용방법(yml)
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/d4ff1490-4c6e-46c2-9891-7992b7b84bb2)
  + CustomFilter와 같은 폴더 내에 GlobalFilter.java 생성 및 내용 구현(Config Class 내용을 구현한 후 @Data어노테이션 붙이기)

* Spring Cloud Gateway - Custom Filter(Logging)
  + 글로벌 필터를 응용하여 로그인 출력하기,
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/e53cd8fb-0ca0-46ec-bf0e-2f9d1ef14861)
  + yml 파일 수정하기
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/3a6e347a-ff70-4888-8e23-8dd88cb76584)
  + 필터 적용 순서 글로벌 -> 커스텀 -> 로깅 -> 커스텀 -> 글로벌
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/45cae1a4-7778-4052-b77f-f5336622571b)
  + filter폴더에 LoggingFilter.java생성(Global Filter복사하여 사용)
  + apply메서드만 위와같이 변경
  + yml파일에 name속성을 이용하여 추가할 속성을 지정하여 추가(파라미터도 같이 전달)
  + Ordered.HIGHST_PRECEDENCE, LOWEST_PRECEDENCE로 우선순위 지정 가능 HIGH일경우 가장 먼저 들어가고 가장 나중에 종료


* Spring Cloud Gateway-Eureka 연동
  + netflix-eureka-client dependency추가(cloudgateway, first, secondservice), yml파일 eureka 추가
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/61050828-bd49-4ebb-850a-2bc361dee213)
  + 등록된 서비스에 맞추어 uri설정해주기(yml파일)
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/650382c5-d453-4adb-9675-5fe9a0ec0aa3)
  + eureka서버 실행 후 올라간 프로젝트 확인하기
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/a78ce76e-b3ce-441c-86c5-cacf74a0a1a7)
  + apigateway, firstservice, secondservice
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/dfdd198e-070b-4a33-9f2b-1a055826f790)
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/6816dcf5-d487-4f58-98b0-0ba3c694aaeb)
  + discoveryservice실행 이후 apigateway, 각 service프로젝트 실행
    
* Spring Cloud Gateway- Load Balancer
  + fisrt, second service 각각 2개씩 기동, 포트번호를 변경하여 기동(8081, 8082, 9091, 9092 로 실행)
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/503e64ee-1c51-48e1-bd0a-27b4c8f8fa39)
  + 같은 애플리케이션 실행 시 gateway에서는 포트번호만 다른 같은 이름의 프로젝트중 가야할 곳을 설정해주어야 함 -> 랜덤포트 사용, port : 0
  + 각 서비스의 yml을 아래와 같이 설정해주어야 함.
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/9d4d13f8-7509-4d14-9c3b-2f2ff4c8e997)
  + 랜덤포트 설정 후 같은 서비스를 다시 두개 실행, 포트번호 확인을 위해 아래와 같이 코드 수정
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/65340b72-2b59-40af-ae18-1088597fb333)
  + 랜덤포트를 설정하여 같은 애플리케이션 여러개 설정시 라운드 로빈 방식으로 번갈아가면서 호출하게 된다.
  + 랜덤포트를 사용하지 않더라도 같은 이름의 애플리케이션 여러개 실행 시 rr방식으로 번갈아가면서 호출함


### Section3. E-commerce 애플리케이션
* 개요
  ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/fe5f3efc-d39b-4296-b5ca-87d4493d7e08)
* 구성
  ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/0e0d6455-0c66-453f-9e85-1fd464baddd7)
  ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/9f9531b8-5606-48dd-9d52-a0e80bf8e6c5)
* 사용 기술
  ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/8aa521d9-a970-4f74-8f3b-570be660c555)
* api 명세
  ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/82b69903-dca4-49ab-bd30-fecc5b075e46)



### Section4. User-microService(가입, 유저조회 부분)
- 개요
- 프로젝트 생성
- DB
- 회원가입
- Security

* 시스템 구조
  ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/67bd5569-9f86-4ee1-abe2-1ab079da28e9)
* 기능
  ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/60f24cc5-f88e-4bbe-8f5e-0eb8dae4b3a2)

* 프로젝트 생성 목차(UserMicroService)
  + dependency
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/c6c830d9-559e-47f6-a83d-6edf83b53d8e)
  + application에 @EnableDiscoveryClient 어노테이션 추가
  + yml파일 수정
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/9ccaaeb8-7760-4c98-ac08-c97fc37b6b2b)
  + 컨트롤러 생성
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/5be585cc-fcd8-4702-a32c-87c590e27c5c)
  + yml 파일 추가 및 연결여부 확인
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/8bfe0b67-8bd0-437d-9d90-08b733393211)
  + (연결 여부 확인하는 방법2- 컴포넌트 등록하기)
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/320283b7-121d-4a72-8eb2-6e21e47d86a1)
  + H2DB사용을 위한 dependency 추가, yml파일에 h2 DB와 맞는 환경 추가
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/5f943773-3add-435e-b9d7-92bf910b5cb5)
  + h2 1.4.198은 보안 문제로 자동으로 db를 설정해주지 않음, 편의성을 위하여 버전을 낮추어서 진행

* 프로젝트 생성(UserMicroService)
  + artifact: user-service, gradle
  + dependency: spring boot devtools, lombok, spring web, eureka discovery client
  + @EnableDiscoveryClient(application클래스에 어노테이션 붙이기)
  + yml파일 수정
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/b47e8c17-ca64-4d44-a462-9dfde205b5fd)
  + controller폴더 생성 후 UserController.java클래스 생성(@RestContoller, RequestMapping("/"))
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/b024adc5-e06e-4205-bbba-3a8f6d5fbfb1)
  + eureka서비스 실행, userservice 실행(구현한 메서드를 기반으로 구동 상황 체크)
  + yml 파일에 해당 코드 추가
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/dfc26141-f850-4000-852c-1fded94ba716)
  + Environment를 이용한 값 불러오기
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/222d5eb7-1271-4699-88ba-4ecf72472d99)
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/8dfc7a2f-91ae-4146-bb41-87badc2eba8d)
  + value annotation을 이용한 값 불러오기
    - vo 폴더 생성
    - Greeting.java 클래스 생성annotation(Component, Data), yml의 사용할 변수 선언
      ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/4f73d63e-19f2-4c78-8e94-e399fcb49338)
    - Controller에서 사용
      ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/70056b45-46c9-4d33-be51-c77d18cd0d33)
      ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/036802eb-e93e-47b9-8784-674c962bfd66)

* H2 DB 연동
  + h2 dependency 추가, yml파일에 h2 DB를 사용하기 위한 설정 코드 구현(1.3.176버전 사용 시 자동생성)
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/770d10ab-2966-461d-ac3b-1cd97c469b3b)

* User-MicroService 회원가입
  + Contoller, User클래스 구현
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/8ae01564-3f29-4e8b-a86f-9b9816edb623)
  + Validation check
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/f8319ea8-7dba-40c5-855a-f349fd09fa4f)
  + 아래와 같은 구조로 데이터가 이동할 예정
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/d33f0ba4-7bd1-4e75-a21b-3d73e6475054)
  + 각 클래스파일 내용
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/f21db200-5968-430e-ad8f-62ea309b5aed)
  + jpa dependency추가
  + UserEntity클래스 생성(데이터베이스와 연동되는 객체)
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/d10ebaab-898e-4d9f-af36-5cfe808a06d7)
  + service 회원가입 메서드 구현
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/54494279-9aa2-4813-acbf-c509a4b83883)
  + controller 회원가입 메서드 구현
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/9ebaf7f9-ea20-41b4-bbf4-c83a58f925f1)
  + 회원가입 요청
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/918f06ff-8d39-40a9-9ab4-c876948e8240)

* User-Microservice-JPA
  + 회원가입 테스트 진행
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/563b7498-a748-40ae-a6df-41b557d767d6)
  + 데이터베이스 연결 항목 추가, 이후 테스트 진행
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/17aa6b7d-53e5-4fd6-b86b-7765b1f8db2c)
  + 반환타입은 responseEntity로 사용
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/c0ea82e4-8324-48fd-804c-f722659c7a45)
  + vo패키지에 ResponseUser.java클래스 생성
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/102a1e63-a6d5-413e-af4a-26264501bbad)
  + 반환값도 지정하기
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/418203c9-6aeb-4674-afc0-0bffb5ec92c2)
  + userServiceImpl의 createUser에서 반환할 객체 다시 지정하주기
      
* User Microservice -Security
  ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/308c9350-51a9-427b-a677-9938d3be0a12)
  + spring-boot-starter-security dependency추가
  + security패키지 생성 후 WebSecurity.java클래스 생성, extends WebSecurityConfigurerAdapter 확장,
  + 어노테이션(@Configuration, @EnableWebSecurity사용
  + override메서드 정의(configure메서드 사용)
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/a7ad313a-5c43-44d3-ab18-ed53af3e3f4a)
  + UserService의 createUser에 암오화 코드 추가
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/3eb2550e-b170-451c-a33a-d5665a889349)
  + userRepository생성이 UserService생성자와 연결
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/ee19b91d-42b7-4505-9f6e-2da98775a146)
  + passwordEncoder Bean등록해두기
    ![image](https://github.com/yhj0214/InfleanStudy/assets/87259492/b78d97b9-a8a5-400d-882b-3f7fce14fed6)
  + 
