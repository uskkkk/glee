package com.glee.Kakao.service;

import com.glee.dto.FriendsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomGetFriendsSerivce {

    @Autowired
    GetFriendsService getFriendsService;

    public boolean getMyFriends() {
        FriendsDto friendsDto = new FriendsDto();
        friendsDto.setOffset(0);
        friendsDto.setLimit(3);
        friendsDto.setOrder("desc");
        friendsDto.setFriend_order("favorite");

        String accessToken = AuthService.authToken;

        return getFriendsService.getFriends(accessToken,friendsDto);
    }
}
