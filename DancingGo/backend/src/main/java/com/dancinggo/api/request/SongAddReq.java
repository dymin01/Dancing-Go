package com.dancinggo.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("SongAddReq")
@Data
@Builder
public class SongAddReq {

    @ApiModelProperty(name = "난이도")
    Long difficulty;

    @ApiModelProperty(name = "곡 이름(한글)")
    String songNameKor;

    @ApiModelProperty(name = "곡 이름(영어)")
    String songNameEng;

    @ApiModelProperty(name = "가수 이름(한국)")
    String singerKor;

    @ApiModelProperty(name = "가수 이름(영어)")
    String singerEng;

    @ApiModelProperty(name = "엘범커버")
    String songImg;

    @ApiModelProperty(name = "파일이름")
    String fileName;

    @ApiModelProperty(name = "장르 id")
    Long genreId;

    @ApiModelProperty(name="곡의 길이")
    String songLen;
}
