package org.cos.security1.controller;

import org.cos.security1.Repository.UserRepository;
import org.cos.security1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 뷰를 리턴하겠다.
public class IndexController {
    //.mustache 라이브러리 : 기본폴더, src/main/resources/
    // 뷰리졸버 설정 :  mvc:
    //    view:
    //      prefix: /templates/
    //      suffix: .mustache
    // 위 설정 yml 파일에서 생략 가능
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/user")
    public  @ResponseBody String user(){
    return "user";
    }

    @GetMapping("/admin")
    public  @ResponseBody String admin(){
        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody  String manager(){
        return "manager";
    }
   //시큐리티의 인증 로그인이 낚아쳐버림... -> 작동하지 않게 기스에이블 해주었음
    @GetMapping("/loginForm")
    public String loginFrom(){
        return "loginForm";
    }
    @GetMapping("/joinForm") // 회원가입 페이지
    public String joinFrom(){
        return "joinForm";
    }
    @PostMapping("/join")
    public String join(User user){
        System.out.println(user.toString());
        user.setRole("USER");
        userRepository.save(user);
        //하지만 이렇게 회원가입을 하게 되면
        // 비밀번호 :1234, => 시큐리티로 로그인이 안됨
        // 이유는 채스워드가 암호화가 되어있지 않기 때문에
        //따라서 시큐리티 컨피그에  BCryptPasswordEncoder 빈 등록이 필요 하다.

        //페스워드 인코딩 작업하기
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);
        return "redirect:/loginForm";
        //redirect: = 함수 호출
    }




}
