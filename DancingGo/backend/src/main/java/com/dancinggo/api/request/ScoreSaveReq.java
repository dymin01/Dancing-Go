package com.dancinggo.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ScoreSaveReq")
@Data
public class ScoreSaveReq {

    @ApiModelProperty(name="유저 닉네임")
    String userNickname;

    @ApiModelProperty(name="노래 아이디")
    Long songId;

    @ApiModelProperty(name="노래 점수")
    Long value;
    
}
