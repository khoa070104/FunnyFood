package com.khoana.funnyfood.service.imp;

import com.khoana.funnyfood.dto.UserDTO;
import com.khoana.funnyfood.entity.User;

import java.util.List;

public interface UserServiceImp {
    List<UserDTO> getAllUser();
}
