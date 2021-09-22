package com.dancinggo.api.service;

import com.dancinggo.db.entity.Badge;
import com.dancinggo.db.entity.Challenge;
import com.dancinggo.db.repository.BadgeRepository;
import com.dancinggo.db.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgeServiceImpl implements BadgeService{

    @Autowired
    BadgeRepository badgeRepository;

    @Override
    public List<Badge> allBadgeList() {
        return badgeRepository.findAll();
    }

}
