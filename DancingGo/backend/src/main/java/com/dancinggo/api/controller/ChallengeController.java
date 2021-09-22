package com.dancinggo.api.controller;

import com.dancinggo.api.request.ChallengeAddReq;
import com.dancinggo.api.response.ChallengeMyBadgesRes;
import com.dancinggo.common.response.BaseResponseBody;
import com.dancinggo.api.service.ChallengeService;
import com.dancinggo.db.entity.Challenge;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "도전 API", tags = {"Challenge"})
@RequestMapping("/challenge")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class ChallengeController {

    @Autowired
    ChallengeService challengeService;

    // 닉네임으로 내가 획득한 뱃지 리스트 가져오기
    @GetMapping("/myBadge/{userNickname}")
    @ApiOperation(value = "", notes = "")
    public ResponseEntity<List<ChallengeMyBadgesRes>> myBadges(@PathVariable("userNickname") String userNickname) {
        List<ChallengeMyBadgesRes> challengeList = challengeService.myBadges(userNickname);
        return new ResponseEntity<List<ChallengeMyBadgesRes>>(challengeList, HttpStatus.OK);
    }

    // 뱃지 획득 시 등록하기
    @PostMapping("/addChallenge")
    @ApiOperation(value = "", notes = "")
    public ResponseEntity<? extends BaseResponseBody> addChallenge(@RequestBody ChallengeAddReq challengeAddReq) {
        System.out.println(challengeAddReq.getUserNickname());
        challengeService.addChallenge(challengeAddReq.getUserNickname(), challengeAddReq.getBadgeId());
        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }
}
