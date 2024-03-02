package com.hrs.hotelbooking.repository;

import com.hrs.hotelbooking.entity.Booking;
import com.hrs.hotelbooking.repository.view.BookingView;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This class provides methods working with Booking data.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

  @Query(
      value =
          "SELECT CASE WHEN COUNT(1) > 0 THEN TRUE "
              + "           ELSE FALSE "
              + "       END "
              + "FROM   bookings "
              + "WHERE  bookings.room_id = :roomId "
              + "AND    bookings.status = :status "
              + "AND    bookings.check_in < :checkOut "
              + "AND    bookings.check_out > :checkIn "
              + "AND (:checkIn BETWEEN bookings.check_in AND bookings.check_out OR :checkOut BETWEEN bookings.check_in AND bookings.check_out) "
              , nativeQuery = true)
  boolean isExistedBookingsForRoomOnThePeriod(
      @Param("roomId") final Long roomId,
      @Param("checkIn") final LocalDateTime checkIn,
      @Param("checkOut") final LocalDateTime checkOut,
      @Param("status") final int status);

  @Query(value = "SELECT bookings.id "
      + ", users.name as user "
      + ", rooms.name as room "
      + ", bookings.check_in as checkIn "
      + ", bookings.check_out as checkOut "
      + ", bookings.status as statusValue "
      + "FROM bookings "
      + "INNER JOIN users ON bookings.user_id = users.id "
      + "INNER JOIN rooms ON bookings.room_id = rooms.id "
      + "WHERE bookings.id = :id "
        , nativeQuery = true)
  Optional<BookingView> getBookingDetail(@Param("id") Long id);

  String BOOKING_SEARCH_QUERY =
      "SELECT bookings.id "
          + ", users.name as user "
          + ", rooms.name as room "
          + ", bookings.check_in as checkIn "
          + ", bookings.check_out as checkOut "
          + ", bookings.status as statusValue "
          + "FROM bookings "
          + "INNER JOIN users ON bookings.user_id = users.id "
          + "INNER JOIN rooms ON bookings.room_id = rooms.id "
          + "ORDER BY bookings.created_at desc";

  String BOOKING_SEARCH_COUNT =
      " SELECT COUNT(1)" + " FROM ( " + BOOKING_SEARCH_QUERY + " ) as bookings";

  @Query(value = BOOKING_SEARCH_QUERY, countQuery = BOOKING_SEARCH_COUNT, nativeQuery = true)
  Page<BookingView> getBookings(Pageable pageable);

}
