package com.dancinggo.db.repository;


import com.dancinggo.db.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {

    Optional<Score> findByUser_UserNicknameAndSong_SongId(String userNickname, Long songId);

    List<Score> findAllBySong_SongId(Long songId);

    @Query(value = "select count(S.value) from score S where S.value > :value group by song_id having song_id = :songId", nativeQuery = true)
    Long findByRank(@Param("songId") Long songId, @Param("value") Long value);
}
