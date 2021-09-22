package com.dancinggo.db.repository;

import com.dancinggo.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(String userId);

    Optional<User> findByUserNickname(String userNickname);

}
