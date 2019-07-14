package com.wedul.wedul_timeline.core.repository;

import com.wedul.wedul_timeline.core.entity.TimeLineSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * timelineItem site 관리 레포지토리
 *
 * @author wedul
 * @since 2019-06-02
 **/
@Repository
public interface TimeLineSiteRepository extends JpaRepository<TimeLineSite, Long> {
}
