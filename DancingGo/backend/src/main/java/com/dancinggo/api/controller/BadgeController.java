package com.dancinggo.api.controller;

import com.dancinggo.api.service.BadgeService;
import com.dancinggo.db.entity.Badge;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "뱃지 API", tags = {"Badge"})
@RequestMapping("/badge")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class BadgeController {

    @Autowired
    BadgeService badgeService;

    // 전체 뱃지 가져오기
    @GetMapping("/allBadges")
    @ApiOperation(value = "뱃지 전체 리스트 가져오기", notes = "그냥 다 준다")
    public ResponseEntity<List<Badge>> allBadges(){

        List<Badge> badgeList = badgeService.allBadgeList();

        return new ResponseEntity<List<Badge>>(badgeList, HttpStatus.OK);
    }





}
