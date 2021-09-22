package com.dancinggo.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@ApiModel("songRankRes")
public class songRankRes {

    @ApiModelProperty(name = "사용자 닉네임")
    String userNickname;

    @ApiModelProperty(name = "사용자 이미지")
    String userImg;

    @ApiModelProperty(name = "점수")
    Long value;

}
