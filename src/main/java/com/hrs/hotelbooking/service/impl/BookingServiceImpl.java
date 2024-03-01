package com.hrs.hotelbooking.service.impl;

import com.cloud.galactic.openapi.model.BookingCreateDto;
import com.hrs.hotelbooking.entity.Booking;
import com.hrs.hotelbooking.mapper.BookingMapper;
import com.hrs.hotelbooking.repository.BookingRepository;
import com.hrs.hotelbooking.repository.RoomRepository;
import com.hrs.hotelbooking.repository.UserRepository;
import com.hrs.hotelbooking.service.BookingService;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

  private final RoomRepository roomRepository;
  private final UserRepository userRepository;
  private final BookingRepository bookingRepository;
  private final BookingMapper bookingMapper;

  @Override
  public Long createBooking(BookingCreateDto bookingCreateDto) {
    if(!roomRepository.existsById(bookingCreateDto.getRoomId())){
      throw new IllegalArgumentException("The room is not found");
    }
    if(!userRepository.existsById(bookingCreateDto.getRoomId())){
      throw new IllegalArgumentException("The user is not found");
    }
    final Booking booking = bookingMapper.toEntity(bookingCreateDto);
    return bookingRepository.save(booking).getId();
  }

}
