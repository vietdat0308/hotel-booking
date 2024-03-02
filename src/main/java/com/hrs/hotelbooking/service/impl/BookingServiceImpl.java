package com.hrs.hotelbooking.service.impl;


import com.hrs.hotelbooking.entity.Booking;
import com.hrs.hotelbooking.entity.Room;
import com.hrs.hotelbooking.entity.User;
import com.hrs.hotelbooking.enumeration.BookingStatus;
import com.hrs.hotelbooking.mapper.BookingMapper;
import com.hrs.hotelbooking.mapper.PageableMapper;
import com.hrs.hotelbooking.messaging.MessageProducer;
import com.hrs.hotelbooking.openapi.model.BookingCreateDto;
import com.hrs.hotelbooking.openapi.model.BookingDto;
import com.hrs.hotelbooking.openapi.model.BookingsDto;
import com.hrs.hotelbooking.openapi.model.PageableDto;
import com.hrs.hotelbooking.repository.BookingRepository;
import com.hrs.hotelbooking.repository.RoomRepository;
import com.hrs.hotelbooking.repository.UserRepository;
import com.hrs.hotelbooking.repository.view.BookingView;
import com.hrs.hotelbooking.service.BookingService;
import com.hrs.hotelbooking.service.TimeCalculatorService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

  private final RoomRepository roomRepository;
  private final UserRepository userRepository;
  private final BookingRepository bookingRepository;
  private final BookingMapper bookingMapper;
  private final TimeCalculatorService timeCalculatorService;
  private final MessageProducer messageProducer;
  private final PageableMapper pageableMapper;

  @Value("${integration.activemq.queue.creating.booking}")
  private String bookingQueue;

  @Override
  @Transactional
  public Long createBooking(BookingCreateDto bookingCreateDto) {
    final Room room = roomRepository
        .findById(bookingCreateDto.getRoomId())
        .orElseThrow(() -> new IllegalArgumentException("The room is not found"));

    final User user = userRepository
        .findById(bookingCreateDto.getUserId())
        .orElseThrow(() -> new IllegalArgumentException("The user is not found"));

    final LocalDate checkInDate = bookingCreateDto.getCheckIn();
    final LocalDate checkOutDate = bookingCreateDto.getCheckOut();
    if (checkOutDate.minusDays(1).isBefore(checkInDate)) {
      throw new IllegalArgumentException("The checkout date must after checkin date at least 1 day");
    }

    LocalDateTime checkInTime = timeCalculatorService.calculateCheckInTime(checkInDate);
    LocalDateTime checkOutTime = timeCalculatorService.calculateCheckOutTime(checkOutDate);
    if (bookingRepository.isExistedBookingsForRoomOnThePeriod(room.getId(), checkInTime, checkOutTime, BookingStatus.BOOKED.getValue())) {
      throw new IllegalArgumentException("The room is already booked in that period time");
    }

    final Booking booking = bookingRepository.save(bookingMapper.toEntity(bookingCreateDto, room, user, checkInTime, checkOutTime));
    messageProducer.send(bookingQueue, bookingMapper.toBookingEvent(booking));
    return booking.getId();
  }

  @Override
  public BookingDto getBookingDetail(Long id) {
    final BookingView bookingView = bookingRepository
        .getBookingDetail(id)
        .orElseThrow(() -> new IllegalArgumentException("The booking is not found"));
    return bookingMapper.toBookingDto(bookingView);
  }

  @Override
  public BookingsDto getBookings(PageableDto pageableDto) {
    final Pageable pageable = pageableMapper.toPageable(pageableDto);
    final Page<BookingView> bookingViewPage = bookingRepository.getBookings(pageable);
    return bookingMapper.toBookingsDto(bookingViewPage);
  }

}
