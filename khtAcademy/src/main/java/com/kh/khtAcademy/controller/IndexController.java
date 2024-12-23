package com.kh.khtAcademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //java에서 service와 html에 변수명을 주고 받는 공간
public class IndexController {

    @GetMapping("/") // - 엔드포인트 html 파일에 작성한 화면을 보여줄 주소
    public String index(Model model) { // model은 index.html에 자바로 작성한 값을 전달할 변수
        model.addAttribute("message", "Hello World");
        return "index";
    }
}
