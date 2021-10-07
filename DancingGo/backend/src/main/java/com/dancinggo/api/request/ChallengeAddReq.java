package com.dancinggo.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ChallengeAddReq")
@Data
public class ChallengeAddReq {
    @ApiModelProperty(name="뱃지 아이디")
    Long badgeId;

    @ApiModelProperty(name="유저 닉네임")
    String userNickname;
}
