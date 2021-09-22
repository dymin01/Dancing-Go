package com.dancinggo.api.service;

import com.dancinggo.api.request.ScoreSaveReq;
import com.dancinggo.api.response.songRankRes;

import java.util.List;

public interface ScoreService {

    Boolean saveScoreValue(ScoreSaveReq scoreSaveReq);

    List<songRankRes> songRank(Long songId);

}
