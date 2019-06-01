package com.wedul.wedul_timeline.api;

import com.wedul.wedul_timeline.api.service.TimeLineItemService;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.type.EnumSiteType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WedulTimelineApplicationTests {

  @Autowired
  private TimeLineItemService timeLineItemService;

  @Test
  @Transactional
  public void test() {
    TimeLineItem timeLineItem = TimeLineItem.builder()
            .content("dbsafer")
            .imageUrl("dd")
            .siteName("zz")
            .siteType(EnumSiteType.APT)
            .siteUrl("zz")
            .build();

    timeLineItemService.insertTimeLineItem(timeLineItem);
  }

}
