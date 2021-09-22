package com.dancinggo.db.repository;

import com.dancinggo.db.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {

    Optional<Song> findBySongId(Long songId);

    List<Song> findAll();

}
