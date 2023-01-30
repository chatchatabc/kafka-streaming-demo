# Kafka Streaming Demo

This demo shows how to use Apache Kafka to stream messages from the producer to the consumer. The producer microservice
sends messages to a specified Kafka topic, and the consumer microservice consumes messages from an assigned topic.

This demo is self-contained (there is no entry point for user input). The producer service sends messages to the Kafka
topic every 3 seconds using a scheduler with a fixed rate.

# Prerequisites

- [Docker](https://docs.docker.com/) — for running Zookeeper and Kafka in a containerized environment
- [Spring Boot](https://spring.io/projects/spring-boot) — Java web framework used
- [Apache Kafka](https://kafka.apache.org/) — The event streaming platform used
- [Apache Zookeeper](https://zookeeper.apache.org/) - dependency of Kafka
- [Gson](https://github.com/google/gson) — Serialization library used to serialize and deserialize Java objects to and
  from JSON

# Running the demo

1. Run `docker-compose up` to start the Kafka and Zookeeper containers.

2. Run the producer microservice first using this command:
   ```shell
   ./mvnw :message-producer:bootRun
   ```
   The producer should now start to log generated names every 3 seconds.

3. It should automatically send messages to the Kafka topic.

4. Run the consumer microservice with this command:
    ```shell
    ./mvnw :message-consumer:bootRun
    ```
   The consumer logs should now be in-sync with the producer logs.

# Project Structure

This project is divided into 3 modules:

## `common` — Shared library

This module contains the shared classes used by the producer and consumer microservices.

- [`FullName`](message-common/src/main/java/arnado/mike/common/domain/model/FullName.java) — A simple class that
  contains the first and last name of a person.
- [`NameGeneratedEvent`](message-common/src/main/java/arnado/mike/common/domain/event/NameGeneratedEvent.java) — The
  class being sent to the Kafka topic. It contains the `FullName` object and a timestamp of the time it was generated.
- [`GenericSerializer`](message-common/src/main/java/arnado/mike/common/domain/serialization/GenericSerializer.java) and
  [`GenericDeserializer`](message-common/src/main/java/arnado/mike/common/domain/serialization/GenericDeserializer.java) —
  custom serializers and deserializers which utilize the Gson library.

## `kafka-producer` — Producer microservice

The producer microservice sends messages to the Kafka topic. It uses Faker to generate random names, transforms it into
the `FullName` class and gets wrapped in a `NameGeneratedEvent` object. The `NameGeneratedEvent` object is then
serialized to JSON and sent to the Kafka topic.

## `kafka-consumer` — Consumer microservice

The consumer microservice consumes messages from the Kafka topic. The message is deserialized to
the `NameGeneratedEvent` class and is sent to the consumer. The consumer then logs the `FullName` object.
                  
 
# TODO

* Root module absent
* Different between Spring Kafka and Spring Cloud Stream 
* Events processor in DDD

# References
      
* Kafka Home: https://kafka.apache.org/
* Spring Kafka: https://spring.io/projects/spring-kafka
* Spring Cloud Stream: https://docs.spring.io/spring-cloud-stream/docs/3.2.4/reference/html/index.html
* Spring Cloud Stream Kafka Binder: https://docs.spring.io/spring-cloud-stream/docs/3.2.4/reference/html/spring-cloud-stream-binder-kafka.html
* Spring Boot Kafka JsonSerializer Example: https://howtodoinjava.com/kafka/spring-boot-jsonserializer-example/
* Guide to Spring Cloud Stream with Kafka, Apache Avro and Confluent Schema Registry: https://www.baeldung.com/spring-cloud-stream-kafka-avro-confluent
