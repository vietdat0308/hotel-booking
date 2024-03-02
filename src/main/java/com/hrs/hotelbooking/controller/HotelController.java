package com.hrs.hotelbooking.controller;

import com.hrs.hotelbooking.openapi.api.HotelsApi;
import com.hrs.hotelbooking.openapi.model.HotelFilterDto;
import com.hrs.hotelbooking.openapi.model.HotelsDto;
import com.hrs.hotelbooking.openapi.model.PageableDto;
import com.hrs.hotelbooking.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller responsible for handling HTTP requests related to hotel operations.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
@RestController
@RequiredArgsConstructor
public class HotelController implements HotelsApi {

  private final HotelService hotelService;

  @Override
  public ResponseEntity<HotelsDto> getHotels(String searchKey, HotelFilterDto filter, PageableDto pageableDto) {
    return ResponseEntity.ok(hotelService.getHotels(searchKey, filter, pageableDto));
  }
}