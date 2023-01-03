package com.glee.controller;

import com.glee.Kakao.service.AuthService;
import com.glee.Kakao.service.CustomGetFriendsSerivce;
import com.glee.Kakao.service.CustomMessageService;
import com.glee.dto.KakaoCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KakaoController {

    @Autowired
    AuthService authService;

    @Autowired
    CustomMessageService customMessageService;

    @Autowired
    CustomGetFriendsSerivce customGetFriendsSerivce;


    @GetMapping("/kakao")
    public String serviceStart(String code) {
        if(authService.getKakaoAuthToken(code, KakaoCode.message)) {
            customMessageService.sendMyMessage();
            return "msgSuccess";
        }else {
            return "토큰발급 실패";
        }
    }

    @GetMapping("/friend")
    public String getFriend(String code){
        if (authService.getKakaoAuthToken(code, KakaoCode.friend)) {
            customGetFriendsSerivce.getMyFriends();
            return "success";
        } else {
            return "fail";
        }
    }


}
