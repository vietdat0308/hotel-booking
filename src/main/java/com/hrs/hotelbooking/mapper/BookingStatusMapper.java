package com.hrs.hotelbooking.mapper;


import com.hrs.hotelbooking.enumeration.BookingStatus;
import com.hrs.hotelbooking.openapi.model.BookingStatusDto;
import java.util.Objects;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * This interface support mappings related to Booking Status.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
@Mapper(componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookingStatusMapper {

  @Named("status.toDto")
  default BookingStatusDto toDto(BookingStatus status) {
    return BookingStatusDto.valueOf(status.toString());
  }


  @Named("status.toEntity")
  default BookingStatus toEntity(BookingStatusDto status) {
    if (Objects.isNull(status)) {
      return null;
    }
    return BookingStatus.valueOf(status.toString());
  }


}