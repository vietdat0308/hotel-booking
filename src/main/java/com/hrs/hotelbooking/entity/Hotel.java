package com.hrs.hotelbooking.entity;


import com.hrs.hotelbooking.enumeration.Location;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * The entity represents a hotel.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
@Getter
@Setter
@Entity
@Table(name = "hotels")
public class Hotel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String name;

  @NotNull
  private Location location;

}
