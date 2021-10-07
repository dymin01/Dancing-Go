package com.dancinggo.api.controller;

import com.dancinggo.api.request.SongAddReq;
import com.dancinggo.api.response.SongGetRes;
import com.dancinggo.api.service.SongService;
import com.dancinggo.common.response.BaseResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "노래 API", tags = {"Song"})
@RequestMapping("/song")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class SongController {

    @Autowired
    SongService songService;

    //노래 전체 가져오기
    @GetMapping("/allSongs")
    @ApiOperation(value = "", notes = "")
    public ResponseEntity<List<SongGetRes>> allSongs() {
        List<SongGetRes> songGetResList = songService.allSong();
        return new ResponseEntity<List<SongGetRes>>(songGetResList, HttpStatus.OK);
    }

    //노래 한곡 정보 가져오기
    @GetMapping("/getSong/{songId}")
    @ApiOperation(value = "", notes = "")
    public ResponseEntity<SongGetRes> oneSong(@PathVariable("songId") Long songId) {
        SongGetRes songGetRes = songService.oneSong(songId);
        return new ResponseEntity<SongGetRes>(songGetRes, HttpStatus.OK);
    }

    //노래 등록하기
    @PostMapping("/addSong")
    @ApiOperation(value = "", notes = "")
    public ResponseEntity<? extends BaseResponseBody> addSongs(@RequestBody SongAddReq songAddReq) {
        songService.addSong(songAddReq);
        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }
}
