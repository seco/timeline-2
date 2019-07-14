package com.wedul.wedul_timeline.core.repository;

import com.wedul.wedul_timeline.core.entity.TimeLineItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(
            value = "SELECT * FROM timeline_item t JOIN timeline_site s on t.site_id = s.site_id WHERE s.site_type = :siteType",
            nativeQuery = true
    )
    Page<TimeLineItem> findAllBySiteType(Pageable pageable, @Param("siteType") String siteType);

    @Query(
            value = "SELECT * " +
                    "FROM timeline_item t " +
                    "JOIN timeline_site s " +
                    "on t.site_id = s.site_id " +
                    "WHERE s.site_type = :siteType " +
                    "and (t.title like CONCAT('%',:searchQuery,'%') or t.content like CONCAT('%',:searchQuery,'%'))",
            nativeQuery = true
    )
    Page<TimeLineItem> findAllBySiteTypeAndSearchQuery(Pageable pageable, @Param("siteType") String siteType, @Param("searchQuery") String searchQuery);

    @Query(
            value = "SELECT * " +
                    "FROM timeline_item t " +
                    "JOIN timeline_site s " +
                    "on t.site_id = s.site_id " +
                    "WHERE s.site_type = :siteType " +
                    "and t.update_at >= :startDate and t.update_at <= :endDate " +
                    "and (t.title like CONCAT('%',:searchQuery,'%') or t.content like CONCAT('%',:searchQuery,'%'))",
            nativeQuery = true
    )
    Page<TimeLineItem> findAllBySiteTypeAndSearchQueryAndDate(Pageable pageable, @Param("siteType") String siteType, @Param("searchQuery") String searchQuery, @Param("startDate") long startDate, @Param("endDate") long endDate);

    @Query(
            value = "SELECT * " +
                    "FROM timeline_item t " +
                    "JOIN timeline_site s " +
                    "on t.site_id = s.site_id " +
                    "WHERE s.site_type = :siteType " +
                    "and t.update_at >= :startDate and t.update_at <= :endDate",
            nativeQuery = true
    )
    Page<TimeLineItem> findAllBySiteTypeAndSearchDate(Pageable pageable, @Param("siteType") String siteType, @Param("startDate") long startDate, @Param("endDate") long endDate);

    @Query(
            value = "SELECT * " +
                    "FROM timeline_item t " +
                    "WHERE t.update_at >= :startDate and t.update_at <= :endDate " +
                    "and (t.title like CONCAT('%',:searchQuery,'%') or t.content like CONCAT('%',:searchQuery,'%'))",
            nativeQuery = true
    )
    Page<TimeLineItem> findAllByTitleLikeAndContentLikeAndUpdateAtBetween(Pageable pageable, @Param("searchQuery") String searchQuery, @Param("startDate") long startDate, @Param("endDate") long endDate);

    @Query(
            value = "SELECT * " +
                    "FROM timeline_item t " +
                    "WHERE t.update_at >= :startDate and t.update_at <= :endDate ",
            nativeQuery = true
    )
    Page<TimeLineItem> findAllByUpdateAtBetween(Pageable pageable, @Param("startDate") long startDate, @Param("endDate") long endDate);

    @Query(
            value = "SELECT * " +
                    "FROM timeline_item t " +
                    "where t.title like CONCAT('%',:searchQuery,'%') or t.content like CONCAT('%',:searchQuery,'%')",
            nativeQuery = true
    )
    Page<TimeLineItem> findAllByTitleLikeAndContentLike(Pageable pageable, @Param("searchQuery") String searchQuery);

}