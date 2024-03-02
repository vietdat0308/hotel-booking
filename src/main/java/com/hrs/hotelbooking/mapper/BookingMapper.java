package com.hrs.hotelbooking.mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;

import com.hrs.hotelbooking.entity.Booking;
import com.hrs.hotelbooking.entity.Room;
import com.hrs.hotelbooking.entity.User;
import com.hrs.hotelbooking.enumeration.BookingStatus;
import com.hrs.hotelbooking.messaging.event.BookingEvent;
import com.hrs.hotelbooking.openapi.model.BookingCreateDto;
import com.hrs.hotelbooking.openapi.model.BookingDto;
import com.hrs.hotelbooking.openapi.model.BookingsDto;
import com.hrs.hotelbooking.repository.view.BookingView;
import java.time.LocalDateTime;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

/**
 * This interface support mappings related to Booking.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
@Mapper(componentModel = "spring",
    uses = {BookingStatusMapper.class},
    imports = {BookingStatus.class, LocalDateTime.class},
    nullValueCheckStrategy = ALWAYS)
public interface BookingMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "checkIn", source = "checkIn")
  @Mapping(target = "checkOut", source = "checkOut")
  @Mapping(target = "status", expression = "java(BookingStatus.PENDING)")
  @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
  Booking toEntity(BookingCreateDto bookingCreateDto, Room room, User user, LocalDateTime checkIn, LocalDateTime checkOut);

  @Mapping(target = "bookingId", source = "id")
  @Mapping(target = "roomId", source = "room.id")
  BookingEvent toBookingEvent(Booking booking);

  @Mapping(target = "status", source = "status", qualifiedByName = "status.toDto")
  BookingDto toBookingDto(BookingView bookingView);

  default BookingsDto toBookingsDto(Page<BookingView> bookingViewPage) {
    BookingsDto bookingsDto = new BookingsDto();
    if (bookingViewPage.hasContent()) {
      Page<BookingDto> bookingDtoPage = bookingViewPage.map(this::toBookingDto);
      bookingsDto.setData(bookingDtoPage.toList());
      bookingsDto.setTotal(bookingDtoPage.getTotalElements());
    }
    return bookingsDto;
  }

}
