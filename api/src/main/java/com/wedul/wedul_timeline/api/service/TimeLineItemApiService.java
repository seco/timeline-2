package com.wedul.wedul_timeline.api.service;

import com.google.common.collect.Lists;
import com.wedul.wedul_timeline.api.dto.ItemReqDto;
import com.wedul.wedul_timeline.core.config.error.NotFoundException;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.repository.TimeLineItemRepository;
import com.wedul.wedul_timeline.core.type.EnumSiteType;
import com.wedul.wedul_timeline.core.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

/**
 * 아이템 관련 서비스
 *
 * @author wedul
 * @since 2019-05-30
 **/
@Service
@RequiredArgsConstructor
public class TimeLineItemApiService {

    private final TimeLineItemRepository timeLineItemRepository;

    /**
     * enum site type 가져오기.
     *
     * @param
     * @return
     */
    @Transactional
    public Page<TimeLineItem> timeLineItems(Pageable pageable, ItemReqDto itemReqDto) throws ParseException {
        Page<TimeLineItem> timeLineItems;
        String searchQuery = itemReqDto.getSearchQuery();
        String searchDate = itemReqDto.getSearchDate();
        List<Long> dates = getDates(searchDate);

        if (StringUtils.isNotBlank(searchQuery) && StringUtils.isNotBlank(searchDate)) {
            timeLineItems = timeLineItemRepository.findAllByTitleLikeAndContentLikeAndUpdateAtBetween(pageable, searchQuery, dates.get(0), dates.get(1));
        } else if (StringUtils.isNotBlank(searchDate)) {
            timeLineItems = timeLineItemRepository.findAllByUpdateAtBetween(pageable, dates.get(0), dates.get(1));
        } else if (StringUtils.isNotBlank(searchQuery)) {
            timeLineItems = timeLineItemRepository.findAllByTitleLikeAndContentLike(pageable, searchQuery);
        } else {
            timeLineItems = timeLineItemRepository.findAll(pageable);
        }

        if (timeLineItems == null) throw new NotFoundException();
        return timeLineItems;
    }

    /**
     * enum site type 가져오기.
     *
     * @param
     * @return
     */
    @Transactional
    public Page<TimeLineItem> timeLineItemsBySiteType(Pageable pageable, String type, ItemReqDto itemReqDto) throws ParseException {
        EnumSiteType enumSiteType = EnumSiteType.getSiteType(type);
        if (null == enumSiteType) throw new NotFoundException();

        Page<TimeLineItem> timeLineItems;
        String searchQuery = itemReqDto.getSearchQuery();
        String searchDate = itemReqDto.getSearchDate();
        List<Long> dates = getDates(searchDate);

        if (StringUtils.isNotBlank(searchQuery) && StringUtils.isNotBlank(searchDate)) {
            timeLineItems = timeLineItemRepository.findAllBySiteTypeAndSearchQueryAndDate(pageable, type, searchQuery, dates.get(0), dates.get(1));
        } else if (StringUtils.isNotBlank(searchDate)) {
            timeLineItems = timeLineItemRepository.findAllBySiteTypeAndSearchDate(pageable, type, dates.get(0), dates.get(1));
        } else if (StringUtils.isNotBlank(searchQuery)) {
            timeLineItems = timeLineItemRepository.findAllBySiteTypeAndSearchQuery(pageable, type, searchQuery);
        } else {
            timeLineItems = timeLineItemRepository.findAllBySiteType(pageable, type);
        }

        if (timeLineItems == null) throw new NotFoundException();
        return timeLineItems;
    }

    private List<Long> getDates(String inputDate) throws ParseException {
        long startDate;
        long endDate;

        if (StringUtils.isBlank(inputDate)) {
            String toDay = DateUtil.todayDate();

            startDate = DateUtil.startUnixTimeStamp(toDay);
            endDate = DateUtil.endUnixTimeStamp(toDay);
        } else {
            startDate = DateUtil.startUnixTimeStamp(inputDate);
            endDate = DateUtil.endUnixTimeStamp(inputDate);
        }

        return Arrays.asList(startDate, endDate);
    }


}
