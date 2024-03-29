package com.wedul.wedul_timeline.batch.service.kafka;

import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.service.TimeLineItemService;
import com.wedul.wedul_timeline.core.util.ObjectHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    @Transactional
    public void pointChangedListener(String data) throws Exception {
        TimeLineItem timeLineItem = ObjectHelper.getInstance().readValue(data, TimeLineItem.class);
        TimeLineItem savedTimeLineItem = timeLineItemService.getTimeLineItem(timeLineItem.getSourceId());
        if (null != savedTimeLineItem) {
            savedTimeLineItem.copy(timeLineItem);
            savedTimeLineItem.setUpdateAt(System.currentTimeMillis());
            timeLineItemService.setTimeLineItem(savedTimeLineItem);
        } else {
            timeLineItem.setTimeLineSite(timeLineItem.getCopyTimeLineSite());
            timeLineItemService.setTimeLineItem(timeLineItem);
        }
    }

    public void sendMessage(String data) {
        kafkaTemplate.send(topic, data);
    }

}
