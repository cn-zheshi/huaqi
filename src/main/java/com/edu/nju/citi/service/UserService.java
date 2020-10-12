package com.edu.nju.citi.service;

import com.edu.nju.citi.VO.*;
import com.edu.nju.citi.form.*;

public interface UserService {
    ResponseVO creat(UserForm usr);
    ResponseVO login(UserLoginForm loginform);
    ResponseVO info(String sessionID);
    ResponseVO bind(String sessionID,CitiBindingForm citiAccount);
}
