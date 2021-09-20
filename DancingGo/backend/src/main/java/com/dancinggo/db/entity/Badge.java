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
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_id")
    private Long badgeId;

    @OneToMany(mappedBy = "badge")
    private List<Challenge> challenges = new ArrayList<>();

    @Column(name = "badge_name_kor")
    private String badgeNameKor;

    @Column(name = "badge_name_eng")
    private String badgeNameEng;

    @Column(name = "condition_kor")
    private String conditionKor;

    @Column(name = "condition_eng")
    private String conditionEng;
}
