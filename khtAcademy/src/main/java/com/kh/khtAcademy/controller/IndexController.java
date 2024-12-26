package com.kh.khtAcademy.controller;

import com.kh.khtAcademy.dto.User;
import com.kh.khtAcademy.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    // DB에 값을 집어넣을 때는 PostMapping 사용하고 엔드포인트 form action에서 작성한
    // 주소를 엔드포인트로 지정
    @PostMapping("/register-success")
    public String registerSuccess(@ModelAttribute("user") User user, Model model) {
        userProfileService.insertUser(user);
        model.addAttribute("msg", "회원가입이 성공적으로 완료되었습니다.");
        return "success"; //회원가입이 무사히 완료될 경우 success 페이지로 이동
    }

    // get DB에서 값을 가져와 조회하거나 또는 endpoint를 통해 html 화면 보여주기 위해 작성
    @GetMapping("/find-username") // endpoint = api = /find-username
    public String findByUsername(){
        return "find-username";
    }

    // 이메일로 이름을 찾은 결과 find-username-result 페이지에서 보여주기
    @GetMapping("/find-username-result") // endpoint = api = /find-username-result
    public String findByUsername(@RequestParam("email")String email,
                                 Model model){
        String username = userProfileService.findByUsername(email);
        model.addAttribute("username", username);
        return "find-username-result";
    }

    @GetMapping("/find-email") // endpoint = api = /find-username
    public String findByEmail(){
        return "find-email";
    }
    @GetMapping("/find-email-result")
    public String findByEmail(@RequestParam("username")String username,
                              @RequestParam("gender") String gender,
                              Model model){
            userProfileService.findByEmail(username,gender);
            model.addAttribute("email", userProfileService.findByEmail(username,gender));
        return "find-email-result";
    }

    /**
     * @GetMapping("/detail/{userId}")   특정변수에 대한 값으로 페이지 변동을 원한다면 {변수이름}
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("/detail/{userId}")
    public String getUser(@PathVariable int userId, Model model){
        /*
            자료형으로 service 변수명 설정할 때 어떤 자료형을 사용해야할지 모르겠다면
            0. service에서 기능 명칭 앞에 자료형을 무엇을 작성했는지 확인하는 것이 제일 좋음
            1. String 작성해보기   2. Model 작성해보기  3. DTO에 작성한 클래스명칭 작성해보기
            ※ 단 자료형이 void일 경우 변수명 설정이 어려움
        */
       User user =  userProfileService.getUser(userId);
       model.addAttribute("user",user);
       return "detail";
    }



    /*
    * controller - Get - Post - RequestParam
    * */
}
