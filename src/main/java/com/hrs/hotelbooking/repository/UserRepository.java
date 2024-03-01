package com.hrs.hotelbooking.repository;

import com.hrs.hotelbooking.entity.Room;
import com.hrs.hotelbooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsById(Long id);

}
