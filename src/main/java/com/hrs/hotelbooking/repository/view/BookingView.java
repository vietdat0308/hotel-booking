package com.hrs.hotelbooking.repository.view;

import com.hrs.hotelbooking.enumeration.BookingStatus;
import java.time.LocalDateTime;

/**
 * BookingView interface provides projection fields working with Spring Data JPA.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
public interface BookingView {

  Long getId();

  String getUser();

  String getRoom();

  LocalDateTime getCheckIn();

  LocalDateTime getCheckOut();

  Integer getStatusValue();

  default BookingStatus getStatus() {
    return BookingStatus.fromValue(getStatusValue());
  }


}
