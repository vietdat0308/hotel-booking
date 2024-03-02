package com.hrs.hotelbooking.service;

import com.hrs.hotelbooking.openapi.model.HotelFilterDto;
import com.hrs.hotelbooking.openapi.model.HotelsDto;
import com.hrs.hotelbooking.openapi.model.PageableDto;

/**
 * This interface provides operations related to managing hotels in a hotel reservation system.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
public interface HotelService {

    HotelsDto getHotels(String searchKey, HotelFilterDto filter, PageableDto pageable);

}
