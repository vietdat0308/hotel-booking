package com.hrs.hotelbooking.mapper;


import com.cloud.galactic.openapi.model.BookingStatusDto;
import com.hrs.hotelbooking.enumeration.BookingStatus;
import java.util.Objects;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

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