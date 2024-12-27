package com.kh.khtAcademy.service;

import com.kh.khtAcademy.dto.User;
import com.kh.khtAcademy.mapper.UserProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    /*
        // interface = List
        List<String> aa = new List<>();

       UserProfileMapper userProfileMapper = new UserProfileMapper() ;
       --> interface로 만들어진 자바 파일은 class 자바파일로 implements 해서 사용해야함
       그대신 @ 어노테이션 명칭을 사용해서 @AutoWired 를 이용해서
       자바 파일을 찾아 사용할 수 있도록 설정

       @AutoWired
       private  UserProfileMapper userProfileMapper ; 와

       UserProfileMapper userProfileMapper = new UserProfileMapper() ; 같음
    */

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Override
    public List<Map<String, Object>> getAllUsers() {
        List<User> userList = userProfileMapper.getAllUsers();
        return userList.stream().map(user -> {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("userId", user.getUserId());
            userMap.put("username", user.getUsername());
            userMap.put("email", user.getEmail());
            userMap.put("birthdate", user.getBirthdate().toString());
            userMap.put("accountBalance", user.getAccountBalance());
            userMap.put("gender", user.getGender());
            userMap.put("hobbies", user.getHobbies());
            return userMap;
        }).collect(Collectors.toList());
    }

    @Override
    public void insertUser(User user) {
        userProfileMapper.insertUser(user);
    }

    @Override
    public String findByUsername(String email) {
        return userProfileMapper.findByUsername(email);
    }

    @Override
    public String findByEmail(String username, String gender) {
        return userProfileMapper.findByEmail(username, gender);
    }

    @Override
    public User getUser(int userId) {
        return userProfileMapper.getUser(userId);     //sql문 호출할 수 있도록 mapper에 전달
    }



}
