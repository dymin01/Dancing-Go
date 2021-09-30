package com.dancinggo.db.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private Long songId;

    @OneToMany(mappedBy = "song")
    private List<Score> scores = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @Column(name = "difficulty")
    private Long difficulty;

    @Column(name = "song_name_kor")
    private String songNameKor;

    @Column(name = "song_name_eng")
    private String songNameEng;

    @Column(name = "singer_kor")
    private String singerKor;

    @Column(name = "singer_eng")
    private String singerEng;

    @Column(name = "song_img")
    private String songImg;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "song_len")
    private Long SongLen;
}
