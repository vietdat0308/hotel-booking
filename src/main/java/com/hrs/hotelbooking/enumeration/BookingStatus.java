package com.hrs.hotelbooking.enumeration;

import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum BookingStatus {

  BOOKED(0), COMPLETED(1), CANCELLED(2);

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

//  public static Status toEntity(StatusDto status) {
//    if (Objects.isNull(status)) {
//      return null;
//    }
//    return Status.valueOf(status.toString());
//  }

}
