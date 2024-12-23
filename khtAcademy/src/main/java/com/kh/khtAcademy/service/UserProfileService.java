package com.kh.khtAcademy.service;

import com.kh.khtAcademy.dto.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;



// 자바에서 사용할 기능의 목록만 작성!!!
public interface UserProfileService {
    // service로 사용할 기능 설정

    // html로 서비스 기능을 통해 나타난 결과를 보여줄 기능들 작성

    // 모든 유저 보기 기능
    List<Map<String, Object>> getAllUsers();

    // 유저 저장하기 기능
    void insertUser(User user);
}
