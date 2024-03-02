package com.hrs.hotelbooking.service.impl;


import com.hrs.hotelbooking.service.TimeCalculatorService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeCalculatorServiceImpl implements TimeCalculatorService {

  // Depend of Hotel rules, this should be configure in Hotel table but to keep simple as possible so I hard code this one
  private static final int CHECK_IN_HOUR = 15;
  private static final int CHECK_IN_MINUTE = 30;
  private static final int CHECK_OUT_HOUR = 12;
  private static final int CHECK_OUT_MINUTE = 30;

  @Override
  public LocalDateTime calculateCheckInTime(LocalDate checkIn) {
    return checkIn.atTime(CHECK_IN_HOUR, CHECK_IN_MINUTE);
  }

  @Override
  public LocalDateTime calculateCheckOutTime(LocalDate checkOut) {
    return checkOut.atTime(CHECK_OUT_HOUR, CHECK_OUT_MINUTE);
  }

}
