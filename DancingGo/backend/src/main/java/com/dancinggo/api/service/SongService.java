package com.dancinggo.api.service;

import com.dancinggo.api.request.SongAddReq;
import com.dancinggo.api.response.SongGetRes;

import java.util.List;

public interface SongService {

    void addSong(SongAddReq songAddReq);

    SongGetRes oneSong(Long songId);

    List<SongGetRes> allSong();
}
