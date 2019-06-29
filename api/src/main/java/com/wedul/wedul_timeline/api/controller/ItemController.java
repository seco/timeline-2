package com.wedul.wedul_timeline.api.controller;

import com.wedul.wedul_timeline.api.service.TimeLineItemApiService;
import com.wedul.wedul_timeline.core.config.error.NotFoundException;
import com.wedul.wedul_timeline.core.type.EnumSiteType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-05-22
 **/
@RestController
@RequestMapping(value = "/item")
@RequiredArgsConstructor
public class ItemController {

  private final TimeLineItemApiService timeLineItemService;

  /**
   * 아이템 리스트 가져오기
   *
   * @param
   * @return
   * @throws NotFoundException
   */
  @GetMapping("")
  public ResponseEntity timeLineItems(Pageable pageable) throws NotFoundException {
    return ResponseEntity.ok(timeLineItemService.timeLineItems(pageable));
  }

  /**
   * 사이트 별로 아이템 리스트 가져오기
   *
   * @param
   * @return
   * @throws NotFoundException
   */
  @GetMapping("/site/{type}")
  public ResponseEntity timeLineItemsBySiteType(Pageable pageable, @PathVariable String type) throws NotFoundException {
    return ResponseEntity.ok(timeLineItemService.timeLineItemsBySiteType(pageable, type));
  }

}
