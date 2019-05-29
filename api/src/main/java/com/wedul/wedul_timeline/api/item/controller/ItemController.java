package com.wedul.wedul_timeline.api.item.controller;

import com.wedul.wedul_timeline.api.item.service.ItemService;
import com.wedul.wedul_timeline.core.config.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

  private final ItemService itemService;

  @GetMapping("")
  public ResponseEntity items() throws NotFoundException {
    itemService.sendMessage("dbsafer");
    return ResponseEntity.ok("d");
  }

}
