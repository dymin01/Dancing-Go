package com.dancinggo.api.controller;

import com.dancinggo.api.request.MyScoreReq;
import com.dancinggo.api.request.ScoreSaveReq;
import com.dancinggo.api.response.MyScoreRes;
import com.dancinggo.api.response.songRankRes;
import com.dancinggo.api.service.ScoreService;
import com.dancinggo.api.service.UserService;
import com.dancinggo.common.response.BaseResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "점수 API", tags = {"Score"})
@RequestMapping("/score")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class ScoreController {

    @Autowired
    ScoreService scoreService;

    // 점수 등록
    @PostMapping("/saveScoreValue")
    @ApiOperation(value = "점수 등록", notes = "cnt+1 해주고 점수는 최댓값을 저장한다.")
    public ResponseEntity<? extends BaseResponseBody> saveScoreValue(@RequestBody ScoreSaveReq scoreSaveReq) {
        if (scoreService.saveScoreValue(scoreSaveReq)) {
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "점수 등록 성공"));
        } else {
            return ResponseEntity.status(999).body(BaseResponseBody.of(999, "점수 등록 실패"));
        }
    }

    // 곡 별 랭킹 리스트 가져오기
    @GetMapping("/songRank/{songId}")
    @ApiOperation(value = "곡 별 랭킹 리스트 가져오기", notes = "곡 별 랭킹을 가져온다")
    public ResponseEntity<List<songRankRes>> songRank(@PathVariable("songId") Long songId) {
        List<songRankRes> songRankResList = scoreService.songRank(songId);
        return new ResponseEntity<List<songRankRes>>(songRankResList, HttpStatus.OK);
    }

    // 해당 곡에 대한 나의 점수, 랭크, 플레이 횟수 가져오기
    @PostMapping("/findMyScore")
    @ApiOperation(value = "해당 곡에 대한 나의 점수, 랭크, 플레이 횟수 가져오기", notes = "해당 곡에 대한 나의 점수, 랭크, 플레이 횟수를 가져온다.")
    public ResponseEntity<MyScoreRes> findMyScore(@RequestBody MyScoreReq myScoreReq) {
        MyScoreRes myScoreRes = scoreService.findMyScore(myScoreReq);
        return new ResponseEntity<MyScoreRes>(myScoreRes, HttpStatus.OK);
    }

}
