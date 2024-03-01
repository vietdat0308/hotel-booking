package com.hrs.hotelbooking.enumeration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum RoomType {

  STANDARD (0), PREMIUM(1);

  private final int value;

  private static final Map<Integer, RoomType> lookup = new HashMap<>();

  static {
    for (RoomType d : RoomType.values()) {
      lookup.put(d.getValue(), d);
    }
  }

  public static RoomType fromValue(Integer value) {
    return lookup.get(value);
  }

  public static Status toEntity(RoomType type) {
    if (Objects.isNull(type)) {
      return null;
    }
    return Status.valueOf(type.toString());
  }

}
