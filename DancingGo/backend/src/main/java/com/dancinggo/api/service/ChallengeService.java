package com.dancinggo.api.service;

import com.dancinggo.api.response.ChallengeMyBadgesRes;
import com.dancinggo.db.entity.Challenge;

import java.util.List;

public interface ChallengeService {
    List<ChallengeMyBadgesRes> myBadges(String userNickname);

    void addChallenge(String userNickname, Long badgeId);

}
