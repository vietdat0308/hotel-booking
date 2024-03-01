package com.hrs.hotelbooking.mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;

import com.cloud.galactic.openapi.model.BookingCreateDto;
import com.hrs.hotelbooking.entity.Booking;
import com.hrs.hotelbooking.enumeration.BookingStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
    uses = {BookingStatusMapper.class},
    imports = {BookingStatus.class},
    nullValueCheckStrategy = ALWAYS)
public interface BookingMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "status", expression = "java(BookingStatus.BOOKED)")
  Booking toEntity (BookingCreateDto bookingCreateDto);

}
