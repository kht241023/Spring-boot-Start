package com.kh.khtAcademy.mapper;

import com.kh.khtAcademy.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper // sql 에 작성한 id를 가져와서 자바에서 사용하겠다는 설정 표기
public interface UserProfileMapper {
    // xml 파일에 id 값 설정한 기능 목록 조회

    // 모든 유저 목록 조회
    List<User> getAllUsers();

    // 유저 저장하기
    void insertUser(User user);

    // 이메일로 이름 찾기
    String findByUsername(String email);

    // 이름과 성별로 이메일 찾기
    String findByEmail(String username, String gender);
}
