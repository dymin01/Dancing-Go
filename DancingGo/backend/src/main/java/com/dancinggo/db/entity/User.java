package com.dancinggo.db.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(columnDefinition = "varchar(255) default 'defaultImg'", name = "user_img")
    private String userImg;

    @Column(columnDefinition = "integer default 0", name = "gameover_cnt")
    private Integer gameoverCnt;

    @Column(columnDefinition = "integer default 0", name = "total_play_cnt")
    private Integer totalPlayCnt;

    @Column(columnDefinition = "integer default 0", name = "total_score")
    private Integer totalScore;

    @OneToMany(mappedBy = "user")
    private List<Score> scores = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Challenge> challenges = new ArrayList<>();
}
