package com.dancinggo.api.controller;

import com.dancinggo.api.service.UserService;
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
}
