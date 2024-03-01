package com.hrs.hotelbooking.enumeration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Status {

   INACTIVE(0) ,ACTIVE(1);

  private final int value;

  private static final Map<Integer, Status> lookup = new HashMap<>();

  static {
    for (Status d : Status.values()) {
      lookup.put(d.getValue(), d);
    }
  }

  public static Status fromValue(Integer value) {
    return lookup.get(value);
  }

//  public static Status toEntity(StatusDto status) {
//    if (Objects.isNull(status)) {
//      return null;
//    }
//    return Status.valueOf(status.toString());
//  }

}
