package com.wedul.wedul_timeline.core.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

/**
 * querydsl
 *
 * @author wedul
 * @since 2019-06-30
 **/
@Repository
public class TimeLineQueryDslRepository extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public TimeLineQueryDslRepository(JPAQueryFactory queryFactory) {
        super(TimeLineItem.class);
        this.queryFactory = queryFactory;
    }
}
