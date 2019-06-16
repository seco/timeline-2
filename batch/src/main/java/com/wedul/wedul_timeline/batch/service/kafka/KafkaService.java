package com.wedul.wedul_timeline.batch.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.service.TimeLineItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-06-11
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final TimeLineItemService timeLineItemService;

    @Value("${config.kafka-topic.item}")
    private String topic;

    @KafkaListener(topics = "${config.kafka-topic.item}")
    public void pointChangedListener(String data) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            TimeLineItem timeLineItem = objectMapper.readValue(data, TimeLineItem.class);
            timeLineItemService.setTimeLineItem(timeLineItem);
        } catch (IOException e) {
            log.error("Fail save timeline item", e);
        }
    }

    public void sendMessage(String data) {
        kafkaTemplate.send(topic, data);
    }

}
