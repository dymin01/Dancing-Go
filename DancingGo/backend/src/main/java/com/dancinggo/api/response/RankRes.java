package com.dancinggo.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("RankRes")
@Data
public class RankRes {

    @ApiModelProperty(name="유저 닉네임")
    String userNickname;

    @ApiModelProperty(name="유저 랭킹")
    int rank;

    public RankRes(String userNickname, int rank){
        this.userNickname = userNickname;
        this.rank = rank;
    }

}
