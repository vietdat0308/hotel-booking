package com.hrs.hotelbooking.messaging.event;

import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * This class represents a booking event for exchange.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
@Data
public class BookingEvent {

  @NotEmpty
  private Long bookingId;

  @NotEmpty
  private Long roomId;

  @NotEmpty
  private LocalDateTime checkIn;

  @NotEmpty
  private LocalDateTime checkOut;

}