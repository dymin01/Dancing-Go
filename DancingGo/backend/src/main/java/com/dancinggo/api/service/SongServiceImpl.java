package com.dancinggo.api.service;

import com.dancinggo.api.request.SongAddReq;
import com.dancinggo.api.response.SongGetRes;
import com.dancinggo.db.entity.Song;
import com.dancinggo.db.repository.GenreRepository;
import com.dancinggo.db.repository.ScoreRepository;
import com.dancinggo.db.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongServiceImpl implements SongService{

    @Autowired
    SongRepository songRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    ScoreRepository scoreRepository;

    @Override
    public void addSong(SongAddReq songAddReq) {
        songRepository.save(Song.builder()
                .difficulty(songAddReq.getDifficulty())
                .songNameKor(songAddReq.getSongNameKor())
                .songNameEng(songAddReq.getSongNameEng())
                .singerKor(songAddReq.getSingerKor())
                .singerEng(songAddReq.getSingerEng())
                .songImg(songAddReq.getSongImg())
                .fileName(songAddReq.getFileName())
                .genre(genreRepository.findByGenreId(songAddReq.getGenreId()).get())
                .build()
        );

    }

    @Override
    public SongGetRes oneSong(Long songId) {

        long maxValue = scoreRepository.findByScore(songId);

        Song song = songRepository.findBySongId(songId).get();
        SongGetRes songGetRes = SongGetRes.builder()
                .songId(song.getSongId())
                .difficulty(song.getDifficulty())
                .songNameKor(song.getSongNameKor())
                .songNameEng(song.getSongNameEng())
                .singerKor(song.getSingerKor())
                .singerEng(song.getSingerEng())
                .songImg(song.getSongImg())
                .fileName(song.getFileName())
                .genreId(song.getGenre().getGenreId())
                .value(maxValue)
                .build();

        return songGetRes;
    }

    @Override
    public List<SongGetRes> allSong() {
        List<SongGetRes> songGetResList = new ArrayList<>();

        List<Song> allSongList = songRepository.findAll();

        for (Song s : allSongList) {
            songGetResList.add(SongGetRes.builder()
                    .songId(s.getSongId())
                    .difficulty(s.getDifficulty())
                    .songNameKor(s.getSongNameKor())
                    .songNameEng(s.getSongNameEng())
                    .singerKor(s.getSingerKor())
                    .singerEng(s.getSingerEng())
                    .songImg(s.getSongImg())
                    .fileName(s.getFileName())
                    .genreId(s.getGenre().getGenreId())
                    .build()
            );
        }
        return songGetResList;
    }
}
