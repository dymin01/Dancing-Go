package com.dancinggo.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BadgeRes")
@Builder
public class BadgeRes {

    @ApiModelProperty(name="뱃지 아이디")
    Long badgeId;

    @ApiModelProperty(name="뱃지 한국어 이름")
    String badgeNameKor;

    @ApiModelProperty(name="뱃지 영어 이름")
    String badgeNameEng;

    @ApiModelProperty(name="뱃지 한국어 조건")
    String conditionKor;

    @ApiModelProperty(name="뱃지 영어 조건")
    String conditionEng;

}
