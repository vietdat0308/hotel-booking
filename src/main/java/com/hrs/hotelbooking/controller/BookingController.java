package com.hrs.hotelbooking.controller;

import com.cloud.galactic.openapi.api.BookingsApi;
import com.cloud.galactic.openapi.model.BookingCreateDto;
import com.hrs.hotelbooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookingController implements BookingsApi {

  private final BookingService bookingService;

  @Override
  public ResponseEntity<Long> createBooking(BookingCreateDto bookingCreateDto) {
    return new ResponseEntity<>(bookingService.createBooking(bookingCreateDto), HttpStatus.CREATED);
  }

}