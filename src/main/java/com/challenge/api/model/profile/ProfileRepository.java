package com.challenge.api.model.profile;

import com.challenge.api.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
    Optional<Profile> findByUserName(String userName);

    @Query("SELECT u FROM User u WHERE u.id in (SELECT p.id FROM Profile p WHERE p.userName = :userName)")
    Optional<User> findUserByUserName(String userName);
}
