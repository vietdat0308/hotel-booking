package com.hrs.hotelbooking.repository;

import com.hrs.hotelbooking.entity.Hotel;
import com.hrs.hotelbooking.entity.User;
import com.hrs.hotelbooking.openapi.model.HotelFilterDto;
import com.hrs.hotelbooking.repository.view.BookingView;
import com.hrs.hotelbooking.repository.view.HotelView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This class provides methods working with Hotel data.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

  String HOTEL_SEARCH_QUERY =
      "SELECT hotels.id "
          + ", hotels.name "
          + ", hotels.location as locationValue "
          + "FROM hotels "
          + "WHERE (:searchKey IS NULL OR LOWER(hotels.name) LIKE CONCAT('%', LOWER(:searchKey), '%'))"
          + "   AND (:#{#filter.location} IS NULL OR hotels.location = :#{#filter.location})";

  String HOTEL_SEARCH_COUNT =
      " SELECT COUNT(1)" + " FROM ( " + HOTEL_SEARCH_QUERY + " ) as hotels";

  @Query(value = HOTEL_SEARCH_QUERY, countQuery = HOTEL_SEARCH_COUNT, nativeQuery = true)
  Page<HotelView> getHotels(
      @Param("filter") HotelFilterDto filter,
      @Param("searchKey") String searchKey,
      Pageable pageable);

}
