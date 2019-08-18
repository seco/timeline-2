package com.wedul.wedul_timeline.batch.job;

import com.wedul.wedul_timeline.batch.step.job.api.CoupangJobService;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static com.google.common.base.CharMatcher.isNot;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-08-18
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class CoupangJobServiceTest {

    private CoupangJobService coupangJobService;

    @Before
    public void setUp() {
        this.coupangJobService = new CoupangJobService();
    }

    @Test
    public void coupang_test() throws IOException {
        TimeLineSite timeLineSite = TimeLineSite.builder().siteUrl("https://rocketyourcareer.kr.coupang.com/search-jobs/results?ActiveFacetID=62822&CurrentPage=1&RecordsPerPage=15&Distance=50&RadiusUnitType=0&Keywords=&Location=%EB%8C%80%ED%95%9C%EB%AF%BC%EA%B5%AD&Latitude=&Longitude=&ShowRadius=False&CustomFacetName=&FacetTerm=&FacetType=0&FacetFilters[0].ID=62822&FacetFilters[0].FacetType=1&FacetFilters[0].Count=10&FacetFilters[0].Display=Software+Development+Engineers&FacetFilters[0].IsApplied=true&FacetFilters[0].FieldName=&FacetFilters[1].ID=1835841&FacetFilters[1].FacetType=2&FacetFilters[1].Count=80&FacetFilters[1].Display=%EB%8C%80%ED%95%9C%EB%AF%BC%EA%B5%AD&FacetFilters[1].IsApplied=true&FacetFilters[1].FieldName=&SearchResultsModuleName=Search+Results&SearchFiltersModuleName=Search+Filters&SortCriteria=0&SortDirection=0&SearchType=6&CategoryFacetTerm=&CategoryFacetType=&LocationFacetTerm=&LocationFacetType=&KeywordType=&LocationType=&LocationPath=&OrganizationIds=24450&PostalCode=&fc=&fl=&fcf=&afc=&afl=&afcf=").build();

        List<TimeLineItem> timeLineItemList = coupangJobService.crawl(timeLineSite);
        assertThat(timeLineItemList.size(), not(0));
    }


}
