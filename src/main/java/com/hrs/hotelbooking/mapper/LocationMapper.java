package com.hrs.hotelbooking.mapper;


import com.hrs.hotelbooking.enumeration.Location;
import com.hrs.hotelbooking.openapi.model.LocationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * This interface support mappings related to Location.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
@Mapper(componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LocationMapper {

  @Named("location.toDto")
  default LocationDto toDto(Location location) {
    return LocationDto.valueOf(location.toString());
  }

}