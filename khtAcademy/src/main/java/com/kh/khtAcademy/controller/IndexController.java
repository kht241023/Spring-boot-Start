package com.kh.khtAcademy.controller;

import com.kh.khtAcademy.dto.User;
import com.kh.khtAcademy.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller //java에서 service와 html에 변수명을 주고 받는 공간
public class IndexController {
    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/") // - 엔드포인트 html 파일에 작성한 화면을 보여줄 주소
    public String index(Model model) { // model은 index.html에 자바로 작성한 값을 전달할 변수
        List<Map<String, Object>> users = userProfileService.getAllUsers();
        System.out.println("users 목록 확인  : "  + users);
        model.addAttribute("users", users);
        //model.addAttribute("message", "Hello World");
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        // 회원가입을 진행할 때는 DB에서 가져올게 없기 때문에
        // Model model을 사용하지 않고
        // http://localhost:8080/register 로 이동했을 때
        // register.html 에 작성한 화면이 보일 수 있도록 설정
        return "register";
    }
}
