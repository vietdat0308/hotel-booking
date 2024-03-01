package com.hrs.hotelbooking.service;


import com.cloud.galactic.openapi.model.BookingCreateDto;

public interface BookingService {

    Long createBooking(BookingCreateDto bookingCreateDto);

}
