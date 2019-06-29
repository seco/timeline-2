package com.wedul.wedul_timeline.core.repository;

import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-06-01
 **/
@Repository
public interface TimeLineItemRepository extends JpaRepository<TimeLineItem, Long> {

    TimeLineItem findBySourceId(String sourceId);

    Page<TimeLineItem> findAllByTimeLineSite(Pageable pageable, TimeLineSite timeLineSite);

}
