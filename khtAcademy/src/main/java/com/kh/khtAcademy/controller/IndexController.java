package com.kh.khtAcademy.controller;

import com.kh.khtAcademy.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller //java에서 service와 html에 변수명을 주고 받는 공간
public class IndexController {
    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/") // - 엔드포인트 html 파일에 작성한 화면을 보여줄 주소
    public String index(Model model) { // model은 index.html에 자바로 작성한 값을 전달할 변수
        List<Map<String, Object>> users = userProfileService.getAllUsers();
        model.addAttribute("users", users);
        //model.addAttribute("message", "Hello World");
        return "index";
    }
}
