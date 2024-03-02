package com.hrs.hotelbooking.service;

import com.hrs.hotelbooking.openapi.model.BookingCreateDto;
import com.hrs.hotelbooking.openapi.model.BookingDto;
import com.hrs.hotelbooking.openapi.model.BookingsDto;
import com.hrs.hotelbooking.openapi.model.PageableDto;

/**
 * This interface provides operations related to managing bookings in a hotel reservation system.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
public interface BookingService {

    Long createBooking(BookingCreateDto bookingCreateDto);

    BookingDto getBookingDetail(Long id);

    BookingsDto getBookings(PageableDto pageableDto);
}
