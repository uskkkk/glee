package com.glee.Kakao.service;

import com.glee.Kakao.HttpCallService;
import com.glee.dto.FriendsDto;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetFriendsService extends HttpCallService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String GET_FRIENDS_SERVICE_URI = "https://kapi.kakao.com/v1/api/talk/friends";
    private static final String GET_SUCCESS_MSG = "친구 목록요청에 성공했습니다.";
    private static final String GET_FAIL_MSG = "친구 목록요청에 실패했습니다.";

    private static final String SUCCESS_CODE = "0"; //kakao api에서 return해주는 success code 값

    public boolean getFriends(String accessToken, FriendsDto friDto) {

        HttpHeaders header = new HttpHeaders();
//        header.set("Content-Type", APP_TYPE_JSON);
        header.set("Authorization", "Bearer " + accessToken);

        List<FriendsDto> parameters = new ArrayList<>();
        parameters.add(friDto);
//        parameters.put("limit",friDto.getLimit());
//        parameters.put("order",friDto.getOrder());
//        parameters.put("friend_order",friDto.getFriend_order());

        HttpEntity<?> getFriendRequestEntity = httpClientEntity(header, null);

        String resultCode = "";
        ResponseEntity<String> response = httpRequest(GET_FRIENDS_SERVICE_URI, HttpMethod.GET,getFriendRequestEntity);
        JSONObject jsonData = new JSONObject(response.getBody());
        resultCode = jsonData.get("total_count").toString();

        return successCheck(resultCode);
    }


    public boolean successCheck(String resultCode) {
        if(resultCode.equals(SUCCESS_CODE)) {
            logger.debug(GET_SUCCESS_MSG);
            return true;
        }else {
            logger.debug(GET_FAIL_MSG);
            return false;
        }

    }
}
