package com.hrs.hotelbooking.repository;

import com.hrs.hotelbooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This class provides methods working with User data.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
