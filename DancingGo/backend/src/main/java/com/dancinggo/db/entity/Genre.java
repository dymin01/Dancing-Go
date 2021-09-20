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
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long genreId;

    @OneToMany(mappedBy = "genre")
    private List<Song> songs = new ArrayList<>();

    @Column(name = "genre_name_kor")
    private String genreNameKor;

    @Column(name = "genre_name_eng")
    private String genreNameEng;

}
