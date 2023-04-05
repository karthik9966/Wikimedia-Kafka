package com.ttu;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

import java.sql.SQLOutput;

@SpringBootApplication
public class WikimediaKafkaProducer implements CommandLineRunner {

    @Value("${spring.topic.name}")
    String topic;

    private ProducerService producerService;
    public WikimediaKafkaProducer(ProducerService producerService)
    {
        this.producerService = producerService;
    }

    @Bean
    public NewTopic newTopic()
    {
        return TopicBuilder.name(topic).build();
    }

    public static void main(String[] args)  {
        SpringApplication.run(WikimediaKafkaProducer.class);
    }

    @Override
    public void run(String... args) throws Exception {
        producerService.sendToBroker();
    }
}
