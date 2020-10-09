package com.edu.nju.citi.controller;


import com.edu.nju.citi.VO.ResponseVO;
import com.sun.xml.messaging.saaj.packaging.mime.internet.ContentType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/account") //domain为网站的域名
public class UserController {
    //cookie 用来判断用户是否登陆，但采用什么类存疑
    @PostMapping("/create")
    public ResponseEntity<ResponseVO> accountCreationRequest(@RequestHeader("uuid") String uuid, @RequestHeader("Content-Type") String type) {
        return null;
    }

    //账户注册
    @GetMapping("/request_verification")
    public ResponseEntity<ResponseVO> verification(Cookie cookie, UUID uuid) {
        return null;
    }

    //账户验证
    @PostMapping("/verify")
    public ResponseEntity<ResponseVO> verify(@RequestHeader("uuid") String uuid, @CookieValue("session_id") String sessionID, @RequestHeader("Content-Type") String type) {
        return null;
    }

    //验证验证码是否正确


    @PostMapping("/login")
    public ResponseEntity<ResponseVO> userLogin(@RequestHeader("uuid") String uuid, @RequestHeader("Content-Type") String type) {  //Content-Type: application/json required
        return null;
    }

    //用户登陆

    @GetMapping("/info")
    public ResponseEntity<ResponseVO> accountInfo(@RequestHeader("uuid") String uuid, @CookieValue("session_id") String sessionID) {
        return null;
    }

    //查看账户信息


    @PostMapping("/info")
    public ResponseEntity<ResponseVO> changeAccountInfo(@RequestHeader("uuid") String uuid, @CookieValue("session_id") String sessionID, @RequestHeader("Content-Type") String type) {
        return null;
    }


    @PostMapping("/bind")
    public ResponseEntity<ResponseVO> bindCitiAccount(@RequestHeader("uuid") String uuid, @CookieValue("session_id") String sessionID, @RequestHeader("Content-Type") String type) {
        return null;
    }


}
