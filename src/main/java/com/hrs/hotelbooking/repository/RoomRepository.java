package com.hrs.hotelbooking.repository;

import com.hrs.hotelbooking.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    boolean existsById(Long id);

}
