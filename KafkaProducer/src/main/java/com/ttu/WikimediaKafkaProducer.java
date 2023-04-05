package com.ttu;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class WikimediaKafkaProducer {

    @Bean
    public NewTopic newTopic()
    {
        return TopicBuilder.name("${spring.topic.name}").build();
    }

    public static void main(String[] args) {
        SpringApplication.run(WikimediaKafkaProducer.class);
    }
}
