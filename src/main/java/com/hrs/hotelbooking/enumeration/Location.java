package com.hrs.hotelbooking.enumeration;

import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The enum lists locations.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
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

}
