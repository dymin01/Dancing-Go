package com.dancinggo.api.service;

import com.dancinggo.api.request.ScoreSaveReq;
import com.dancinggo.api.response.SongScoreListRes;

import java.util.List;

public interface ScoreService {

    Boolean saveScoreValue(ScoreSaveReq scoreSaveReq);

    List<SongScoreListRes> songScoreList(Long songId);

}
