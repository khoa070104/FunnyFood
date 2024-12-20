package com.khoana.funnyfood.service;

import com.khoana.funnyfood.dto.UserDTO;
import com.khoana.funnyfood.repository.UserRepository;
import com.khoana.funnyfood.service.imp.UserServiceImp;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserService implements UserServiceImp {
    UserRepository userRepo;
    @Override
    public List<UserDTO> getAllUser() {
        return userRepo.findAll().stream().map(user ->{
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(user.getUsername());
            userDTO.setFullName(user.getFullName());
            userDTO.setPassword(user.getPassword());
            return userDTO;
        }).collect(Collectors.toList());
    }
}
