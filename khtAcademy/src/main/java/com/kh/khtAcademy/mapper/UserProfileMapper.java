package com.kh.khtAcademy.mapper;

import com.kh.khtAcademy.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper // sql 에 작성한 id를 가져와서 자바에서 사용하겠다는 설정 표기
public interface UserProfileMapper {
    List<User> getAllUsers();
}
