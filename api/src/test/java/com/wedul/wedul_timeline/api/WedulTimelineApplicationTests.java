package com.wedul.wedul_timeline.api;

import com.wedul.wedul_timeline.api.service.TimeLineItemService;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import com.wedul.wedul_timeline.core.repository.TimeLineSiteRepository;
import com.wedul.wedul_timeline.core.type.EnumSiteType;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WedulTimelineApplicationTests {

  @Autowired
  private TimeLineItemService timeLineItemService;

  @Autowired
  private TimeLineSiteRepository timeLineSiteRepository;

  @Test
  @Transactional
  public void test() {
    TimeLineItem timeLineItem = TimeLineItem.builder()
            .content("dbsafer")
            .landingUrl("dd")
            .build();

    timeLineItemService.insertTimeLineItem(timeLineItem);
  }

  @Test
  public void timelinesite_get() {
    List<TimeLineSite> timeLineSiteList = Lists.newArrayList(timeLineSiteRepository.findAll());

    System.out.println(timeLineSiteList);
  }

}
