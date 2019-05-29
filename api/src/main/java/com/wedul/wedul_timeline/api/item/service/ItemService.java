package com.wedul.wedul_timeline.api.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * 아이템 관련 서비스
 *
 * @author wedul
 * @since 2019-05-30
 **/
@Service
@RequiredArgsConstructor
public class ItemService {

  private final KafkaTemplate<String, String> kafkaTemplate;

  @KafkaListener(topics = "${config.kafka-topic.item}")
  public void pointChangedListener(String data) {
    System.out.println(data);
  }

  public void sendMessage(String data) {
    kafkaTemplate.send("wedul_timeline", data);
  }

}
