package com.dancinggo.api.service;

import com.dancinggo.api.request.NicknameSaveReq;
import com.dancinggo.api.response.UserGameInfoRes;
import com.dancinggo.db.entity.User;
import com.dancinggo.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    public boolean nicknameisExists(String userNickname) {
        if (userRepository.findByUserNickname(userNickname).isPresent()) {
            return true;
        }
        return false;
    }

    public boolean setUserNickName(NicknameSaveReq nicknameSaveReq) {

        User user = userRepository.findByUserId(nicknameSaveReq.getUserId());

        user.setUserNickname(nicknameSaveReq.getUserNickname());

        userRepository.save(user);

        return true;

    }

    public int getRank(Long totalScore) {
        return userRepository.findByRank(totalScore);
    }

    public List<User> getAllRank() {

        List<User> userList = userRepository.findAll(Sort.by("totalScore").descending());

        return userList;
    }

    public boolean gameover(String userNickname) {

        User user = userRepository.findByUserNickname(userNickname).get();
        user.setGameoverCnt(user.getGameoverCnt() + 1);
        userRepository.save(user);

        return true;
    }

    public UserGameInfoRes gameInfoRes(String userId) {
        User user = userRepository.findByUserId(userId);
        List<User> userList = this.getAllRank();
        int rank = 0;
        // 총 점수가 없으면 랭킹은 -1 로
        if (user.getTotalScore() == 0 || user.getTotalScore() == null) {
            rank = -1;
        } else {
            rank = this.getRank(user.getTotalScore()) + 1;
        }
        UserGameInfoRes userGameInfoRes = UserGameInfoRes.builder()
                .userSeq(user.getUserSeq())
                .userNickname(user.getUserNickname())
                .rank(rank)
                .totalPlayCnt(user.getTotalPlayCnt())
                .gameoverCnt(user.getGameoverCnt())
                .build();

        return userGameInfoRes;
    }

}
