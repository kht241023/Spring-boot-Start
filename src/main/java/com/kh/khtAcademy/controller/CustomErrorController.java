package com.kh.khtAcademy.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.geom.RectangularShape;

/*
* java 자체에서 만든 ErrorController.java 파일이 존재하기 때문에
* 직접적으로 ErrorController.java라는 이름 사용 X
* 왜냐하면 우선순위에서 개발자가 만든 파일명칭이 우선적으로 사용되고
* 자바에서 만든 파일은 사용 X
*
* */
@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String error(HttpServletRequest request, Model model, HttpSession session) {
        //세션확인
        Object abc = session.getAttribute("loggedInUser");
        System.out.println("loggedInUser 로 가져와서 abc 에 넣어준 값 : " + abc);

        if (abc != null) {
            model.addAttribute("loggedInUser", abc);
            model.addAttribute("errorMessage", "세션이 만료되었습니다. 다시 로그인해주세요.");
        } else {
            model.addAttribute("errorMessage", "예기치 못한 문제가 발생했습니다.");
        }
        return "error";
    }
}
