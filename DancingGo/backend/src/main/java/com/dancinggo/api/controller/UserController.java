package com.dancinggo.api.controller;

import com.dancinggo.api.request.NicknameSaveReq;
import com.dancinggo.api.service.UserService;
import com.dancinggo.common.response.BaseResponseBody;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class UserController {

    private final UserService userService;

    // 닉네임 중복 확인.
    // true : 이미 닉네임 있음
    // false : 닉에님 사용 가능
    @GetMapping("/nickname/{userNickname}")
    public ResponseEntity<Boolean> nicknameisExists(@RequestParam("userNickname") String userNickname){

        boolean isExists = userService.nicknameisExists(userNickname);
        return new ResponseEntity<>(isExists, HttpStatus.OK);
    }

    //닉네임 수정
    @PutMapping("/nickname")
    public ResponseEntity<? extends BaseResponseBody> saveNickname(@RequestBody NicknameSaveReq nicknameSaveReq){

        boolean isOk = userService.setUserNickName(nicknameSaveReq);

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "닉네임 등록/변경 성공"));

    }
}
