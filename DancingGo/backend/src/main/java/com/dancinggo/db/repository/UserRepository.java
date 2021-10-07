package com.dancinggo.db.repository;

import com.dancinggo.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(String userId);

    Optional<User> findByUserNickname(String userNickname);

    @Query(value = "select count(U.total_score) from user U where U.total_score > :totalScore", nativeQuery = true)
    int findByRank(@Param("totalScore") Long totalScore);

}
