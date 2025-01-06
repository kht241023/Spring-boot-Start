package com.kh.khtAcademy.service;

import com.kh.khtAcademy.dto.User;
import com.kh.khtAcademy.mapper.UserProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
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
    public void insertUser(
            String username,
            String email,
            Date birthdate,
            String accountBalance,
            String gender,
            String hobbies,
            MultipartFile profileImagePath) {
        // profileImagePath 가져온 이미지 파일을 바탕화면에 특정 폴더에 저장한 후 바탕화면에 저장된 폴더경로를
        // 가져와서 MYSQL DB 에 [배경화면/이미지정장된폴더/이미지이름]   이미지경로를 넣어줌

        // 1. 프로퍼티를 이용해서 기본 파일 저장 경로 가져오기
        Properties properties = System.getProperties();
        //  user.home = C://Users/user1 까지의 경로가 가져와짐     user.dir = khtAcademy 경로를 가져옴
        // C:/Users/user1/Desktop/SpringBoot-workspace/khtAcademy/khtAcademy
        String projectDir = System.getProperty("user.dir") +"/src/main/resources/static/images/profile_img/";
        System.out.println("projectDir : " + projectDir);
        /*
        * projectDir 을 이용해서 static/images/ 내에 profile_img 폴더를 만들고
        * resource 안에 /static/images/ 내에 이미지 저장
        *
        * */
        String baseDir = System.getProperty("user.home") + "/Desktop/user_images/"; //바탕화면 에서 user_images 폴더에 사진이 저장됨

        // 2. 디렉토리(폴더) 가 존재하지 않으면 생성
        //File imgFolder = new File(baseDir);

        File imgFolder = new File(projectDir);
        if (!imgFolder.exists()) { //만약에 이미지 폴더가 존재하지 않는게 맞다면 ~!
            imgFolder.mkdirs();     // 존재하지 않는 폴더들 모두 생성해 ~!
        }

        // 3. 이미지 파일 이름 가져오기
        String fileName = profileImagePath.getOriginalFilename(); // 사용자 컴퓨터나 핸드폰에 저장된 이름 그대로 가져오기

        // 4. 파일에서 이미지 저장할 경로와 이미지이름을 합치기
        File imageFile = new File(projectDir + fileName);


        try { // 이미지를 저장할 때 생길 문제를 미리 방지

            // 5. 이미지를 저장할 경로와 이미지이름을 합쳤으면 합친 이미지 저장하기
            profileImagePath.transferTo(imageFile);

            // 이미지 파일에서 추출한 이미지 폴더 경로를 작성해서 profileImagePath에 넣어줘야함
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setBirthdate(birthdate);
            user.setAccountBalance(accountBalance);
            user.setGender(gender);
            user.setHobbies(hobbies);
            //System.getProperty("user.dir") +"/src/main/resources/static/images/profile_img/";
            user.setProfileImagePath("/images/profile_img/" + fileName); // -> static 아래에있는 이미지 경로이기 때문에 static 이전폴더는 모두 생략
           // user.setProfileImagePath(imageFile.getAbsolutePath()); //프로필 이미지를 저장하고 저장한 경로 가져오기

            userProfileMapper.insertUser(user);

            System.out.println("파일 업로드를 성공적으로 완료했습니다.");
            System.out.println("저장경로 : " + imageFile.getAbsolutePath());

        } catch (IOException e) {
            // 개발자한테 이미지를 저장할 때 문제가 생겼음을 알리는 메세지
            System.out.println(e.getMessage() +"이미지 저장 중 문제가 발생했습니다.");
        }
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

    @Override
    public User login(String username, String email) {
        return userProfileMapper.login(username, email);
    }

    @Override
    public List<User> searchHobby(String hobbies) {
        return userProfileMapper.searchHobby(hobbies);
    }

    /**
     *
     * @param username = 회원가입할 때 일치하는 username이 있는지 확인하고
     *    userProfileMapper.checkDuplicatedUsername(username) > 0
     *     checkDuplicatedUsername에서 넘어온 숫자가 1이상이면
     *                 DB에 1명이상 존재하는 것이기 때문에 중복이므로
     *                 중복이 맞습니다.
     * @return          true 전달
     */
    @Override
    public boolean checkDuplicatedUsername(String username) {
        boolean duplicated = userProfileMapper.checkDuplicatedUsername(username) > 0;
        System.out.println("중복 확인 : " + duplicated);
        return duplicated;
    }


}
