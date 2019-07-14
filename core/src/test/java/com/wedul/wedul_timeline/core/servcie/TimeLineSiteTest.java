package com.wedul.wedul_timeline.core.servcie;

import com.wedul.wedul_timeline.core.config.common.RootApplicationContextConfig;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import com.wedul.wedul_timeline.core.repository.TimeLineSiteRepository;
import com.wedul.wedul_timeline.core.type.EnumSiteType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RootApplicationContextConfig.class)
@ActiveProfiles(value = {"spring", "config", "development", "default"})
@EntityScan("com.wedul.wedul_timeline.core.entity")
@EnableJpaRepositories(basePackages = "com.wedul.wedul_timeline.core")
public class TimeLineSiteTest {

    @Autowired
    private TimeLineSiteRepository timeLineSiteRepository;

    @Test
    public void addTechSiteToTimeLineSite() {
        TimeLineSite timeLineSite = TimeLineSite.builder()
                .siteType(EnumSiteType.TECH)
                .siteName("ZoomInternetTech")
                .siteUrl("https://zuminternet.github.io/feed.xml")
                .build();

        TimeLineSite savedTimeLineSite = timeLineSiteRepository.save(timeLineSite);

        Assert.assertTrue(timeLineSiteRepository.getOne(savedTimeLineSite.getSiteId()) != null);
    }

}
