package com.hrs.hotelbooking.enumeration;

import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Location {

   HCM(0) ,HN(1);

  private final int value;

  private static final Map<Integer, Location> lookup = new HashMap<>();

  static {
    for (Location d : Location.values()) {
      lookup.put(d.getValue(), d);
    }
  }

  public static Location fromValue(Integer value) {
    return lookup.get(value);
  }

//  public static Status toEntity(StatusDto status) {
//    if (Objects.isNull(status)) {
//      return null;
//    }
//    return Status.valueOf(status.toString());
//  }

}
