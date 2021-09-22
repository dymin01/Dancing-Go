package com.dancinggo.api.service;

import com.dancinggo.api.request.ScoreSaveReq;
import com.dancinggo.api.response.songRankRes;
import com.dancinggo.db.entity.Score;
import com.dancinggo.db.entity.Song;
import com.dancinggo.db.entity.User;
import com.dancinggo.db.repository.ScoreRepository;
import com.dancinggo.db.repository.SongRepository;
import com.dancinggo.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SongRepository songRepository;

    @Override
    public Boolean saveScoreValue(ScoreSaveReq scoreSaveReq) {

        Optional<Score> score = scoreRepository.findByUser_UserNicknameAndSong_SongId(scoreSaveReq.getUserNickname(), scoreSaveReq.getSongId());

        if (score.isPresent()) { // 두번 이상 춘 곡일 때
            Score newScore = score.get();
            long playCnt = newScore.getPlayCnt();
            long value = newScore.getValue();
            long maxValue = Math.max(value, scoreSaveReq.getValue());

            newScore.setPlayCnt(playCnt + 1L);
            newScore.setValue(maxValue);

            scoreRepository.save(newScore);

        } else { // 처음 춘 곡일 때
            User user = userRepository.findByUserNickname(scoreSaveReq.getUserNickname()).get();
            Song song = songRepository.findBySongId(scoreSaveReq.getSongId()).get();

            scoreRepository.save(Score.builder()
                    .user(user)
                    .song(song)
                    .playCnt(1L)
                    .value(scoreSaveReq.getValue())
                    .build());
        }

        return true;
    }

    @Override
    public List<songRankRes> songRank(Long songId) {

        List<Score> scores = scoreRepository.findAllBySong_SongId(songId);

        // 정렬
        // 점수 내림차순
        // 점수가 같을 때는 play count 오름차순
        Collections.sort(scores, new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                if (o1.getValue() == o2.getValue()) {
                    return (int) (o1.getPlayCnt() - o2.getPlayCnt());
                }
                return (int) (o2.getValue() - o2.getValue());
            }
        });

        List<songRankRes> listRes = new ArrayList<>();
        for (Score score : scores) {
            User user = score.getUser();
            listRes.add(songRankRes.builder()
                    .userNickname(user.getUserNickname())
                    .userImg(user.getProfileImageUrl())
                    .value(score.getValue())
                    .build());
        }

        return listRes;
    }
}
