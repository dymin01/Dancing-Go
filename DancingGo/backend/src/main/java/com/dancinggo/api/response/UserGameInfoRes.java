package com.dancinggo.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserGameInfoRes")
@Data
@Builder
public class UserGameInfoRes {

    @ApiModelProperty(name="유저 seq")
    Long userSeq;

    @ApiModelProperty(name="유저 닉네임")
    String userNickname;

    @ApiModelProperty(name="유저 랭킹")
    int rank;

    @ApiModelProperty(name="전체 플레이 횟수")
    Long totalPlayCnt;

    @ApiModelProperty(name="게임오버 카운트")
    Long gameoverCnt;

}
