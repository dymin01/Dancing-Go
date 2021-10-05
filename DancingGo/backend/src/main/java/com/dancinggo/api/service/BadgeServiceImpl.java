package com.dancinggo.api.service;

import com.dancinggo.api.response.BadgeRes;
import com.dancinggo.db.entity.Badge;
import com.dancinggo.db.entity.Challenge;
import com.dancinggo.db.repository.BadgeRepository;
import com.dancinggo.db.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BadgeServiceImpl implements BadgeService{

    @Autowired
    BadgeRepository badgeRepository;

    @Override
    public List<Badge> allBadgeList() {
        return badgeRepository.findAll();
    }

    @Override
    public List<BadgeRes> findAllBadgeList() {
        List<Badge> badgeList = badgeRepository.findAll();
        List<BadgeRes> badgeResList = new ArrayList<>();

        for(Badge badge : badgeList) {
            BadgeRes badgeRes = BadgeRes.builder()
                    .badgeId(badge.getBadgeId())
                    .badgeNameEng(badge.getBadgeNameEng())
                    .badgeNameKor(badge.getBadgeNameKor())
                    .conditionEng(badge.getConditionEng())
                    .conditionKor(badge.getConditionKor())
                    .build();

            badgeResList.add(badgeRes);
        }

        return badgeResList;
    }
}
