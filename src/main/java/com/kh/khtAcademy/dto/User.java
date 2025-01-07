package com.kh.khtAcademy.dto;
// javax -> jakarta
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor    // 필수 생성자
@NoArgsConstructor     // 기본생성자
@Setter                // setter 줄임말로 사용
@Getter                // getter 줄임말로 사용
@ToString             //toString 줄임말로 사용
public class User implements Serializable {
    //  implements Serializable User에 저장된 객체를 파일이나 네트워크로 전송
    //  객체를 임시저장 빠르게 복원
    /*
    * private static final long serialVersionUID = 1L 작성하지 않아도
    * 프로그램 실행할 때마다 임의의 ID값이 설정
    * 자동으로 생성된 ID값에 따라 저장
    *
    * 1 = 개발자가 설정하는 숫자값으로 무조건 1이어야할 이유 XXXXX
    * Long 타입으로 버전 숫자를 작성
    * */
    private static final long serialVersionUID = 1L;



    // 네트워크 전송을 위한 직렬화 버전
    // 버전을 통해 객체를 버전별로 저장하고 불러옴 가능
    // 보안 노출 위험
    @Id //primary key 표기로 id는 맨 위에 작성
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 숫자자동증가임을 설정
    private int userId;
    private String username;
    private String email;

    /*
    private String birthdate;
    birthdate SQL 에서는 DATE 타입
             JAVA 에서는 String 타입
             만약 SQL에서 DATE 타입인 자료형을 JAVA에서 String 타입으로 사용하려면
             mybatis-config.xml 에서
             SQL DATE 타입을 String으로 받아서 가져오겠다 는 형식 변환 표기

    private Date birthdate;
        birthdate SQL 에서는 DATE 타입
                 JAVA 에서는 DATE 타입을 사용한다면
                 import java.util.Date를 가져와서 사용할 것!
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;
    private String accountBalance;
    private String gender;
    private String hobbies;
    private String profileImagePath;


}
