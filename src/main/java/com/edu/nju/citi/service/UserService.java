package com.edu.nju.citi.service;

import com.edu.nju.citi.VO.*;
import com.edu.nju.citi.form.*;

public interface UserService {
    Pair<ResponseVO,String> creat(UserForm usr);
    ResponseVO login(UserLoginForm loginform);
    ResponseVO info(String sessionID);
    ResponseVO bind(String sessionID,CitiBindingForm citiAccount);
    String getSessionID(String emailOrName);
}
