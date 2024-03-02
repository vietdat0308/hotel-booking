package com.hrs.hotelbooking.service;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * This interface provides operations related to calculate time in a hotel reservation system.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
public interface TimeCalculatorService {

    LocalDateTime calculateCheckInTime(LocalDate checkIn);

    LocalDateTime calculateCheckOutTime(LocalDate checkOut);

}
