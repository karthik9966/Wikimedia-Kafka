package com.ttu.service;

import com.ttu.Repository.KafkaConsumerRepository;
import com.ttu.model.WikimediaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WikimediaService {

    @Autowired
    private KafkaConsumerRepository kafkaConsumerRepository;

    @KafkaListener(topics = "${spring.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeData(String event)
    {
        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiData(event);
        kafkaConsumerRepository.save(wikimediaData);
    }
}
