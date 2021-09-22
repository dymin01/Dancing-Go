package com.dancinggo.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ChallengeMyBadgesRes")
@Builder
public class ChallengeMyBadgesRes {

//    @ApiModelProperty(name="도전 아이디")
//    Long challengeId;

    @ApiModelProperty(name="뱃지 아이디")
    Long badgeId;

    @ApiModelProperty(name="유저 닉네임")
    String userNickname;
}
