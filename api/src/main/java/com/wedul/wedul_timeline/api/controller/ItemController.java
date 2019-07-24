package com.wedul.wedul_timeline.api.controller;

import com.wedul.wedul_timeline.api.dto.ItemReqDto;
import com.wedul.wedul_timeline.api.dto.PageRequest;
import com.wedul.wedul_timeline.api.service.TimeLineItemApiService;
import com.wedul.wedul_timeline.core.config.error.NotFoundException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
  private final Map<String, List<String>> sortMap = new HashMap<String, List<String>>() {{
    put("native", Arrays.asList("published_at", "update_at"));
    put("nonNative", Arrays.asList("publishedAt", "updateAt"));
  }};

  /**
   * 아이템 리스트 가져오기
   *
   * @param
   * @return
   * @throws NotFoundException
   */
  @ApiOperation(value = "전체 보기 api")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "page", value = "읽을 페이지", dataType = "int", paramType = "query", defaultValue = "0"),
          @ApiImplicitParam(name = "size", value = "데이터 양", dataType = "int", paramType = "query", defaultValue = "10"),
          @ApiImplicitParam(name = "searchQuery", value = "검색내용", dataType = "string", paramType = "query"),
          @ApiImplicitParam(name = "searchData", value = "타임라인 날짜", dataType = "string", paramType = "query")
  })
  @GetMapping("")
  public ResponseEntity timeLineItems(PageRequest pageRequest, ItemReqDto itemReqDto) throws NotFoundException, ParseException {
    return ResponseEntity.ok(timeLineItemService.timeLineItems(pageRequest.of(itemReqDto.hasCondition() ? sortMap.get("native") : sortMap.get("nonNative")), itemReqDto));
  }

  /**
   * 사이트 별로 아이템 리스트 가져오기
   *
   * @param
   * @return
   * @throws NotFoundException
   */
  @ApiOperation(value = "주제별 보기 api")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "page", value = "읽을 페이지", dataType = "int", paramType = "query", defaultValue = "0"),
          @ApiImplicitParam(name = "size", value = "데이터 양", dataType = "int", paramType = "query", defaultValue = "10"),
          @ApiImplicitParam(name = "searchQuery", value = "검색내용", dataType = "string", paramType = "query"),
          @ApiImplicitParam(name = "searchData", value = "타임라인 날짜", dataType = "string", paramType = "query"),
          @ApiImplicitParam(name = "type", value = "가져오고 싶은 type", dataType = "string", paramType = "path")
  })
  @GetMapping("/site/{type}")
  public ResponseEntity timeLineItemsBySiteType(PageRequest pageRequest, ItemReqDto itemReqDto, @PathVariable String type) throws NotFoundException, ParseException {
    return ResponseEntity.ok(timeLineItemService.timeLineItemsBySiteType(pageRequest.of(sortMap.get("native")), type.toUpperCase(), itemReqDto));
  }

}
