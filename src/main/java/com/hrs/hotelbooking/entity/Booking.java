package com.hrs.hotelbooking.entity;

import com.hrs.hotelbooking.enumeration.BookingStatus;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * The entity represents a booking.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
@Getter
@Setter
@Entity
@Table(name = "bookings")
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_id")
  private Room room;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @NotNull
  private LocalDateTime checkIn;

  @NotNull
  private LocalDateTime checkOut;

  @NotNull
  private BookingStatus status;

  private String failReason;

  @NotNull
  private LocalDateTime createdAt;

}
