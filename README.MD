# spring-cloud-stream-sink-stomp-multi-topic
## For what is the simulator created for?
Data-MicroServices Application provided as Spring Cloud Stream

# I. Local Java CLI
## Prerequisites

* Maven
* RabbitMQ

### Download and Build
You can skip this step if you use the jar I uploaded.

```
git clone https://github.com/komushi/spring-cloud-stream-sink-stomp-multi-topic.git
cd spring-cloud-stream-sink-stomp-multi-topic
mvn clean package
```

### Start RabbitMQ

```
rabbitmq-server
```

## TODO: fix from here below

### 1. Test with Http Source

Start Local StompSink using RabbitMQ

```
java -jar target/spring-cloud-stream-sink-stomp-multi-topic-0.0.1-SNAPSHOT.jar --logging.level.io.pivotal.spring.cloud.stream.sink=TRACE --spring.cloud.stream.bindings.input.destination=transform_geotuple --spring.cloud.stream.bindings.input.group=stomp_raw_events --stomp.withSockJS=true --stomp.topicPath=/dropoffDistrictCode --server.port=9110
```

Start HttpSource using RabbitMQ

https://rawgit.com/komushi/spring-cloud-stream/master/http-source-rabbit-1.0.0.BUILD-SNAPSHOT.jar

```
java -jar time-source-rabbit-1.0.0.BUILD-SNAPSHOT.jar --server.port=7070 --spring.cloud.stream.bindings.output.destination=httpjson
```

Curl POST

```
curl -H "Content-Type: application/json" -X POST -d'[{"rank":1,"from":"C167.155","to":"C135.295","count":549},{"rank":2,"from":"C246.86","to":"C14.174","count":548},{"rank":3,"from":"C134.297","to":"C78.17","count":547},{"rank":4,"from":"C78.147","to":"C173.81","count":546},{"rank":5,"from":"C34.197","to":"C156.151","count":545},{"rank":6,"from":"C87.65","to":"C222.56","count":544},{"rank":7,"from":"C172.8","to":"C29.116","count":543},{"rank":8,"from":"C282.175","to":"C82.193","count":542},{"rank":9,"from":"C248.103","to":"C248.26","count":541},{"rank":10,"from":"C212.278","to":"C299.203","count":540}]' 'http://localhost:7070'
```

```
2016-06-28 15:02:28.345 TRACE 58483 --- [wSkCSHQR6F3dg-1] i.p.s.c.s.sink.StompSinkConfiguration    : Handling message: GenericMessage [payload=[{"rank":1,"from":"C167.155","to":"C135.295","count":549},{"rank":2,"from":"C246.86","to":"C14.174","count":548},{"rank":3,"from":"C134.297","to":"C78.17","count":547},{"rank":4,"from":"C78.147","to":"C173.81","count":546},{"rank":5,"from":"C34.197","to":"C156.151","count":545},{"rank":6,"from":"C87.65","to":"C222.56","count":544},{"rank":7,"from":"C172.8","to":"C29.116","count":543},{"rank":8,"from":"C282.175","to":"C82.193","count":542},{"rank":9,"from":"C248.103","to":"C248.26","count":541},{"rank":10,"from":"C212.278","to":"C299.203","count":540}], headers={amqp_receivedRoutingKey=httpjson, amqp_receivedExchange=httpjson, amqp_deliveryTag=1, amqp_consumerQueue=httpjson.anonymous.I6MUGzg0SwSkCSHQR6F3dg, amqp_redelivered=false, id=c04687c0-43a2-07a5-05e8-3477379a89cd, amqp_consumerTag=amq.ctag-pQRvXpUotbdYd7XxZ8QdcA, contentType=application/json, timestamp=1467093748342}]
```

You can subscribe http://localhost:8080/stomp at /topic/httpjson to retrieve the inbound messages.

# II. Another deployment option: docker image

## 1. Build docker image by maven
```
mvn clean package docker:build
```

if necessay push to docker hub

```
mvn docker:push -Ddocker.username=[provide your username] -Ddocker.password=[provide password]
```

##2. Run the image

Since we use docker container here and the Spring Cloud Stream App deault port is 8080 so the --server.port(the container port) parameter does not need to be specified for docker deployment.

```
docker run -p <host_port>:<container_port> komushi/spring-cloud-stream-sink-stomp-multi-topic <app_args>
```

```
docker run -p 9210:8080 komushi/spring-cloud-stream-sink-stomp-multi-topic --stomp.withsockjs=true --stomp.topic-path=/dropoffDistrictCode --spring.cloud.stream.bindings.input.destination=transform_geotuple --spring.cloud.stream.bindings.input.group=1 --spring.rabbitmq.host=192.168.11.111 --spring.rabbitmq.port=5672 --spring.rabbitmq.username=test --spring.rabbitmq.password=test
```