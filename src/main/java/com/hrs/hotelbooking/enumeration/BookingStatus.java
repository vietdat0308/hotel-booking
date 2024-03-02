package com.hrs.hotelbooking.enumeration;

import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The enum lists booking statuses.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum BookingStatus {

  PENDING(0), BOOKED(1), FAILED(2), COMPLETED(3), CANCELLED(4);

  private final int value;

  private static final Map<Integer, BookingStatus> lookup = new HashMap<>();

  static {
    for (BookingStatus d : BookingStatus.values()) {
      lookup.put(d.getValue(), d);
    }
  }

  public static BookingStatus fromValue(Integer value) {
    return lookup.get(value);
  }

}
