package com.dancinggo.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@ApiModel("MyScoreRes")
public class MyScoreRes {

    @ApiModelProperty(name = "점수")
    Long value;

    @ApiModelProperty(name = "랭크")
    Long rank;

}
