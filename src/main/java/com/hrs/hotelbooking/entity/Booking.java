package com.hrs.hotelbooking.entity;

import com.hrs.hotelbooking.enumeration.BookingStatus;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bookings")
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "room_id")
  private Room room;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @NotNull
  private LocalDateTime checkIn;

  @NotNull
  private LocalDateTime checkOut;

  @NotNull
  private BookingStatus status;

  @NotNull
  private LocalDateTime createdAt;

}
