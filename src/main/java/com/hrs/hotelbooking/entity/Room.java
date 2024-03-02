package com.hrs.hotelbooking.entity;

import com.hrs.hotelbooking.enumeration.Status;
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
 * The entity represents a room.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
@Getter
@Setter
@Entity
@Table(name = "rooms")
public class Room {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "hotel_id")
  @NotNull
  private Hotel hotel;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_type_id")
  @NotNull
  private RoomType roomType;

  @NotNull
  private Double price;

  @NotNull
  private Status status;

}
