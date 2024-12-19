package com.khoana.funnyfood.service.imp;

import com.khoana.funnyfood.dto.UserDTO;
import com.khoana.funnyfood.entity.User;
import com.khoana.funnyfood.payload.request.SignUpRequest;

import java.util.List;

public interface LoginServiceImp {
    List<UserDTO> getAllUser();
    boolean checkLogin(String username, String password);
    boolean signUp(SignUpRequest request);
    User getUserByUsername(String username);

}
