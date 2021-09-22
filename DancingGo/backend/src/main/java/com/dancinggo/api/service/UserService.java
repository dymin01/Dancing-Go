package com.dancinggo.api.service;

import com.dancinggo.api.request.NicknameSaveReq;
import com.dancinggo.db.entity.User;
import com.dancinggo.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    public boolean nicknameisExists(String userNickname) {
        if(userRepository.findByUserNickname(userNickname).isPresent()){
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
}
