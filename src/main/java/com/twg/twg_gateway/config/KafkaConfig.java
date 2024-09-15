package com.twg.twg_gateway.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    //start a bean
    //partitions(2): Sets the number of partitions for the topic to 2.
    // Partitions allow Kafka to split the data for parallel processing.

    //replicas(2): Sets the replication factor to 2.
    // This means that each partition of the topic will have 2 copies (or replicas) across different Kafka brokers to ensure data redundancy and fault tolerance.


    @Bean
    public NewTopic transactionTopic(){
        return TopicBuilder.name("transaction-topic")
                .partitions(2).replicas(1).build();
    }
}
