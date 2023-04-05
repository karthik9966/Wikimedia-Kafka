package com.ttu;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class ProducerService {

    private KafkaTemplate<String,String> kafkaTemplate;

    @Value("${spring.topic.name}")
    String topic;

    public ProducerService(KafkaTemplate<String,String> kafkaTemplate)
    {
        this.kafkaTemplate=kafkaTemplate;
    }

    public void sendToBroker() throws InterruptedException {
        EventHandler eventHandler = new WikimediaEventHandler(kafkaTemplate, topic);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder builder = new EventSource.Builder(eventHandler,URI.create(url));
        EventSource eventSource = builder.build();
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }
}
