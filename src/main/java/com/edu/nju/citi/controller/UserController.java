package com.edu.nju.citi.controller;


import com.edu.nju.citi.VO.ResponseCode;
import com.edu.nju.citi.VO.ResponseVO;
import com.edu.nju.citi.form.*;
import com.edu.nju.citi.service.UserService;
import com.edu.nju.citi.service.impl.UserServiceImpl;
import com.sun.xml.messaging.saaj.packaging.mime.internet.ContentType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.Cookie;

import java.net.http.HttpHeaders;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/account") //domain为网站的域名
public class UserController {
    UserService userService=new UserServiceImpl();
    ResponseVO rvo;
    //cookie 用来判断用户是否登陆，但采用什么类存疑
    //账户注册
    @PostMapping("/create")
    public ResponseEntity<ResponseVO> accountCreationRequest(@RequestHeader("uuid") String uuid, @RequestHeader("Content-Type") String type,@RequestBody UserForm usr) {
        rvo=userService.creat(usr);
        if(rvo.getCode().equals(ResponseCode.ERROR)){
            return new ResponseEntity<>(ResponseVO.error("用户已存在"), HttpStatus.BAD_REQUEST);
        }
        HttpHeaders headers=new HttpHeaders();
        String id=usr.getEmail();//这个咋整咱也不知道先用着email?
        headers.add("Set-Cookie","session-id="+id);
        return new ResponseEntity<>(rvo,headers,HttpStatus.OK);
    }

    /*账户验证 unused
    @GetMapping("/request_verification")
    public ResponseEntity<ResponseVO> verification(Cookie cookie, UUID uuid) {
        return null;
    }
    */

    //验证验证码是否正确 似乎也是unused 先不写这个
    @PostMapping("/verify")
    public ResponseEntity<ResponseVO> verify(@RequestHeader("uuid") String uuid, @CookieValue("session_id") String sessionID, @RequestHeader("Content-Type") String type) {
        return null;
    }

    //用户登陆
    @PostMapping("/login")
    public ResponseEntity<ResponseVO> userLogin(@RequestHeader("uuid") String uuid, @RequestHeader("Content-Type") String type, @RequestBody UserLoginForm loginform) {  //Content-Type: application/json required
        rvo=userService.login(loginform);
        if(rvo.getCode().equals(ResponseCode.ERROR)){
            return new ResponseEntity<>(ResponseVO.error("用户名或密码错误"),HttpStatus.UNAUTHORIZED);
        }
        HttpHeaders headers=new HttpHeaders();
        String id=loginform.getEmail();//这个咋整咱也不知道先用着email?
        headers.add("Set-Cookie","session-id="+id);
        return new ResponseEntity<>(rvo,headers,HttpStatus.OK);
    }

    //查看账户信息
    @GetMapping("/info")
    public ResponseEntity<ResponseVO> accountInfo(@RequestHeader("uuid") String uuid, @CookieValue("session_id") String sessionID) {
        if(sessionID==null){
            return new ResponseEntity<>(ResponseVO.error("用户未登录"), HttpStatus.BAD_REQUEST); 
        }
        rvo=userService.info(sessionID);
        if(rvo.getCode().equals(ResponseCode.ERROR)){
            return new ResponseEntity<>(ResponseVO.error("用户未找到"), HttpStatus.UNAUTHORIZED); 
        }
        return new ResponseEntity<>(rvo,HttpStatus.OK);
    }

    /*
    更改账户信息 unused
    @PostMapping("/info")
    public ResponseEntity<ResponseVO> changeAccountInfo(@RequestHeader("uuid") String uuid, @CookieValue("session_id") String sessionID, @RequestHeader("Content-Type") String type) {
        return null;
    }
    */

    @PostMapping("/bind")
    public ResponseEntity<ResponseVO> bindCitiAccount(@RequestHeader("uuid") String uuid, @CookieValue("session_id") String sessionID, @RequestHeader("Content-Type") String type,@RequestBody CitiBindingForm citiAccount) {
        if(sessionID==null){
            return new ResponseEntity<>(ResponseVO.error("用户未登录"),HttpStatus.UNAUTHORIZED);
        }
        rvo=userService.bind(sessionID, citiAccount);
        if(rvo.getCode().equals(ResponseCode.ERROR)){
            return new ResponseEntity<>(ResponseVO.error("绑定失败"), HttpStatus.BAD_REQUEST); 
        }
        return new ResponseEntity<>(rvo,HttpStatus.OK);
    }


}
