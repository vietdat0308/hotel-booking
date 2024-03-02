package com.hrs.hotelbooking.repository.view;

import com.hrs.hotelbooking.enumeration.BookingStatus;
import com.hrs.hotelbooking.enumeration.Location;
import java.time.LocalDateTime;

/**
 * HotelView interface provides projection fields working with Spring Data JPA.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
public interface HotelView {

  Long getId();

  String getName();

  Integer getLocationValue();

  default Location getLocation() {
    return Location.fromValue(getLocationValue());
  }


}
