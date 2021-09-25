package com.dancinggo.api.controller;

import com.dancinggo.api.service.UserService;
import com.dancinggo.common.response.ApiResponse;
import com.dancinggo.common.response.BaseResponseBody;
import com.dancinggo.db.entity.User;
import com.dancinggo.oauth.entity.RoleType;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping
    public ApiResponse getUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(principal.getUsername());

        User tempUser = new User(user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getEmailVerifiedYn(),
                user.getProfileImageUrl(),
                user.getUserNickname(),
                user.getProviderType(),
                user.getRoleType(),
                user.getCreatedAt(),
                user.getModifiedAt());

        return ApiResponse.success("user", tempUser);
    }

}
