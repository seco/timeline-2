package com.wedul.wedul_timeline.api.item.controller;

import com.wedul.wedul_timeline.core.config.error.NotFoundException;
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
public class ItemController {

  @GetMapping("")
  public String items() throws NotFoundException {
    throw new NotFoundException();
  }

}
