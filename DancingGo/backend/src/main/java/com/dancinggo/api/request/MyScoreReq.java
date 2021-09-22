package com.dancinggo.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("MyScoreReq")
@Data
public class MyScoreReq {

    @ApiModelProperty(name="유저 닉네임")
    String userNickname;

    @ApiModelProperty(name="노래 아이디")
    Long songId;

}
