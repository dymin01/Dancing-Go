package com.dancinggo.db.repository;

import com.dancinggo.db.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    // 내가 획득한 뱃지 닉네임으로 가져오기
    List<Challenge> findByUser_UserNickname(String userNickname);


}
