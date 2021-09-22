package com.dancinggo.db.repository;


import com.dancinggo.db.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {

    Optional<Score> findByUser_UserNicknameAndSong_SongId(String userNickname, Long songId);

}
