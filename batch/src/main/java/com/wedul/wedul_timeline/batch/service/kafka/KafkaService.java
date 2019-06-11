package com.wedul.wedul_timeline.batch.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-06-11
 **/
@Service
public class KafkaService {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Value("${config.kafka-topic.item}")
    private String topic;

    @KafkaListener(topics = "${config.kafka-topic.item}")
    public void pointChangedListener(String data) {
        System.out.println(data);
    }

    public void sendMessage(String data) {
        kafkaTemplate.send(topic, data);
    }

}
