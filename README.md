# Kafka Streaming Demo

This demo shows how to use Spring Cloud Stream along with Apache Kafka to stream messages from the producer to the
consumer. The producer microservice sends messages to a specified channel, and the consumer microservice transforms and
consumes messages from an assigned channel.

This demo is self-contained (there is no entry point for user input). The producer service sends messages to a channel
every 3 seconds using a scheduler with a fixed rate.

> Note: This README is a work-in-progress, this document might not reflect the current state of the project.

# Prerequisites

- [Docker](https://docs.docker.com/) — for running Zookeeper and Kafka in a containerized environment
- [Spring Boot](https://spring.io/projects/spring-boot) — Java web framework used
- [Spring Cloud Stream](https://spring.io/projects/spring-cloud-stream) — Spring framework for building message-driven
  microservices
- [Apache Kafka](https://kafka.apache.org/) — The event streaming platform used
- [Apache Zookeeper](https://zookeeper.apache.org/) - dependency of Kafka

# Running the demo

1. Run `docker-compose up` to start the Kafka and Zookeeper containers.

2. Run the producer microservice first using this command:
   ```shell
   ./mvnw :message-producer:spring-boot:run
   ```
   The producer should now start to log generated names every 3 seconds.

3. It should automatically send messages to the Kafka topic.

4. Run the consumer microservice with this command:
    ```shell
    ./mvnw :message-consumer:bootRun
    ```
   The consumer logs should now be in-sync with the producer logs.

# How Kafka Works

![Kafka Internal](/img/kafka-diagram.png)

# Spring Kafka vs Spring Cloud Stream

![Spring Kafka vs Spring Cloud Stream](/img/comparison.png)

# Stream Pipeline

![Stream Flow](/img/stream-flow.png)

# Project Structure

This project is divided into 3 modules:

1. `common` - where the 2 shared classes are stored: `User` and `UserCreatedEvent`
2. `message-producer` — generates a user with a random first and last name at an interval of 3 seconds. This is then
   sent to the `user-topic` Kafka topic using Spring Cloud Stream's `StreamBridge`.
3. `message-consumer` — transforms the received `User` into a `UserCreatedEvent`. This is then sent to
   the `user-created-topic`. The stream flow and entry points are defined in the `application.properties` configuration
   file.

   Currently, there are 2 consumers:
    - `count()` - counts the number of messages received and logs it.
    - `processEvent()` - logs the user contained in the `UserCreatedEvent`.

# TODO

- [x] Better README.md
- [x] Root module absent
- [x] Differences between Spring Kafka and Spring Cloud Stream
- [x] Events processor in DDD

# References

* Kafka: An Overview - https://medium.com/@vinciabhinav7/kafka-an-overview-859c51081a30
* Kafka Home: https://kafka.apache.org/
* Spring Kafka: https://spring.io/projects/spring-kafka
* Spring Cloud Stream: https://docs.spring.io/spring-cloud-stream/docs/3.2.4/reference/html/index.html
* Spring Cloud Stream Kafka
  Binder: https://docs.spring.io/spring-cloud-stream/docs/3.2.4/reference/html/spring-cloud-stream-binder-kafka.html
* Spring Boot Kafka JsonSerializer Example: https://howtodoinjava.com/kafka/spring-boot-jsonserializer-example/
* Guide to Spring Cloud Stream with Kafka, Apache Avro and Confluent Schema
  Registry: https://www.baeldung.com/spring-cloud-stream-kafka-avro-confluent
