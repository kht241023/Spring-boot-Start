package com.kh.khtAcademy.mapper;

import com.kh.khtAcademy.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
// xml에 작성한 select insert update delete 쿼리를
// xml 에서 작성한 id값을 통해 sql문 연결
@Mapper // sql 에 작성한 id를 가져와서 자바에서 사용하겠다는 설정 표기
public interface UserProfileMapper {
    // xml 파일에 id 값 설정한 기능 목록 조회

    // 모든 유저 목록 조회
    //SELECT * FROM USERPROFILE
    List<User> getAllUsers();

    // 유저 저장하기
    void insertUser(User user);

    // 이메일로 이름 찾기
    String findByUsername(String email);

    // 이름과 성별로 이메일 찾기
    String findByEmail(String username, String gender);

    // 유저 아이디 번호 통해서 유저에 대한 정보 가져오기
    User getUser(int userId);


    // mapper.xml 에 작성한 SQL문 연결하는 메서드명칭 작성
    User login(String username,  String email);


    // 정보 하나를 가져올 때는         자료형에 String, int, DTO  파일 명칭을 작성

    // 정보를 두 가지 이상 가져올 때는 자료형에 List 사용해서 가져옴
    List<User> searchHobby(String hobbies);

    // 유저네임이 중복인지 확인 = String username이 존재하면
    // 0,1,2,.. 와 같은 숫자값이 넘어올 것이기 때문에 int 사용
    int checkDuplicatedUsername(String username);

}
