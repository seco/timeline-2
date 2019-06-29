package com.wedul.wedul_timeline.api;

import com.wedul.wedul_timeline.api.service.TimeLineItemApiService;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import com.wedul.wedul_timeline.core.repository.TimeLineSiteRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WedulTimelineApplicationTests {

  @Autowired
  private TimeLineItemApiService timeLineItemService;

  @Autowired
  private TimeLineSiteRepository timeLineSiteRepository;

  @Test
  public void timelinesite_get() {
    List<TimeLineSite> timeLineSiteList = Lists.newArrayList(timeLineSiteRepository.findAll());

    System.out.println(timeLineSiteList);
  }

}
