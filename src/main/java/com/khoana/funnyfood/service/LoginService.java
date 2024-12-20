package com.khoana.funnyfood.service;

import com.khoana.funnyfood.dto.UserDTO;
import com.khoana.funnyfood.entity.Role;
import com.khoana.funnyfood.entity.User;
import com.khoana.funnyfood.payload.request.SignUpRequest;
import com.khoana.funnyfood.repository.UserRepository;
import com.khoana.funnyfood.security.CustomFilterSecurity;
import com.khoana.funnyfood.service.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginService implements LoginServiceImp {
    @Autowired
    UserRepository userRepo;
    @Autowired
    CustomFilterSecurity customFilterSecurity;
    //PasswordEncoder passwordEncoder;

    public List<UserDTO> getAllUser(){
        List<User> userList = userRepo.findAll();
        return userList.stream().
                map(user ->{
                                    UserDTO userDTO = new UserDTO();
                                    userDTO.setUsername(user.getUsername());
                                    userDTO.setFullName(user.getFullName());
                                    userDTO.setPassword(user.getPassword() );
                                    return userDTO;
                                }).collect(Collectors.toList());
    }

    public boolean checkLogin(String username, String password){
        User user = userRepo.findByUsernameAndPassword(username, password);
        return user != null;
    }

    public boolean signUp(SignUpRequest request){

        if(getUserByUsername(request.getUsername()) != null){
            //throw new RuntimeException("User already exists");
            return false;
        }
        User user = new User();

        Role role = new Role();
        role.setId(1);
        user.setUsername(request.getUsername());
        user.setPassword(customFilterSecurity.passwordEncoder().encode(request.getPassword()));
        user.setFullName(request.getFullname());
        user.setRole(role);
        userRepo.save(user);
        return true;
    }

    public User getUserByUsername(String username){
        return userRepo.findUserByUsername(username);
    }



}
