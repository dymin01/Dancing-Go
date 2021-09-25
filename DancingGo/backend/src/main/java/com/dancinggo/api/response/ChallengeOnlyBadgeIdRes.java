package com.dancinggo.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ChallengeOnlyBadgeIdRes")
@Builder
public class ChallengeOnlyBadgeIdRes {

    @ApiModelProperty(name="뱃지 아이디")
    Long badgeId;

}
