package com.dancinggo.db.repository;

import com.dancinggo.db.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {
    //뱃지전체정보 가져오기
    List<Badge> findAll();

    Optional<Badge> findByBadgeId(Long badgeId);
}
