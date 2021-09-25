package com.dancinggo.api.service;

import com.dancinggo.api.response.ChallengeMyBadgesRes;
import com.dancinggo.api.response.ChallengeOnlyBadgeIdRes;
import com.dancinggo.db.entity.Challenge;
import com.dancinggo.db.repository.BadgeRepository;
import com.dancinggo.db.repository.ChallengeRepository;
import com.dancinggo.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChallengeServiceImpl implements ChallengeService{

    @Autowired
    ChallengeRepository challengeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BadgeRepository badgeRepository;

    @Override
    public List<ChallengeMyBadgesRes> myBadges(String userNickname) {
        List<Challenge> myChallenges = challengeRepository.findByUser_UserNickname(userNickname);
        List<ChallengeMyBadgesRes> myBadgesResList = new ArrayList<>();
        for (Challenge c : myChallenges) {
            myBadgesResList.add(ChallengeMyBadgesRes.builder()
                    .badgeId(c.getBadge().getBadgeId())
                    .userNickname(c.getUser().getUserNickname())
                    .build()
            );
        }
        return myBadgesResList;
    }

    @Override
    public void addChallenge(String userNickname, Long badgeId) {

//        Optional<User> user = userRepository.findByUserNickname(userNickname);
//        if (user.isPresent()) {
//            System.out.println(userNickname);
//            System.out.println("있어");
//        }else {
//            System.out.println(userNickname);
//            System.out.println("없어");
//        }
        challengeRepository.save(Challenge.builder()
                .user(userRepository.findByUserNickname(userNickname).get())
                .badge(badgeRepository.findByBadgeId(badgeId).get())
                .build()
        );

    }

    @Override
    public List<ChallengeOnlyBadgeIdRes> onlyMyBadges(String userNickname) {

        List<Challenge> myChallenges = challengeRepository.findByUser_UserNickname(userNickname);
        List<ChallengeOnlyBadgeIdRes> challengeOnlyBadgeIdResList = new ArrayList<>();
        for (Challenge c : myChallenges) {
            challengeOnlyBadgeIdResList.add(ChallengeOnlyBadgeIdRes.builder()
                    .badgeId(c.getBadge().getBadgeId())
                    .build()
            );
        }

        return challengeOnlyBadgeIdResList;
    }
}
