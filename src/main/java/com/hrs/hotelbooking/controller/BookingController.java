package com.hrs.hotelbooking.controller;

import com.hrs.hotelbooking.openapi.api.BookingsApi;
import com.hrs.hotelbooking.openapi.model.BookingCreateDto;
import com.hrs.hotelbooking.openapi.model.BookingDto;
import com.hrs.hotelbooking.openapi.model.BookingsDto;
import com.hrs.hotelbooking.openapi.model.PageableDto;
import com.hrs.hotelbooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller responsible for handling HTTP requests related to booking operations.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
@RestController
@RequiredArgsConstructor
public class BookingController implements BookingsApi {

  private final BookingService bookingService;

  @Override
  public ResponseEntity<Long> createBooking(BookingCreateDto bookingCreateDto) {
    return new ResponseEntity<>(bookingService.createBooking(bookingCreateDto), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<BookingDto> getBookingDetail(Long id) {
    return ResponseEntity.ok(bookingService.getBookingDetail(id));
  }

  @Override
  public ResponseEntity<BookingsDto> getBookings(PageableDto pageable) {
    return ResponseEntity.ok(bookingService.getBookings(pageable));
  }
}