package com.hrs.hotelbooking.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * The entity represents a room type.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
@Getter
@Setter
@Entity
@Table(name = "room_type")
public class RoomType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private com.hrs.hotelbooking.enumeration.RoomType type;

  private String description;


}
