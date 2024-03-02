package com.hrs.hotelbooking.mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;

import com.hrs.hotelbooking.openapi.model.HotelDto;
import com.hrs.hotelbooking.openapi.model.HotelsDto;
import com.hrs.hotelbooking.repository.view.HotelView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

/**
 * This interface support mappings related to Hotel.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
@Mapper(componentModel = "spring",
    uses = {LocationMapper.class},
    nullValueCheckStrategy = ALWAYS)
public interface HotelMapper {

  @Mapping(target = "location", source = "location", qualifiedByName = "location.toDto")
  HotelDto toHotelDto(HotelView bookingView);

  default HotelsDto toHotelsDto(Page<HotelView> hotelViewPage) {
    HotelsDto hotelsDto = new HotelsDto();
    if (hotelViewPage.hasContent()) {
      Page<HotelDto> hotelDtoPage = hotelViewPage.map(this::toHotelDto);
      hotelsDto.setData(hotelDtoPage.toList());
      hotelsDto.setTotal(hotelDtoPage.getTotalElements());
    }
    return hotelsDto;
  }

}
