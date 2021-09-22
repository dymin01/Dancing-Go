package com.dancinggo.db.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_id")
    private Long scoreId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id")
    private Song song;

    @Column(name = "value")
    private Long value;

    @Column(name = "play_cnt")
    private Long playCnt;

    @PrePersist
    public void prePersist() {
        this.value = this.value == null ? 0 : this.value;
        this.playCnt = this.playCnt == null ? 0 : this.playCnt;
    }
}
