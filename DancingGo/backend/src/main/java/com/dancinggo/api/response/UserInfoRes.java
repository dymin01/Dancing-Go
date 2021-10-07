package com.dancinggo.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserInfoRes")
@Data
public class UserInfoRes {

    @ApiModelProperty(name="유저 닉네임")
    String userNickname;

    @ApiModelProperty(name="유저 랭킹")
    int rank;

    @ApiModelProperty(name="유저 이미지")
    String userImg;

    @ApiModelProperty(name="유저 총점")
    long totalScore;

    public UserInfoRes(){};

    public UserInfoRes(String userNickname, Long totalScore, int rank, String userImg){
        this.userNickname = userNickname;
        this.totalScore = totalScore;
        this.rank = rank;
        this.userImg = userImg;
    }
}
