     Eureka通过“伙伴”机制实现高可用。每一台Eureka都需要在配置中指定另一个Eureka的地址作为伙伴，
     Eureka启动时会向自己的伙伴节点获取当前已经存在的注册列表，
     这样在向Eureka集群中新加机器时就不需要担心注册列表不完整的问题。

     除此之外，Eureka还支持Region和Zone的概念。其中一个Region可以包含多个Zone。Eureka在启动时需要指定一个Zone名，
     即当前Eureka属于哪个zone, 如果不指定则属于defaultZone。Eureka Client也需要指定Zone,
     Client(当与Ribbon配置使用时)在向Server获取注册列表时会优先向自己Zone的Eureka发请求，
     如果自己Zone中的Eureka全挂了才会尝试向其它Zone。
     Region和Zone可以对应于现实中的大区和机房， 如在华北地区有10个机房，在华南地区有20个机房，
     那么分别为Eureka指定合理的Region和Zone能有效避免跨机房调用，
     同时一个地区的Eureka坏掉不会导致整个该地区的服务都不可用。
     Spring Cloud Netflix对微服务的支持还有：

      -  Hystrix: 断路器和资源隔离
      -  Feign: 声明式HTTP REST请求客户端
      -  Ribbon: 与Eureka结合实现软负载均衡
      -  Zuul: API请求路由，即Api Gateway
      -  Bus: 各个微服务节点之间的消息通讯
      -  Config: 配置的中央化存储
     剔除过期等不健康实例（生产环境不建议使用）
     server端：

     1.关闭注册中心自我保护机制
         eureka.server.enable-self-preservation：false
     2.注册中心清理间隔（单位毫秒，默认60*1000）
         eureka.server.eviction-interval-timer-in-ms：10000
     client端：

     1.开启健康检查（需要spring-boot-starter-actuator依赖）
         eureka.client.healthcheck.enabled:true
     2.租期更新时间间隔（默认30秒）
         eureka.instance.lease-renewal-interval-in-seconds=10
     3.租期到期时间（默认90秒）
         eureka.instance.lease-expiration-duration-in-seconds=15

     以上参数配置下来，从服务停止，到注册中心清除不健康实例，时间大约在30秒左右。
     租期到期时间为30秒时，清除时间大约在59秒，若采用默认的30-60配置，清除时间大约在2分半（以上均在关闭保护机制情况下），
     生产环境建议采用默认配置，服务停止到注册中心清除实例之间有一些计算什么的。




     如何处理服务挂掉后或者手动关闭服务后，Ribbon负载均衡还是一直调用这个服务：

     1.Hystrix,在fallback方法中shutdown指定的服务
     2.让zuul只路由到活着的那个服务：
     添加依赖：
       <dependency>
           <groupId>com.squareup.okhttp3</groupId>
           <artifactId>okhttp</artifactId>
           <version>3.6.0</version>
       </dependency>
     配置文件：
     ribbon.connectTimeout: 2000
     ribbon.readTimeout: 10000
     ribbon.maxAutoRetries: 1
     ribbon.maxAutoRetriesNextServer: 2
     ribbon.okToRetryOnAllOperations: true
     ribbon.okhttp.enabled: true
     3.重试机制（和上述一个道理）
     spring.cloud.loadbalancer.retry.enabled=true
     hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds=10000
     hello-service.ribbon.ConnectTimeout=250
     hello-service.ribbon.ReadTimeout=1000
     hello-service.ribbon.OkToRetryOnAllOperations=true
     hello-service.ribbon.MaxAutoRetriesNextServer=2
     hello-service.ribbon.MaxAutoRetries=1


     spring.cloud.loadbalancer.retry.enabled：该参数用来开启重试机制，它默认是关闭的。这里需要注意，官方文档中的配置参数少了enabled
     hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds：断路器的超时时间需要大于ribbon的超时时间，不然不会触发重试。

     hello-service.ribbon.ConnectTimeout：请求连接的超时时间

     hello-service.ribbon.ReadTimeout：请求处理的超时时间

     hello-service.ribbon.OkToRetryOnAllOperations：对所有操作请求都进行重试

     hello-service.ribbon.MaxAutoRetriesNextServer：切换实例的重试次数

     hello-service.ribbon.MaxAutoRetries：对当前实例的重试次数
     二、指定Eureka的Environment
     eureka.environment: 指定环境
     三、指定Eureka的DataCenter
     eureka.datacenter: 指定数据中心
     四、Whitelabel Error Page
     @springBootApplication在进行加载时，只会加载其入口的当前目录及其子目录下的服务，如果存放在其它目录下，应用扫描不到